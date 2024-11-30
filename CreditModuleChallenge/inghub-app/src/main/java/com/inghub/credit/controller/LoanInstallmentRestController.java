package com.inghub.credit.controller;

import com.inghub.core.common.response.BaseResponse;
import com.inghub.credit.model.dto.CustomerDto;
import com.inghub.credit.model.dto.CustomerQueryDto;
import com.inghub.credit.model.dto.LoanInstallmentDto;
import com.inghub.credit.model.dto.LoanInstallmentQueryDto;
import com.inghub.credit.service.LoanInstallmentService;
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
@RequestMapping("${application.module.inghub.services.credit.loanInstallment.controllerPath}")
public class LoanInstallmentRestController {

    private final LoanInstallmentService service;

    @Autowired
    public LoanInstallmentRestController(LoanInstallmentService service) {
        this.service = service;
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
    public BaseResponse<LoanInstallmentDto> getById(@PathVariable("id") @NotNull Long id) {
        return new BaseResponse<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/uuid/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<LoanInstallmentDto> getByUuid(@PathVariable("uuid") @NotBlank String uuid) {
        return new BaseResponse<>(service.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping(
            path = "/query",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<LoanInstallmentDto>> queryList(@Valid @RequestBody @NotNull LoanInstallmentQueryDto queryDto) {
        return new BaseResponse<>(service.queryList(queryDto), HttpStatus.OK);
    }

    @PostMapping(
            path = "/page",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Page<LoanInstallmentDto>> queryPage(@Valid @RequestBody @NotNull LoanInstallmentQueryDto queryDto) {
        return new BaseResponse<>(service.queryPage(queryDto), HttpStatus.OK);
    }


}
