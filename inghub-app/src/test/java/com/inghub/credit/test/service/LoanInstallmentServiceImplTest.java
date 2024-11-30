package com.inghub.credit.test.service;

import com.inghub.core.data.exception.EntityCouldNotBeFoundException;
import com.inghub.credit.mapper.LoanInstallmentMapper;
import com.inghub.credit.model.dto.LoanInstallmentDto;
import com.inghub.credit.model.dto.PaymentResultDto;
import com.inghub.credit.model.entity.LoanInstallmentEntity;
import com.inghub.credit.model.enums.EnumNumberOfInstallment;
import com.inghub.credit.repositoy.LoanInstallmentRepository;
import com.inghub.credit.service.LoanInstallmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoanInstallmentServiceImplTest {


    @Mock
    private LoanInstallmentRepository repository;

    @Mock
    private LoanInstallmentMapper mapper;

    @InjectMocks
    private LoanInstallmentServiceImpl service;

    private LoanInstallmentDto dto;
    private LoanInstallmentEntity entity;


    private static LocalDateTime getDateTime(int yyyy, int MM, int dd) {
        return LocalDateTime.of(yyyy, MM, dd, 9, 9);
    }

    private void createDto() {
        LoanInstallmentDto dto = new LoanInstallmentDto();

        dto.setHasBeenPaid(false);
        dto.setLoanId(1L);
        dto.setAmount(new BigDecimal("500.00"));
        dto.setPaidAmount(new BigDecimal("0.00"));
        dto.setDueDate(getDateTime(2025, 1, 3));
        dto.setHasBeenPaid(false);
        dto.setId(1L);
        String idStr = dto.getId().toString();
        dto.setCreatedBy("creator_" + idStr);
        dto.setUpdatedBy("updater_" + idStr);
        dto.setCreatedDate(getDateTime(2020, 10, 3));
        dto.setUpdatedDate(getDateTime(2020, 10, 9));
        dto.setUuid("uuid_" + idStr);
        this.dto = dto;
    }

    private void createEntity() {
        LoanInstallmentEntity entity = new LoanInstallmentEntity();
        entity.setHasBeenPaid(false);
        entity.setHasBeenPaid(false);
        entity.setLoanId(1L);
        entity.setAmount(new BigDecimal("500.00"));
        entity.setPaidAmount(new BigDecimal("0.00"));
        entity.setDueDate(getDateTime(2025, 1, 3));
        entity.setHasBeenPaid(false);
        entity.setId(1L);
        String idStr = dto.getId().toString();
        entity.setCreatedBy("creator_" + idStr);
        entity.setUpdatedBy("updater_" + idStr);
        entity.setCreatedDate(getDateTime(2020, 10, 3));
        entity.setUpdatedDate(getDateTime(2020, 10, 9));
        entity.setUuid("uuid_" + idStr);
        this.entity = entity;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createDto();
        createEntity();
    }

    @Test
    void testCreateInstallments() {
        Long loanId = 1L;
        BigDecimal installmentAmount = new BigDecimal("500.00");
        int count = EnumNumberOfInstallment.Six.code();

        when(repository.save(any(LoanInstallmentEntity.class))).thenReturn(this.entity);

        assertDoesNotThrow(() -> service.create(loanId, installmentAmount, count));
    }

    @Test
    void testUpdatePayment() {

        BigDecimal paymentAmount = new BigDecimal("500");

        when(mapper.convertToEntity(any(LoanInstallmentDto.class))).thenReturn(new LoanInstallmentEntity());
        when(repository.save(any(LoanInstallmentEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PaymentResultDto result = service.updatePayment(Arrays.asList(dto), paymentAmount);
        assertEquals(1, result.getInstallmentsPaidCount());
        assertTrue(result.getIsLoanFullyPaid());
        // assertEquals(new BigDecimal("483.500"), paymentAmount.compareTo(result.getTotalPaid()));
    }

    @Test
    void testUpdatePaymentPartial() {

        BigDecimal paymentAmount = new BigDecimal("250");

        PaymentResultDto result = service.updatePayment(Arrays.asList(this.dto), paymentAmount);
        assertEquals(0, result.getInstallmentsPaidCount());
        assertFalse(result.getIsLoanFullyPaid());
    }

    @Test
    void testGetByIdFound() {
        Long id = 1L;
        LoanInstallmentEntity entity = new LoanInstallmentEntity();
        LoanInstallmentDto dto = new LoanInstallmentDto();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.convertToDto(entity)).thenReturn(dto);

        LoanInstallmentDto result = service.getById(id);
        assertEquals(dto, result);
    }

    @Test
    void testGetByIdNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityCouldNotBeFoundException.class, () -> service.getById(id));
    }

    @Test
    void testExistsById() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        assertTrue(service.existsById(id));
    }

}
