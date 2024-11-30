package com.inghub.credit.controller;

import com.inghub.core.common.response.BaseResponse;
import com.inghub.credit.model.dto.CustomerDto;
import com.inghub.credit.model.dto.CustomerQueryDto;
import com.inghub.credit.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyurtalan
 * @version 1.0
 */
@RestController
@RequestMapping("${application.module.inghub.services.credit.customer.controllerPath}")
public class CustomerRestController {

    private final CustomerService service;

    @Autowired
    public CustomerRestController(CustomerService service) {
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Long> create(@Valid @RequestBody @NotNull CustomerDto dto) {
        return new BaseResponse<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CustomerDto> update(@Valid @RequestBody @NotNull CustomerDto dto) {
        return new BaseResponse<>(service.update(dto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") @NotNull Long id) {
        service.delete(id);
    }

    @PostMapping(
            path = "/query",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<CustomerDto>> queryList(@Valid @RequestBody @NotNull CustomerQueryDto queryDto) {
        return new BaseResponse<>(service.queryList(queryDto), HttpStatus.OK);
    }

    @PostMapping(
            path = "/page",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Page<CustomerDto>> queryPage(@Valid @RequestBody @NotNull CustomerQueryDto queryDto) {
        return new BaseResponse<>(service.queryPage(queryDto), HttpStatus.OK);
    }


    @PostMapping(path = "/exists", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Boolean> exists(@Valid @RequestBody @NotNull CustomerQueryDto queryDto) {
        return new BaseResponse<>(service.exists(queryDto), HttpStatus.OK);
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
    public BaseResponse<CustomerDto> getById(@PathVariable("id") @NotNull Long id) {
        return new BaseResponse<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/uuid/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<CustomerDto> getByUuid(@PathVariable("uuid") @NotBlank String uuid) {
        return new BaseResponse<>(service.getByUuid(uuid), HttpStatus.OK);
    }
}
