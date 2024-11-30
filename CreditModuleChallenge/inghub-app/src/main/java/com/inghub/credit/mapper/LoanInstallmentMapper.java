package com.inghub.credit.mapper;

import com.inghub.core.common.mapper.MapStructBaseClassMapper;
import com.inghub.credit.model.dto.LoanDto;
import com.inghub.credit.model.dto.LoanInstallmentDto;
import com.inghub.credit.model.entity.LoanEntity;
import com.inghub.credit.model.entity.LoanInstallmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanInstallmentMapper extends MapStructBaseClassMapper<LoanInstallmentDto, LoanInstallmentEntity> {


    @Override
    LoanInstallmentDto convertToDto(LoanInstallmentEntity entity);

    @Mapping(target = "loanEntity", ignore = true)
    @Override
    LoanInstallmentEntity convertToEntity(LoanInstallmentDto dto);

    @Override
    default Page<LoanInstallmentDto> convertToDtoPage(Page<LoanInstallmentEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.map(this::convertToDto);
    }

}