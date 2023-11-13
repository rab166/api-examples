package com.app.apiexamples.features.restaurants;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.app.apiexamples.features.restaurants.Constants.GET_RESTAURANTS;
import static com.app.apiexamples.features.restaurants.Constants.GET_RESTAURANT_DETAIL;
import static com.app.apiexamples.features.restaurants.Constants.ADD_RESTAURANT;
import static com.app.apiexamples.features.restaurants.Constants.UPDATE_RESTAURANT;
import static com.app.apiexamples.features.restaurants.Constants.DELETE_RESTAURANT;


import java.util.Optional;

import javax.validation.Valid;


import com.app.apiexamples.data.dto.AddRestaurantResponse;
import com.app.apiexamples.data.dto.RestaurantsListResponse;

@RestController
public class RestaurantController {

	private final RestaurantService restaurantService;
	
	RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	@GetMapping(value = GET_RESTAURANTS, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestaurantsListResponse getRestaurantsList() {
		RestaurantsListResponse response = new RestaurantsListResponse();
		response.setRestaurants(restaurantService.getRestaurants());
		return response;
	}
	
	@GetMapping(value = GET_RESTAURANT_DETAIL, produces = MediaType.APPLICATION_JSON_VALUE)
	public com.app.apiexamples.data.dto.Restaurant getRestaurantDetail(
			@PathVariable(value = "restaurantId", required = true) Integer restaurantId) {
		Optional<com.app.apiexamples.data.dto.Restaurant> restaurant = 
				restaurantService.getRestaurantDetail(restaurantId);
		if (restaurant.isPresent()) {
			return restaurant.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found");
		
	}
	
	@PostMapping(value = ADD_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
	public AddRestaurantResponse addRestaurant(
			@Valid @RequestBody com.app.apiexamples.data.dto.Restaurant restaurant) {
		AddRestaurantResponse response = new AddRestaurantResponse();
		response.setRestaurant(restaurantService.addRestaurant(restaurant));
		return response;
	}
	
	@PutMapping(value = UPDATE_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateRestaurant(
			@Valid @RequestBody com.app.apiexamples.data.dto.Restaurant restaurant) {
		if (restaurantService.updateRestaurant(restaurant)) {
			return;
		} 
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found");
	}
	
	@DeleteMapping(value = DELETE_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRestaurant(@PathVariable(value = "restaurantId", required = true) Integer restaurantId) {
		restaurantService.deleteRestaurant(restaurantId);
	}
	
	
}
