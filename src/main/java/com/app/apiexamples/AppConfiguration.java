package com.app.apiexamples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.apiexamples.features.restaurants.RestaurantMapper;


@Configuration
@EnableJpaRepositories(basePackages = {"com.app.apiexamples"})
public class AppConfiguration {
	
	
}
