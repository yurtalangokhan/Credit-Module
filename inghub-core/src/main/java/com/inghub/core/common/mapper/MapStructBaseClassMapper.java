package com.inghub.core.common.mapper;

import com.inghub.core.common.dto.BaseDto;
import com.inghub.core.common.entity.BaseEntity;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * @param <OD> the dto type
 * @param <BE> the entity type
 */
public interface MapStructBaseClassMapper<OD extends BaseDto, BE extends BaseEntity> {

    /**
     * Convert to dto.
     *
     * @param entity the entity
     * @return the dto
     */
    OD convertToDto(BE entity);

    /**
     * Convert to dto list.
     *
     * @param entities the entities
     * @return the list
     */
    List<OD> convertToDtoList(List<BE> entities);

    /**
     * Convert to dto set.
     *
     * @param entities the entities
     * @return the set
     */
    Set<OD> convertToDtoSet(Set<BE> entities);

    /**
     * Convert to dto page.
     *
     * @param entities
     * @return to page
     */
    Page<OD> convertToDtoPage(Page<BE> entities);

    /**
     * Convert to entity.
     *
     * @param dto the dto
     * @return the entity
     */
    BE convertToEntity(OD dto);

    /**
     * Map to entity.
     *
     * @param dto    the dto
     * @param entity the entity
     */
    void mapToEntity(OD dto, @MappingTarget BE entity);

    /**
     * Convert to entity list.
     *
     * @param dto the dto
     * @return the list
     */
    List<BE> convertToEntityList(List<OD> dto);

    /**
     * Convert to entity set.
     *
     * @param dto the dto
     * @return the set
     */
    Set<BE> convertToEntitySet(Set<OD> dto);

}