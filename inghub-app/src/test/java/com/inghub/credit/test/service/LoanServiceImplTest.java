package com.inghub.credit.test.service;

import com.inghub.credit.mapper.LoanMapper;
import com.inghub.credit.model.dto.*;
import com.inghub.credit.model.entity.CustomerEntity;
import com.inghub.credit.model.entity.LoanEntity;
import com.inghub.credit.model.enums.EnumNumberOfInstallment;
import com.inghub.credit.repositoy.CustomerRepository;
import com.inghub.credit.repositoy.LoanRepository;
import com.inghub.credit.service.LoanInstallmentService;
import com.inghub.credit.service.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoanServiceImplTest {


    @Mock
    private LoanRepository repository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private LoanMapper mapper;

    @Mock
    private LoanInstallmentService loanInstallmentService;

    @InjectMocks
    private LoanServiceImpl service;

    private LoanDto dto;
    private LoanEntity entity;


    private static LocalDateTime getDateTime(int yyyy, int MM, int dd) {
        return LocalDateTime.of(yyyy, MM, dd, 9, 9);
    }

    private void createDto() {
        LoanDto dto = new LoanDto();

        dto.setHasBeenPaid(false);
        dto.setLoanAmount(BigDecimal.valueOf(1000));
        dto.setCustomerId(1L);
        dto.setInterestRate(new BigDecimal("0.05"));
        dto.setNumberOfInstallment(EnumNumberOfInstallment.Nine);
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
        LoanEntity entity = new LoanEntity();
        entity.setHasBeenPaid(false);
        entity.setLoanAmount(BigDecimal.valueOf(1000));
        entity.setCustomerId(1L);
        entity.setInterestRate(new BigDecimal("0.05"));
        entity.setNumberOfInstallment(EnumNumberOfInstallment.Nine);
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
    void testCreateLoan() {

        CustomerEntity customer = new CustomerEntity();
        customer.setCreditLimit(new BigDecimal("2000"));
        customer.setUsedCreditLimit(new BigDecimal("500"));


        when(customerRepository.findById(dto.getCustomerId())).thenReturn(Optional.of(customer));
        when(mapper.convertToEntity(dto)).thenReturn(entity);
        when(repository.save(any(LoanEntity.class))).thenReturn(entity);

        Long result = service.create(dto);
        assertEquals(1L, result);
    }

    @Test
    void testCreateLoanInsufficientCredit() {


        CustomerEntity customer = new CustomerEntity();
        customer.setCreditLimit(new BigDecimal("2000"));
        customer.setUsedCreditLimit(new BigDecimal("1000"));

        when(customerRepository.findById(this.dto.getCustomerId())).thenReturn(Optional.of(customer));

        assertThrows(IllegalArgumentException.class, () -> service.create(this.dto));
    }

    @Test
    void testPayLoan() {
        PaymentInstallmentDto paymentDto = new PaymentInstallmentDto();
        paymentDto.setLoanId(1L);
        paymentDto.setAmount(new BigDecimal("50"));

        List<LoanInstallmentDto> unpaidInstallments = List.of(new LoanInstallmentDto());

        PaymentResultDto resultDto = new PaymentResultDto();
        resultDto.setIsLoanFullyPaid(false);
        resultDto.setTotalPaid(BigDecimal.ONE);

        when(repository.findById(paymentDto.getLoanId())).thenReturn(Optional.ofNullable(this.entity));
        when(service.getById(paymentDto.getLoanId())).thenReturn(this.dto);
        when(loanInstallmentService.queryList(any(LoanInstallmentQueryDto.class))).thenReturn(unpaidInstallments);
        when(loanInstallmentService.updatePayment(unpaidInstallments, paymentDto.getAmount())).thenReturn(resultDto);

        PaymentResultDto result = service.pay(paymentDto);
        assertNotNull(result);
        assertFalse(result.getIsLoanFullyPaid());
    }

    @Test
    void testGetLoanById() {

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(mapper.convertToDto(entity)).thenReturn(dto);

        LoanDto result = service.getById(entity.getId());
        assertEquals(dto, result);
    }
}
