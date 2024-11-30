package com.inghub.credit.repositoy;

import com.inghub.core.data.jpa.repository.BaseRepository;
import com.inghub.credit.model.entity.LoanEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends BaseRepository<LoanEntity> {
}
