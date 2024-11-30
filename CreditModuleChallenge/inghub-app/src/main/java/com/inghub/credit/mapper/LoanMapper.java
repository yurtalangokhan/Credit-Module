package com.inghub.credit.mapper;

import com.inghub.core.common.mapper.MapStructBaseClassMapper;
import com.inghub.credit.model.dto.LoanDto;
import com.inghub.credit.model.entity.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

/**
 * @author gyurtalan
 * @version 1.0
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper extends MapStructBaseClassMapper<LoanDto, LoanEntity> {


    @Override
    LoanDto convertToDto(LoanEntity entity);

    @Mapping(target = "customerEntity", ignore = true)
    @Override
    LoanEntity convertToEntity(LoanDto dto);

    @Override
    default Page<LoanDto> convertToDtoPage(Page<LoanEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.map(this::convertToDto);
    }

}
