package com.inghub.credit.service;

import com.inghub.core.common.pagable.BaseSortUtils;
import com.inghub.core.data.exception.EntityCouldNotBeFoundException;
import com.inghub.core.exceptionHandling.BaseCheckedException;
import com.inghub.credit.exception.PaymentsNotMadeInFullException;
import com.inghub.credit.mapper.LoanMapper;
import com.inghub.credit.model.dto.*;
import com.inghub.credit.model.entity.CustomerEntity;
import com.inghub.credit.model.entity.LoanEntity;
import com.inghub.credit.model.entity.QLoanEntity;
import com.inghub.credit.repositoy.CustomerRepository;
import com.inghub.credit.repositoy.LoanRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

import static com.inghub.credit.model.constraint.LoanConstraints.LOAN_AMOUNT_FRACTION_SIZE;

@Service
@Transactional(rollbackFor = BaseCheckedException.class)
public class LoanServiceImpl implements LoanService {

    private final LoanMapper mapper;
    private final LoanRepository repository;
    private final CustomerRepository customerRepository;
    private final LoanInstallmentService loanInstallmentService;

    @Autowired
    public LoanServiceImpl(LoanMapper mapper,
                           LoanRepository repository,
                           CustomerRepository customerRepository,
                           LoanInstallmentService loanInstallmentService) {
        this.mapper = mapper;
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.loanInstallmentService = loanInstallmentService;
    }

    @Override
    public Long create(LoanDto dto) {

        //Check user
        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new EntityCouldNotBeFoundException(CustomerEntity.class.getSimpleName(), dto.getCustomerId().toString()));

        BigDecimal totalLoanAmount = dto.getLoanAmount().multiply(dto.getInterestRate().add(BigDecimal.ONE));

        if (totalLoanAmount.compareTo(customer.getCreditLimit().subtract(customer.getUsedCreditLimit())) > 0) {
            throw new IllegalArgumentException("Insufficient credit limit");
        }
        dto.setHasBeenPaid(false);
        LoanEntity entity = mapper.convertToEntity(dto);
        repository.save(entity);

        // Update customer's used credit limit
        customer.setUsedCreditLimit(customer.getUsedCreditLimit().add(totalLoanAmount));
        customerRepository.save(customer);


        // Create installments
        BigDecimal installmentAmount = totalLoanAmount.divide(BigDecimal.valueOf(dto.getNumberOfInstallment().code()),LOAN_AMOUNT_FRACTION_SIZE,RoundingMode.CEILING);

        loanInstallmentService.create(entity.getId(), installmentAmount, dto.getNumberOfInstallment().code());
        return entity.getId();
    }

    @Override
    public PaymentResultDto pay(PaymentInstallmentDto dto) {

        LoanDto loanDto = getById(dto.getLoanId());
        if (Objects.isNull(loanDto))
            throw new IllegalArgumentException("No credit found");

        LoanInstallmentQueryDto queryDto = new LoanInstallmentQueryDto();
        queryDto.setLoanId(dto.getLoanId());
        queryDto.setIsPaid(false);
        List<LoanInstallmentDto> unpaidInstallments = loanInstallmentService.queryList(queryDto);

        if (unpaidInstallments.isEmpty())
            throw new PaymentsNotMadeInFullException();

        PaymentResultDto result = loanInstallmentService.updatePayment(unpaidInstallments, dto.getAmount());

        if (result.getIsLoanFullyPaid()) {
            loanDto.setHasBeenPaid(true);
            repository.save(mapper.convertToEntity(loanDto));
        }

        BigDecimal unPaidAmount = dto.getAmount().subtract(result.getTotalPaid());
        if (unPaidAmount.compareTo(BigDecimal.ZERO) > 0)
            result.setUnpaidAmount(unPaidAmount);

        return result;
    }


    @Override
    public LoanDto getById(Long id) {
        return mapper.convertToDto(repository.findById(id).orElseThrow(() -> new EntityCouldNotBeFoundException(LoanEntity.class.getSimpleName(), id.toString())));
    }

    @Override
    public LoanDto getByUuid(String uuid) {
        return mapper.convertToDto(repository.getByUuid(uuid));
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
    public List<LoanDto> queryList(LoanQueryDto queryDto) {
        Predicate p = getQueryBuilder(queryDto);
        return mapper.convertToDtoList(repository.findAll(p, BaseSortUtils.convert(queryDto.getPageable().getSort())));
    }

    private BooleanBuilder getQueryBuilder(LoanQueryDto queryDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (queryDto != null) {
            QLoanEntity q = QLoanEntity.loanEntity;
            if (queryDto.getCustomerId() != null)
                booleanBuilder.and(q.customerId.eq(queryDto.getCustomerId()));
            if (queryDto.getHasBeenPaid() != null)
                booleanBuilder.and(q.hasBeenPaid.eq(queryDto.getHasBeenPaid()));
            if (queryDto.getGreaterThanLoanAmount() != null)
                booleanBuilder.and(q.loanAmount.goe(queryDto.getGreaterThanLoanAmount()));
            if (queryDto.getLessThanLoanAmount() != null)
                booleanBuilder.and(q.loanAmount.loe(queryDto.getLessThanLoanAmount()));
            if (queryDto.getGreaterThanCreateDate() != null)
                booleanBuilder.and(q.createdDate.goe(queryDto.getGreaterThanCreateDate()));
            if (queryDto.getLessThanCreateDate() != null)
                booleanBuilder.and(q.createdDate.loe(queryDto.getLessThanCreateDate()));
        }
        return booleanBuilder;
    }
}
