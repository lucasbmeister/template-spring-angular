package com.totvs.template.Config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource ();
        resourceBundleMessageSource.setBasename("classpath:i18n/global/exception/messages");
        return resourceBundleMessageSource;
    }

}
