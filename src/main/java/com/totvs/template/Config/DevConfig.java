package com.totvs.template.Config;

import org.modelmapper.convention.NamingConventions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.modelmapper.config.Configuration.AccessLevel;


@Configuration
@Profile("DEV")
@PropertySource("classpath:${configfile.path}/dev.properties")
public class DevConfig {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
	public ModelMapper modelMapper() {
	    ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
    	return modelMapper;
	}
}
