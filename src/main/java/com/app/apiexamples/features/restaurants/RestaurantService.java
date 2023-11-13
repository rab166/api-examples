package com.app.apiexamples.features.restaurants;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<com.app.apiexamples.data.dto.Restaurant> getRestaurantDetail(final Integer restaurantId) {
		
		Optional<Restaurant> newRestaurant = restaurantRepository.findById(restaurantId);
		if (newRestaurant.isPresent()) {
			return Optional.of(restaurantMapper.mapRestaurant(newRestaurant.get()));
		} 
		return Optional.empty();
	}
	
	public com.app.apiexamples.data.dto.Restaurant addRestaurant(final com.app.apiexamples.data.dto.Restaurant restaurant) {
		Restaurant newRestaurantEntity = restaurantMapper.mapRestaurant(restaurant);
		Restaurant newRestaurant = restaurantRepository.save(newRestaurantEntity);
		return restaurantMapper.mapRestaurant(newRestaurant);
		
	}
	
	public boolean updateRestaurant(final com.app.apiexamples.data.dto.Restaurant restaurant) {
		
		if(getRestaurantDetail(restaurant.getRestaurantId()).isPresent()) {
			Restaurant newRestaurantEntity = restaurantMapper.mapRestaurant(restaurant);
			restaurantRepository.save(newRestaurantEntity);
			return true;
		} 
		return false;
	}
	
	public void deleteRestaurant(final Integer restaurantId) {
		restaurantRepository.deleteById(restaurantId);
	}

}
