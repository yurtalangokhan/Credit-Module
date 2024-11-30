package com.inghub.core.common.listener;

import com.github.f4b6a3.uuid.UuidCreator;
import com.inghub.core.common.entity.BaseEntity;
import jakarta.persistence.PrePersist;


/**
 * @author gyurtalan
 * @version 1.0
 */
public class EntityListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof BaseEntity simpleEntity) {
            simpleEntity.setUuid(UuidCreator.getTimeBased().toString());
        }
    }
}