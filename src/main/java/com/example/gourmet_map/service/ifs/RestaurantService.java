package com.example.gourmet_map.service.ifs;

import com.example.gourmet_map.vo.RestaurantResponse;


public interface RestaurantService {

  public RestaurantResponse addRestaurant(String resName,String address);

  public RestaurantResponse addMenu(String resName,String meal,int price,double review);

  public RestaurantResponse findRestaurantByCity(String city,String index);

  public RestaurantResponse findRestaurantByReviewGreaterThan(double review);

  public RestaurantResponse findRestaurantAndMenuByReview(double review);

  public RestaurantResponse deleteRestaurant(String resName);
}
