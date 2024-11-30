package com.inghub.credit.repositoy;

import com.inghub.core.data.jpa.repository.BaseRepository;
import com.inghub.credit.model.entity.LoanInstallmentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanInstallmentRepository extends BaseRepository<LoanInstallmentEntity> {
}
