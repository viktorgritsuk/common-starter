package com.common.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class BeanConfig {

    /**
     * Create and configure LocalValidatorFactoryBean.
     * Using validation.properties file to com.bae.ii.config validation
     * messages.
     *
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        ReloadableResourceBundleMessageSource messageSource
            = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:validation");
        messageSource.setDefaultEncoding("UTF-8");

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();

        bean.setValidationMessageSource(messageSource);

        return bean;
    }

}
