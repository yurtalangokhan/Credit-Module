package com.inghub.core.data.jpa.repository;

import com.inghub.core.common.entity.BaseEntity;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author gyurtalan
 * @version 1.0
 */
@NoRepositoryBean
public interface JpaRepository<BE extends BaseEntity> extends org.springframework.data.jpa.repository.JpaRepository<BE, Long>, ExtendedQuerydslPredicateExecutor<BE> {

    BE update(@NonNull BE entity);

    void updateAll(@NonNull List<BE> entities);

    void deleteByUuid(@NonNull String uuid);

    EntityManager getEntityManager();

    JPAQuery<BE> getJpaQuery();

}
