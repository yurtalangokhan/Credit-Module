package com.inghub.credit.service;

import com.inghub.credit.model.dto.LoanInstallmentDto;
import com.inghub.credit.model.dto.LoanInstallmentQueryDto;
import com.inghub.credit.model.dto.PaymentResultDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

@Validated
public interface LoanInstallmentService {

    void create(@Valid @NotNull Long loanId, @NotNull BigDecimal installmentAmount, int count);

    PaymentResultDto updatePayment(@NotNull List<LoanInstallmentDto> installments, BigDecimal amount);

    LoanInstallmentDto getById(@NotNull Long id);

    LoanInstallmentDto getByUuid(@NotBlank String uuid);

    boolean existsById(@NotNull Long id);

    boolean existsByUuid(@NotBlank String uuid);

    List<LoanInstallmentDto> queryList(@Valid @NotNull LoanInstallmentQueryDto queryDto);

    Page<LoanInstallmentDto> queryPage(@Valid @NotNull LoanInstallmentQueryDto queryDto);
}
