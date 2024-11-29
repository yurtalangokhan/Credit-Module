package com.inghub.core.common.entity;

import java.io.Serializable;

/**
 * @author gyurtalan
 * @version 1.0
 */
public interface IBaseEntity extends Serializable {
    Long getId();

    void setId(Long id);

    String getUuid();

    void setUuid(String uuid);
}
