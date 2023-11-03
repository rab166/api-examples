package com.app.apiexamples.features.restaurants;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.app.apiexamples.features.restaurants.Constants.GET_RESTAURANTS;

import javax.validation.Valid;

import static com.app.apiexamples.features.restaurants.Constants.ADD_RESTAURANT;
import static com.app.apiexamples.features.restaurants.Constants.DELETE_RESTAURANT;

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
	
	@PostMapping(value = ADD_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
	public AddRestaurantResponse addRestaurant(
			@Valid @RequestBody com.app.apiexamples.data.dto.Restaurant restaurant) {
		AddRestaurantResponse response = new AddRestaurantResponse();
		response.setRestaurant(restaurantService.addRestaurant(restaurant));
		return response;
	}
	
	@DeleteMapping(value = DELETE_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRestaurant(@PathVariable(value = "restaurantId", required = true) Integer restaurantId) {
		restaurantService.deleteRestaurant(restaurantId);
	}
	
	
}
