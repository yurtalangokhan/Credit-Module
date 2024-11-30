package com.inghub.credit.controller;

import com.inghub.core.common.response.BaseResponse;
import com.inghub.credit.model.dto.*;
import com.inghub.credit.service.LoanService;
import com.inghub.credit.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("${application.module.inghub.services.credit.loan.controllerPath}")
public class LoanRestController {

    private final LoanService service;

    @Autowired
    public LoanRestController(LoanService service) {
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Long> create(@Valid @RequestBody @NotNull LoanDto dto) {
        return new BaseResponse<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<PaymentResultDto> pay(@Valid @RequestBody @NotNull PaymentInstallmentDto dto) {
        return new BaseResponse<>(service.pay(dto), HttpStatus.CREATED);
    }

    @PostMapping(
            path = "/query",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<LoanDto>> queryList(@Valid @RequestBody @NotNull LoanQueryDto queryDto) {
        return new BaseResponse<>(service.queryList(queryDto), HttpStatus.OK);
    }


    @GetMapping(path = "/exists/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Boolean> existsById(@PathVariable("id") @NotNull Long id) {
        return new BaseResponse<>(service.existsById(id), HttpStatus.OK);
    }


    @GetMapping(path = "/exists/uuid/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Boolean> existsByUuid(@PathVariable("uuid") @NotBlank String uuid) {
        return new BaseResponse<>(service.existsByUuid(uuid), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<LoanDto> getById(@PathVariable("id") @NotNull Long id) {
        return new BaseResponse<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/uuid/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<LoanDto> getByUuid(@PathVariable("uuid") @NotBlank String uuid) {
        return new BaseResponse<>(service.getByUuid(uuid), HttpStatus.OK);
    }
}
