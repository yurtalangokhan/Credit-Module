package com.inghub.core.common.entity;

import com.inghub.core.common.constant.BaseEntityConstants;
import com.inghub.core.common.constant.EntityDataConstraints;
import com.inghub.core.common.listener.EntityListener;
import com.inghub.core.common.validation.ValidationGroup;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author gyurtalan
 * @version 1.0
 */
@Data
@MappedSuperclass
@EntityListeners(value = {EntityListener.class, AuditingEntityListener.class})
public class Auditable {
    @Column(name = BaseEntityConstants.CREATED_BY_FIELD_COLUMN, updatable = false, nullable = false)
    @Size(max = EntityDataConstraints.CREATED_BY_SIZE)
    @NotBlank(groups = ValidationGroup.Modification.class)
    @CreatedBy
    @DiffIgnore
    private String createdBy;

    @Column(name = BaseEntityConstants.UPDATED_BY_FIELD_COLUMN, nullable = false)
    @Size(max = EntityDataConstraints.UPDATED_BY_SIZE)
    @LastModifiedBy
    @DiffIgnore
    private String updatedBy;

    @Column(name = BaseEntityConstants.CREATED_DATE_FIELD_COLUMN, updatable = false, nullable = false)
    @NotNull(groups = ValidationGroup.Modification.class)
    @CreationTimestamp
    @CreatedDate
    @DiffIgnore
    private LocalDateTime createdDate;

    @Column(name = BaseEntityConstants.UPDATED_DATE_FIELD_COLUMN, nullable = false)
    @NotNull(groups = ValidationGroup.Modification.class)
    @UpdateTimestamp
    @LastModifiedDate
    @DiffIgnore
    private LocalDateTime updatedDate;

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
}
