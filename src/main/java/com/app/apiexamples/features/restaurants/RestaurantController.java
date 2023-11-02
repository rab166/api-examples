package com.app.apiexamples.features.restaurants;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.apiexamples.features.restaurants.Constants.GET_RESTAURANTS;

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
}
