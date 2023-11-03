package com.app.apiexamples.features.restaurants;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
	
	
	@Mapping(target = "restaurantId", source = "id", qualifiedByName = "mapId")
	@Mapping(target = "type", source = "restaurantType", qualifiedByName = "mapRestaurantTypeToType")
	com.app.apiexamples.data.dto.Restaurant mapRestaurant(Restaurant restaurant);
	
	
	List<com.app.apiexamples.data.dto.Restaurant> mapRestaurant(List<Restaurant> restaurantList);
	
	@Mapping(target = "restaurantType", source = "type", qualifiedByName = "mapTypeToRestaurantType")
	Restaurant mapRestaurant(com.app.apiexamples.data.dto.Restaurant restaurant);
	
	
	@Named("mapId")
    default String mapId(Integer id) {
		return (id != null) ? String.valueOf(id.intValue()) : null ;
    }
	
	@Named("mapRestaurantTypeToType")
    default String mapRestaurantTypeToType(String restaurantType) {
		return restaurantType ;
    }
	
	@Named("mapTypeToRestaurantType")
    default String mapTypeToRestaurantType(String type) {
		return type ;
    }


}
