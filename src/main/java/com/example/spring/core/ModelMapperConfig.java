package com.example.spring.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //"@Configuration" is used to declares one or more "@Bean"
public class ModelMapperConfig {
	
	@Bean //"@Bean" produces a bean to be managed by the Spring container
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
