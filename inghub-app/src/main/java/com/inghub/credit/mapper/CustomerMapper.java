package com.inghub.credit.mapper;

import com.inghub.core.common.mapper.MapStructBaseClassMapper;
import com.inghub.credit.model.dto.CustomerDto;
import com.inghub.credit.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper extends MapStructBaseClassMapper<CustomerDto, CustomerEntity> {

    @Override
    CustomerEntity convertToEntity(CustomerDto dto);

    @Override
    CustomerDto convertToDto(CustomerEntity entity);


    @Override
    default Page<CustomerDto> convertToDtoPage(Page<CustomerEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.map(this::convertToDto);
    }
}
