package com.marco.B2TechBuildApiSpringBootMongodbSept2021.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author ncast
 * @version 1.0
 * @created 4/10/2022
 */
@Configuration
public class ValidationConfig {

    /**
     * This method will be triggered before we process any data in the database.
     * @return
     */
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    /**
     *
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
