package com.inghub.credit.service;

import com.inghub.core.common.pagable.BasePageableUtils;
import com.inghub.core.common.pagable.BaseSortUtils;
import com.inghub.core.data.exception.EntityCouldNotBeFoundException;
import com.inghub.core.exceptionHandling.BaseCheckedException;
import com.inghub.credit.mapper.CustomerMapper;
import com.inghub.credit.model.dto.CustomerDto;
import com.inghub.credit.model.dto.CustomerQueryDto;
import com.inghub.credit.model.entity.CustomerEntity;
import com.inghub.credit.model.entity.QCustomerEntity;
import com.inghub.credit.repositoy.CustomerRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gyurtalan
 * @version 1.0
 */

@Service
@Transactional(rollbackFor = BaseCheckedException.class)
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper mapper;
    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository repository) {

        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Long create(CustomerDto dto) {

        CustomerEntity CustomerEntity = mapper.convertToEntity(dto);
        repository.save(CustomerEntity);

        return CustomerEntity.getId();
    }

    @Override
    public CustomerDto update(CustomerDto dto) {
        CustomerEntity CustomerEntity = mapper.convertToEntity(dto);
        if (!repository.existsById(CustomerEntity.getId())) {
            throw new EntityCouldNotBeFoundException(CustomerEntity.class.getSimpleName(), CustomerEntity.getId().toString());
        }
        return mapper.convertToDto(repository.update(CustomerEntity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityCouldNotBeFoundException(CustomerEntity.class.getSimpleName(), id.toString());
        }
        repository.deleteById(id);
    }

    @Override
    public CustomerDto getById(Long id) {
        return mapper.convertToDto(repository.findById(id).orElseThrow(() -> new EntityCouldNotBeFoundException(CustomerEntity.class.getSimpleName(), id.toString())));
    }

    @Override
    public CustomerDto getByUuid(String uuid) {
        return mapper.convertToDto(repository.getByUuid(uuid));
    }

    @Override
    public boolean exists(CustomerQueryDto queryDto) {
        return repository.exists(getQueryBuilder(queryDto));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByUuid(String uuid) {
        return repository.existsByUuid(uuid);
    }

    @Override
    public List<CustomerDto> queryList(CustomerQueryDto queryDto) {
        Predicate p = getQueryBuilder(queryDto);
        return mapper.convertToDtoList(repository.findAll(p, BaseSortUtils.convert(queryDto.getPageable().getSort())));
    }

    @Override
    public Page<CustomerDto> queryPage(CustomerQueryDto queryDto) {
        Predicate predicate = getQueryBuilder(queryDto);
        Page<CustomerEntity> pageList = repository.findAll(predicate, BasePageableUtils.convert(queryDto.getPageable()));

        return mapper.convertToDtoPage(pageList);

    }

    private BooleanBuilder getQueryBuilder(CustomerQueryDto queryDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (queryDto != null) {
            QCustomerEntity q = QCustomerEntity.customerEntity;
            if (Strings.isNotBlank(queryDto.getName()))
                booleanBuilder.and(q.name.contains(queryDto.getName()));
            if (queryDto.getSurname() != null)
                booleanBuilder.and(q.surname.contains(queryDto.getSurname()));
            if (queryDto.getGreaterThanCreditLimit() != null)
                booleanBuilder.and(q.creditLimit.goe(queryDto.getGreaterThanCreditLimit()));
            if (queryDto.getLessThanCreditLimit() != null)
                booleanBuilder.and(q.creditLimit.loe(queryDto.getLessThanCreditLimit()));
            if (queryDto.getGreaterThanUsedCreditLimit() != null)
                booleanBuilder.and(q.usedCreditLimit.goe(queryDto.getGreaterThanUsedCreditLimit()));
            if (queryDto.getLessThanUsedCreditLimit() != null)
                booleanBuilder.and(q.usedCreditLimit.loe(queryDto.getLessThanUsedCreditLimit()));
        }
        return booleanBuilder;
    }

}
