package com.inghub.core.common.dto;

import java.io.Serializable;

/**
 * @author gyurtalan
 * @version 1.0
 */
public interface IBaseDto<ID extends Number> extends Serializable {
    ID getId();

    void setId(ID id);

    String getUuid();

    void setUuid(String uuid);
}
