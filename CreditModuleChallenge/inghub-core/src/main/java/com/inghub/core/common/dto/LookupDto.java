package com.inghub.core.common.dto;

import com.querydsl.core.annotations.QueryProjection;

public class LookupDto {

    @QueryProjection
    public LookupDto(Long id, String code) {
        this.id = id;
        Code = code;
    }

    private Long id;
    private String Code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
