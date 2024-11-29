package com.inghub.core.data.jpa.repository;

import com.inghub.core.common.constant.BaseEntityConstants;
import com.inghub.core.common.entity.BaseEntity;
import com.inghub.core.data.exception.EntityInvalidUpdateException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author gyurtalan
 * @version 1.0
 */
@Transactional(rollbackFor = {Exception.class})
public class JpaRepositoryImpl<BE extends BaseEntity>
        extends SimpleJpaRepository<BE, Long>
        implements JpaRepository<BE> {


    private final JpaEntityInformation<BE, Long> entityInformation;
    private final Class<BE> entityClass;
    private final PathBuilder<BE> pathBuilder;
    private final EntityManager entityManager;
    private final QuerydslPredicateExecutor<BE> querydslPredicateExecutor;
    private final Querydsl querydsl;

    public JpaRepositoryImpl(JpaEntityInformation<BE, Long> entityInformation,
                             EntityManager entityManager) {
        this(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE);
    }

    public JpaRepositoryImpl(JpaEntityInformation<BE, Long> entityInformation,
                             EntityManager entityManager,
                             EntityPathResolver entityPathResolver) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
        this.entityClass = entityInformation.getJavaType();
        this.pathBuilder = new PathBuilder<>(this.entityClass, entityPathResolver.createPath(this.entityClass).getMetadata());
        this.querydslPredicateExecutor = new QuerydslJpaPredicateExecutor<>(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE, null);
        this.querydsl = new Querydsl(entityManager, pathBuilder);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public JPAQuery<BE> getJpaQuery() {
        return new JPAQuery<>(entityManager, JPQLTemplates.DEFAULT);
    }

    @Override
    public BE update(BE entity) {
        if (entity == null) {
            throw new ObjectNotFoundException(entityClass, entityInformation.getEntityName());
        }
        if (entityInformation.isNew(entity)) {
            throw new EntityInvalidUpdateException(entity.getId().toString());
        }

        final BE result = super.save(entity);
        return findById(result.getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format(entityInformation.getJavaType().getName(), result.getId().toString()))
        );
    }

    @Override
    public void updateAll(@NonNull List<BE> entities) {
        for (final BE entity : entities) {
            update(entity);
        }
    }

    @Override
    public void deleteByUuid(@NonNull String uuid) {
        if (Strings.isBlank(uuid)) {
            throw new ObjectNotFoundException(entityClass, entityInformation.getEntityName());
        }
        final Optional<BE> result = findByUuid(uuid);
        final BE entity = result.orElseThrow(() ->
                new EntityNotFoundException(String.format("Entity: %s, uuid: %s", entityInformation.getJavaType().getName(), uuid))
        );
        delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BE> findOne(@NonNull Predicate predicate) {
        return querydslPredicateExecutor.findOne(predicate);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> Optional<T> findOne(@NonNull JPQLQuery<T> jpqlQuery) {
        return Optional.ofNullable(jpqlQuery.fetchFirst());
    }

    @Override
    @Transactional(readOnly = true)
    public BE getByUuid(@NonNull String uuid) {
        if (Strings.isBlank(uuid)) {
            throw new ObjectNotFoundException(entityClass, entityInformation.getEntityName());
        }
        return findOne(generateUuidPredicate(uuid)).orElse(null);
    }

    private BooleanBuilder generateUuidPredicate(@NotNull String uuid) {
        return new BooleanBuilder(pathBuilder.get(BaseEntityConstants.UUID_FIELD).eq(uuid));
    }

    private BooleanBuilder generateUuidsPredicate(@NotNull Iterable<String> uuids) {
        if (uuids instanceof List list) {
            return new BooleanBuilder(pathBuilder.get(BaseEntityConstants.UUID_FIELD).in(list));
        } else {
            return new BooleanBuilder(pathBuilder.get(BaseEntityConstants.UUID_FIELD).in(uuids));
        }
    }

    private <P> Page<P> getPage(JPQLQuery<P> query, JPQLQuery<?> countQuery, Pageable pageable) {
        JPQLQuery<P> paginatedQuery = querydsl.applyPagination(pageable, query);
        return PageableExecutionUtils.getPage(paginatedQuery.fetch(), pageable, countQuery::fetchCount);
    }

    @Override
    @NonNull
    @Transactional(readOnly = true)
    public Optional<BE> findById(@NonNull Long id) {
        return super.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BE> findByUuid(@NonNull String uuid) {
        if (Strings.isBlank(uuid)) {
            throw new ObjectNotFoundException(entityClass, entityInformation.getEntityName());
        }
        return querydslPredicateExecutor.findOne(generateUuidPredicate(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BE> findAllByUuid(@NonNull Iterable<String> uuids) {
        return findAll(generateUuidsPredicate(uuids));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUuid(@NonNull String uuid) {
        if (Strings.isBlank(uuid)) {
            throw new ObjectNotFoundException(entityClass, entityInformation.getEntityName());
        }
        return exists(generateUuidPredicate(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BE> findAll(@NonNull Predicate predicate) {
        return (List<BE>) querydslPredicateExecutor.findAll(predicate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BE> findAll(@NonNull Predicate predicate, @NonNull Sort sort) {
        return (List<BE>) querydslPredicateExecutor.findAll(predicate, sort);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BE> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        return (List<BE>) querydslPredicateExecutor.findAll(predicate, orders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BE> findAll(OrderSpecifier<?>... orders) {
        return (List<BE>) querydslPredicateExecutor.findAll(orders);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<BE> findAll(Predicate predicate, Pageable pageable) {
        return querydslPredicateExecutor.findAll(predicate, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Predicate predicate) {
        return querydslPredicateExecutor.count(predicate);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Predicate predicate) {
        return querydslPredicateExecutor.exists(predicate);
    }

    @Override
    @Transactional(readOnly = true)
    public <S extends BE, R> R findBy(Predicate predicate, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return querydslPredicateExecutor.findBy(predicate, queryFunction);
    }

    @Override
    @Transactional(readOnly = true)
    public <BE> Page<BE> findAll(JPAQuery<BE> query, Pageable pageable) {
        return getPage(query, query, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> List<T> findAll(JPQLQuery<T> query) {
        return query.fetch();
    }

    @Override
    @Transactional(readOnly = true)
    public <T> Page<T> findAll(JPQLQuery<T> query, Pageable pageable) {
        return getPage(query, query, pageable);
    }


}
