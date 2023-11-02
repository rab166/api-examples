package com.app.apiexamples.features.restaurants;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
	
	
	@Mapping(target = "restaurantId", source = "id", qualifiedByName = "mapId")
	@Mapping(target = "type", source = "restaurantType", qualifiedByName = "mapRestaurantType")
	com.app.apiexamples.data.dto.Restaurant mapRule(Restaurant restaurant);
	
	
	List<com.app.apiexamples.data.dto.Restaurant> mapRule(List<Restaurant> restaurantList);
	
	@Named("mapId")
    default String mapId(Integer id) {
		return (id != null) ? String.valueOf(id.intValue()) : null ;
    }
	
	@Named("mapRestaurantType")
    default String mapRestaurantType(String restaurantType) {
		return restaurantType ;
    }


}
