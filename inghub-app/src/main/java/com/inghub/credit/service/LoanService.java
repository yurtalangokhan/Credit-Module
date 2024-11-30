package com.inghub.credit.service;

import com.inghub.credit.model.dto.LoanDto;
import com.inghub.credit.model.dto.LoanQueryDto;
import com.inghub.credit.model.dto.PaymentInstallmentDto;
import com.inghub.credit.model.dto.PaymentResultDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface LoanService {

    Long create(@Valid @NotNull LoanDto dto);

    PaymentResultDto pay(@Valid @NotNull PaymentInstallmentDto dto);

    LoanDto getById(@NotNull Long id);

    LoanDto getByUuid(@NotBlank String uuid);

    boolean existsById(@NotNull Long id);

    boolean existsByUuid(@NotBlank String uuid);

    List<LoanDto> queryList(@Valid @NotNull LoanQueryDto queryDto);

}
