package com.bridgelabz.configration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfigration {
	
	@Bean
	public ModelMapper mapper() {
		
		return new ModelMapper();
	}

}
