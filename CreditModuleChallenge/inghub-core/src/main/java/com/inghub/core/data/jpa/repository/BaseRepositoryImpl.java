package com.inghub.core.data.jpa.repository;

import com.inghub.core.common.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.querydsl.EntityPathResolver;

/**
 * @author gyurtalan
 * @version 1.0
 */

public class BaseRepositoryImpl<BE extends BaseEntity>
        extends JpaRepositoryImpl<BE>
        implements BaseRepository<BE> {
    public BaseRepositoryImpl(JpaEntityInformation<BE, Long> entityInformation,
                              EntityManager entityManager,
                              EntityPathResolver entityPathResolver) {
        super(entityInformation, entityManager, entityPathResolver);
    }
}
