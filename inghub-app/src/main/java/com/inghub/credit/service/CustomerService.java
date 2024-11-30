package com.inghub.credit.service;

import com.inghub.credit.model.dto.CustomerDto;
import com.inghub.credit.model.dto.CustomerQueryDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author gyurtalan
 * @version 1.0
 */
@Validated
public interface CustomerService {

    Long create(@Valid @NotNull CustomerDto dto);

    CustomerDto update(@Valid @NotNull CustomerDto dto);

    void delete(@NotNull Long id);

    CustomerDto getById(@NotNull Long id);

    CustomerDto getByUuid(@NotBlank String uuid);

    boolean exists(@NotNull CustomerQueryDto queryDto);

    boolean existsById(@NotNull Long id);

    boolean existsByUuid(@NotBlank String uuid);

    List<CustomerDto> queryList(@Valid @NotNull CustomerQueryDto queryDto);

    Page<CustomerDto> queryPage(@Valid @NotNull CustomerQueryDto queryDto);


}
