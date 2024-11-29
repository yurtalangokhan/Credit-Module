package com.inghub.core.common.entity;

import com.inghub.core.common.constant.BaseEntityConstants;
import com.inghub.core.common.constant.EntityDataConstraints;
import com.inghub.core.common.validation.ValidationGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.io.Serial;
import java.util.Objects;

/**
 * @author gyurtalan
 * @version 1.0
 */
@Data
@MappedSuperclass
public abstract class BaseEntity extends Auditable implements IBaseEntity, Comparable<BaseEntity> {
    @Serial
    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BaseEntityConstants.SEQUENCE_GENERATOR)
    @GenericGenerator(
            name = BaseEntityConstants.SEQUENCE_GENERATOR,
            strategy = BaseEntityConstants.SEQUENCE_GENERATOR_STRATEGY,
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = SequenceStyleGenerator.INITIAL_PARAM, value = BaseEntityConstants.SEQUENCE_GENERATOR_STRATEGY_INITIAL_PARAM),
                    @org.hibernate.annotations.Parameter(
                            name = SequenceStyleGenerator.INCREMENT_PARAM, value = BaseEntityConstants.SEQUENCE_GENERATOR_STRATEGY_INCREMENT_PARAM),
                    @org.hibernate.annotations.Parameter(
                            name = SequenceStyleGenerator.OPT_PARAM, value = BaseEntityConstants.SEQUENCE_GENERATOR_STRATEGY_POOL_PARAM),
            }
    )
    //@NotAudited
    @Column(name = BaseEntityConstants.ID_FIELD_COLUMN, updatable = false, nullable = false)
    @DiffIgnore
    private Long id;

    //    @NotAudited
    @Column(name = BaseEntityConstants.UUID_FIELD_COLUMN, unique = true, updatable = false, nullable = false)
    @Size(min = EntityDataConstraints.UUID_SIZE, max = EntityDataConstraints.UUID_SIZE)
    @NotBlank(groups = ValidationGroup.Modification.class)
    @DiffIgnore
    private String uuid;

    @Override
    public Long getId() {
        return id;
    }

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


    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(BaseEntity entity) {
        return Integer.compare(this.hashCode(), entity.hashCode());
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
        BaseEntity that = (BaseEntity) o;
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