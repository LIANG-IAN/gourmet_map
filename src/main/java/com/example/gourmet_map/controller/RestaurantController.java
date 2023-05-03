package com.example.gourmet_map.controller;

import com.example.gourmet_map.service.ifs.RestaurantService;
import com.example.gourmet_map.vo.RestaurantRequest;
import com.example.gourmet_map.vo.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RestaurantController {

  @Autowired
  RestaurantService restaurantService;

  @PostMapping(value = "add_restaurant")
  public RestaurantResponse addRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
    return restaurantService.addRestaurant(restaurantRequest.getResName(), restaurantRequest.getAddress());
  }

  @PostMapping(value = "add_menu")
  public RestaurantResponse addMenu(@RequestBody RestaurantRequest restaurantRequest) {
    return restaurantService.addMenu (restaurantRequest.getResName(),restaurantRequest.getMeal(),restaurantRequest.getPrice(),restaurantRequest.getReview());
  }

  @GetMapping(value = "find_restaurant_by_city")
  public RestaurantResponse findRestaurantByCity(@RequestBody RestaurantRequest restaurantRequest) {
    return restaurantService.findRestaurantByCity (restaurantRequest.getCity(),restaurantRequest.getIndex());
  }

  @GetMapping(value = "find_restaurant_by_review_greater_than")
  public RestaurantResponse findRestaurantByReviewGreaterThan(@RequestBody RestaurantRequest restaurantRequest) {
    return restaurantService.findRestaurantByReviewGreaterThan (restaurantRequest.getReview());
  }

  @GetMapping(value = "find_restaurant_and_menu_by_review")
  public RestaurantResponse findRestaurantAndMenuByReview(@RequestBody RestaurantRequest restaurantRequest) {
    return restaurantService.findRestaurantAndMenuByReview (restaurantRequest.getReview());
  }

  @PostMapping(value = "delete_restaurant")
  public RestaurantResponse deleteRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
    return restaurantService.deleteRestaurant (restaurantRequest.getResName());
  }
}
