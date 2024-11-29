package com.inghub.core.common.dto;

import com.inghub.core.common.constant.EntityDataConstraints;
import com.inghub.core.common.validation.ValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author gyurtalan
 * @version 1.0
 */

public abstract class BaseDto implements IBaseDto<Long>, Comparable<BaseDto> {

    @Serial
    private static final long serialVersionUID = -1L;

    @NotNull(groups = ValidationGroup.Modification.class)
    private Long id;

    @Size(min = EntityDataConstraints.UUID_SIZE, max = EntityDataConstraints.UUID_SIZE)
    @NotBlank(groups = ValidationGroup.Modification.class)
    private String uuid;

    @NotNull(groups = ValidationGroup.Modification.class)
    private LocalDateTime createdDate;

    @NotNull(groups = ValidationGroup.Modification.class)
    private LocalDateTime updatedDate;

    @Size(max = EntityDataConstraints.CREATED_BY_SIZE)
    @NotBlank(groups = ValidationGroup.Modification.class)
    private String createdBy;

    @Size(max = EntityDataConstraints.UPDATED_BY_SIZE)
    private String updatedBy;


    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUuid() {
        return uuid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int compareTo(BaseDto o) {
        return Integer.compare(this.hashCode(), o.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (Objects.isNull(o) || !this.getClass().equals(o.getClass())) {
            return false;
        }
        BaseDto that = (BaseDto) o;
        if (Objects.nonNull(this.getId()) && Objects.nonNull(this.getUuid()) && Objects.nonNull(that.getId()) && Objects.nonNull(that.getUuid())) {
            return Objects.equals(this.getId(), that.getId()) && Objects.equals(this.getUuid(), that.getUuid());
        }
        return Objects.equals(this.hashCode(), that.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid());
    }
}