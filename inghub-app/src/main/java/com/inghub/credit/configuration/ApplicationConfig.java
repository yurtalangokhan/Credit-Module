package com.inghub.credit.configuration;

import com.inghub.core.common.configuration.BaseConfiguration;
import com.inghub.core.data.jpa.factory.QuerydslJpaRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.inghub.credit.configuration.Constants.*;


/**
 * @author gyurtalan
 * @version 1.0
 */
@Configuration
@Profile(value = {"dev", "prod", "test"})
@ComponentScan(basePackages = {MODULE_BASE_PACKAGE})
@EntityScan(basePackages = {MODULE_BASE_PACKAGE})
@EnableJpaRepositories(basePackages = {MODULE_BASE_PACKAGE}, repositoryFactoryBeanClass = QuerydslJpaRepositoryFactoryBean.class)
@EnableJpaAuditing(auditorAwareRef = AUDITOR_PROVIDER)
public class ApplicationConfig extends BaseConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("inghub admin");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
