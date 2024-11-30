package com.inghub.credit.test.service;

import com.inghub.core.data.exception.EntityCouldNotBeFoundException;
import com.inghub.credit.mapper.CustomerMapper;
import com.inghub.credit.model.dto.CustomerDto;
import com.inghub.credit.model.entity.CustomerEntity;
import com.inghub.credit.repositoy.CustomerRepository;
import com.inghub.credit.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerServiceImpl service;

    private CustomerDto dto;
    private CustomerEntity entity;

    private static LocalDateTime getDateTime(int yyyy, int MM, int dd) {
        return LocalDateTime.of(yyyy, MM, dd, 9, 9);
    }

    private void createDto() {
        CustomerDto dto = new CustomerDto();

        dto.setCreditLimit(BigDecimal.valueOf(10));
        dto.setName("gokhan");
        dto.setSurname("yurtalan");
        dto.setUsedCreditLimit(BigDecimal.ZERO);

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
        CustomerEntity entity = new CustomerEntity();

        entity.setCreditLimit(BigDecimal.valueOf(10));
        entity.setName("gokhan");
        entity.setSurname("yurtalan");
        entity.setUsedCreditLimit(BigDecimal.ZERO);

        entity.setId(1L);
        String idStr = entity.getId().toString();
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
    void testCreateCustomer() {

        when(mapper.convertToEntity(any(CustomerDto.class))).thenReturn(this.entity);
        when(repository.save(any(CustomerEntity.class))).thenReturn(this.entity);

        Long createdId = service.create(this.dto);
        assertNotNull(createdId);
        assertEquals(1L, createdId);
    }

    @Test
    void testUpdateCustomer() {

        when(mapper.convertToEntity(this.dto)).thenReturn(this.entity);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.update(entity)).thenReturn(this.entity);
        when(mapper.convertToDto(entity)).thenReturn(this.dto);

        CustomerDto updatedDto = service.update(this.dto);
        assertNotNull(updatedDto);
        assertEquals(1L, updatedDto.getId());
    }

    @Test
    void testDeleteCustomer() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        assertDoesNotThrow(() -> service.delete(id));
    }

    @Test
    void testDeleteCustomerNotFound() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(EntityCouldNotBeFoundException.class, () -> service.delete(id));
    }

    @Test
    void testGetCustomerByIdNotFound() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityCouldNotBeFoundException.class, () -> {
            service.getById(1L);
        });

        String expectedMessage = "1, CustomerEntity ENTITY_COULD_NOT_BE_FOUND";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}