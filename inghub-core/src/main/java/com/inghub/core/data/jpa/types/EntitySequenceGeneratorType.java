package com.inghub.core.data.jpa.types;

import com.inghub.core.data.jpa.anotation.EntitySequence;
import jakarta.persistence.Table;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.MappingException;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Properties;

/**
 * @author gyurtalan
 * @version 1.0
 */
public class EntitySequenceGeneratorType extends SequenceStyleGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntitySequenceGeneratorType.class);

    private static final String TARGET_TABLE_PARAM = "target_table";

    private static final String ENTITY_NAME_PARAM = "entity_name";

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        try {
            String entityName = params.getProperty(ENTITY_NAME_PARAM);
            String targetTable = params.getProperty(TARGET_TABLE_PARAM);
            Class<?> entityClass = Class.forName(entityName);

            EntitySequence entitySequenceAnnotation = entityClass.getDeclaredAnnotation(EntitySequence.class);
            String sequenceName;
            if (Objects.isNull(entitySequenceAnnotation)) {
                String sequencePerEntitySuffix = ConfigurationHelper.getString(
                        SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX
                        , params
                        , SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);
                sequenceName = targetTable + sequencePerEntitySuffix;
            } else {
                sequenceName = entitySequenceAnnotation.name();
            }
            Table tableAnnotation = entityClass.getDeclaredAnnotation(Table.class);
            if (Objects.nonNull(tableAnnotation)) {
                String schema = tableAnnotation.schema();
                if (StringUtils.isNotEmpty(schema)) {
                    sequenceName = schema + "." + sequenceName;
                }
            }
            params.put(SequenceStyleGenerator.SEQUENCE_PARAM, sequenceName);

            super.configure(new TypeConfiguration().getBasicTypeRegistry().getRegisteredType(Long.class), params, serviceRegistry);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}