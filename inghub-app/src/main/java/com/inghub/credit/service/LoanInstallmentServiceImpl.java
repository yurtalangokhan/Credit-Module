package com.inghub.credit.service;

import com.inghub.core.common.pagable.BasePageableUtils;
import com.inghub.core.common.pagable.BaseSortUtils;
import com.inghub.core.data.exception.EntityCouldNotBeFoundException;
import com.inghub.core.exceptionHandling.BaseCheckedException;
import com.inghub.credit.mapper.LoanInstallmentMapper;
import com.inghub.credit.model.dto.LoanInstallmentDto;
import com.inghub.credit.model.dto.LoanInstallmentQueryDto;
import com.inghub.credit.model.dto.PaymentResultDto;
import com.inghub.credit.model.entity.LoanInstallmentEntity;
import com.inghub.credit.model.entity.QLoanInstallmentEntity;
import com.inghub.credit.repositoy.LoanInstallmentRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.inghub.credit.model.constraint.LoanInstallmentConstraints.AMOUNT_FRACTION_SIZE;

@Service
@Transactional(rollbackFor = BaseCheckedException.class)
public class LoanInstallmentServiceImpl implements LoanInstallmentService {

    private final LoanInstallmentMapper mapper;
    private final LoanInstallmentRepository repository;

    @Autowired
    public LoanInstallmentServiceImpl(LoanInstallmentMapper mapper, LoanInstallmentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void create(Long loanId, BigDecimal installmentAmount, int count) {

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime dueDate = today.withMonth(today.getMonthValue() + 1).withDayOfMonth(1);
        for (int i = 0; i < count; i++) {
            LoanInstallmentDto dto = new LoanInstallmentDto();
            dto.setLoanId(loanId);
            dto.setAmount(installmentAmount);
            dto.setDueDate(dueDate.plusMonths(i));
            dto.setHasBeenPaid(false);
            repository.save(mapper.convertToEntity(dto));
        }
    }


    @Override
    public PaymentResultDto updatePayment(List<LoanInstallmentDto> unpaidInstallments, BigDecimal amount) {

        BigDecimal totalPaid = BigDecimal.ZERO;
        int installmentsPaidCount = 0;
        boolean isLoanFullyPaid = true;

        // Filter and sort installments by due date, ignoring those that cannot yet be paid.
        List<LoanInstallmentDto> payableInstallments = unpaidInstallments.stream()
                .filter(installment -> installment.getDueDate().isBefore(LocalDateTime.now().plusMonths(3)))
                .sorted(Comparator.comparing(LoanInstallmentDto::getDueDate))
                .collect(Collectors.toList());

        for (LoanInstallmentDto installment : payableInstallments) {
            if (amount.compareTo(installment.getAmount()) >= 0) {
                // Calculate days between payment date and due date
                long daysBetween = ChronoUnit.DAYS.between(LocalDateTime.now(), installment.getDueDate());

                if (daysBetween > 0) {
                    // Early payment, apply reward
                    BigDecimal reward = installment.getAmount().multiply(BigDecimal.valueOf(0.001 * daysBetween)).setScale(AMOUNT_FRACTION_SIZE, RoundingMode.CEILING);
                    installment.setPaidAmount(installment.getAmount().subtract(reward));
                } else if (daysBetween < 0) {
                    // Late payment, apply penalty0.001 * Math.abs(daysBetween)
                    BigDecimal penalty = installment.getAmount().multiply(BigDecimal.valueOf(0.001 * Math.abs(daysBetween))).setScale(AMOUNT_FRACTION_SIZE, RoundingMode.CEILING);
                    ;
                    installment.setPaidAmount(installment.getAmount().add(penalty));
                } else {
                    // On-time payment
                    installment.setPaidAmount(installment.getAmount());
                }

                installment.setPaymentDate(LocalDateTime.now());
                installment.setHasBeenPaid(true);
                repository.save(mapper.convertToEntity(installment));

                totalPaid = totalPaid.add(installment.getPaidAmount());
                amount = amount.subtract(installment.getPaidAmount());
                installmentsPaidCount++;
            } else
                isLoanFullyPaid = false;

            if (amount.compareTo(installment.getAmount()) < 0) {
                break;
            }
        }

        PaymentResultDto result = new PaymentResultDto();
        result.setInstallmentsPaidCount(installmentsPaidCount);
        result.setIsLoanFullyPaid(isLoanFullyPaid);
        result.setTotalPaid(totalPaid);
        return result;

    }

    @Override
    public LoanInstallmentDto getById(Long id) {
        return mapper.convertToDto(repository.findById(id).orElseThrow(() -> new EntityCouldNotBeFoundException(LoanInstallmentEntity.class.getSimpleName(), id.toString())));
    }

    @Override
    public LoanInstallmentDto getByUuid(String uuid) {
        return mapper.convertToDto(repository.getByUuid(uuid));
    }


    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByUuid(String uuid) {
        return repository.existsByUuid(uuid);
    }

    @Override
    public List<LoanInstallmentDto> queryList(LoanInstallmentQueryDto queryDto) {
        Predicate p = getQueryBuilder(queryDto);
        return mapper.convertToDtoList(repository.findAll(p, BaseSortUtils.convert(queryDto.getPageable().getSort())));
    }

    @Override
    public Page<LoanInstallmentDto> queryPage(LoanInstallmentQueryDto queryDto) {
        Predicate predicate = getQueryBuilder(queryDto);
        Page<LoanInstallmentEntity> pageList = repository.findAll(predicate, BasePageableUtils.convert(queryDto.getPageable()));

        return mapper.convertToDtoPage(pageList);
    }

    private BooleanBuilder getQueryBuilder(LoanInstallmentQueryDto queryDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (queryDto != null) {
            QLoanInstallmentEntity q = QLoanInstallmentEntity.loanInstallmentEntity;

            if (queryDto.getLoanId() != null)
                booleanBuilder.and(q.loanId.eq(queryDto.getLoanId()));
            if (queryDto.getIsPaid() != null)
                booleanBuilder.and(q.hasBeenPaid.eq(queryDto.getIsPaid()));
            if (queryDto.getGreaterThanDueDate() != null)
                booleanBuilder.and(q.dueDate.goe(queryDto.getGreaterThanDueDate()));
            if (queryDto.getLessThanDueDate() != null)
                booleanBuilder.and(q.dueDate.loe(queryDto.getLessThanDueDate()));
            if (queryDto.getGreaterThanPaymentDate() != null)
                booleanBuilder.and(q.paymentDate.goe(queryDto.getGreaterThanPaymentDate()));
            if (queryDto.getLessThanPaymentDate() != null)
                booleanBuilder.and(q.paymentDate.loe(queryDto.getLessThanPaymentDate()));
        }
        return booleanBuilder;
    }


}