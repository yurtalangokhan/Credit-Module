package com.inghub.core.data.jpa.repository;

import com.inghub.core.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author gyurtalan
 * @version 1.0
 */
@NoRepositoryBean
@Hidden
public interface BaseRepository<BE extends BaseEntity> extends JpaRepository<BE> {
}
