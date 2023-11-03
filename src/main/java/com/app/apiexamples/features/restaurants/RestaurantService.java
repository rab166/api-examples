package com.app.apiexamples.features.restaurants;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
	
	private final RestaurantRepository restaurantRepository;
	private final RestaurantMapper restaurantMapper;
	
	
	RestaurantService(RestaurantRepository restaurantRepository, 
			RestaurantMapper restaurantMapper) {
		this.restaurantRepository = restaurantRepository;
		this.restaurantMapper = restaurantMapper;
		
	}
	
	public List<com.app.apiexamples.data.dto.Restaurant> getRestaurants() {
		List<Restaurant> restaurantList = restaurantRepository.findAll();
		List<com.app.apiexamples.data.dto.Restaurant> dtoRestaurantList = restaurantMapper.mapRestaurant(restaurantList);
		return dtoRestaurantList;
		
	}
	
	public com.app.apiexamples.data.dto.Restaurant addRestaurant(final com.app.apiexamples.data.dto.Restaurant restaurant) {
		Restaurant newRestaurantEntity = restaurantMapper.mapRestaurant(restaurant);
		Restaurant newRestaurant = restaurantRepository.save(newRestaurantEntity);
		return restaurantMapper.mapRestaurant(newRestaurant);
		
	}
	
	public void deleteRestaurant(Integer restaurantId) {
		restaurantRepository.deleteById(restaurantId);
	}

}
