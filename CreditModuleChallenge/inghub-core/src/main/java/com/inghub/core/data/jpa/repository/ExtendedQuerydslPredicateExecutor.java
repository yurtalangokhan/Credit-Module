package com.inghub.core.data.jpa.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * @author gyurtalan
 * @version 1.0
 */
@NoRepositoryBean
public interface ExtendedQuerydslPredicateExecutor<BE> extends QuerydslPredicateExecutor<BE> {
    /**
     * Extended Querydsl Predicate Executor
     */
    Optional<BE> findOne(@NonNull Predicate predicate);

    <T> Optional<T> findOne(@NonNull JPQLQuery<T> jpqlQuery);

    BE getByUuid(@NonNull String uuid);

    Optional<BE> findByUuid(@NonNull String uuid);

    List<BE> findAllByUuid(@NonNull Iterable<String> uuids);

    boolean existsByUuid(@NonNull String uuid);

    List<BE> findAll(@NonNull Predicate predicate);

    List<BE> findAll(@NonNull Predicate predicate, @NonNull Sort sort);

    List<BE> findAll(@NonNull Predicate predicate, @NonNull OrderSpecifier<?>... orders);

    List<BE> findAll(@NonNull OrderSpecifier<?>... orders);

    List<BE> findAll(@NonNull Sort sort);

    Page<BE> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);

    <BE> Page<BE> findAll(JPAQuery<BE> jpaQuery, @NonNull Pageable pageable);

    <T> List<T> findAll(@NonNull JPQLQuery<T> query);

    <T> Page<T> findAll(@NonNull JPQLQuery<T> query, @NonNull Pageable pageable);
}
