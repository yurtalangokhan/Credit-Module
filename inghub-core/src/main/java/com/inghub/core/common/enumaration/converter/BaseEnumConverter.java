package com.inghub.core.common.enumaration.converter;

import com.inghub.core.common.enumaration.EnumConverter;
import jakarta.persistence.AttributeConverter;
import jakarta.validation.constraints.NotNull;
import org.hibernate.ObjectNotFoundException;

import java.util.Objects;

/**
 * @author gyurtalan
 * @version 1.0
 */

public abstract class BaseEnumConverter<T, EC extends EnumConverter<T>> implements AttributeConverter<EC, T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public T convertToDatabaseColumn(@NotNull EC enumerationConverter) {
        return Objects.nonNull(enumerationConverter) ? enumerationConverter.toDbValue() : null;
    }

    public EC notNull(@NotNull EC enumerationConverter) {
        return notNull(enumerationConverter, null);
    }

    public EC notNull(EC enumerationConverter, T value) {
        if (enumerationConverter == null) {
            throw new ObjectNotFoundException(enumerationConverter.getClass(), enumerationConverter.getClass().getName());
        }
        return enumerationConverter;
    }

    public EC withDefault(EC enumerationConverter, EC defaultEnumerationConverter) {
        return Objects.nonNull(enumerationConverter) ? enumerationConverter : defaultEnumerationConverter;
    }

}
