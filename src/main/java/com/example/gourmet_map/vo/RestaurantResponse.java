package com.example.gourmet_map.vo;

import com.example.gourmet_map.entity.Menu;
import com.example.gourmet_map.entity.Restaurant;

import java.util.List;

public class RestaurantResponse {

  private Restaurant restaurant;

  private List<Restaurant> restaurantList;

  private Menu menu;

  private List<Menu> menuList;

  private String message;

  private List<String> list;

  public RestaurantResponse() {
  }

  public RestaurantResponse(String message) {
    this.message = message;
  }

  public RestaurantResponse(Restaurant restaurant, String message) {
    this.restaurant = restaurant;
    this.message = message;
  }

  public RestaurantResponse(List<String> list,String message) {
    this.list = list;
    this.message = message;
  }

  public RestaurantResponse(List<Restaurant> restaurantList, List<Menu> menuList, String message) {
    this.restaurantList = restaurantList;
    this.menuList = menuList;
    this.message = message;
  }


  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  public List<Restaurant> getRestaurantList() {
    return restaurantList;
  }

  public void setRestaurantList(List<Restaurant> restaurantList) {
    this.restaurantList = restaurantList;
  }

  public List<Menu> getMenuList() {
    return menuList;
  }

  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public void setMenuList(List<Menu> menuList) {
    this.menuList = menuList;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getList() {
    return list;
  }

  public void setList(List<String> list) {
    this.list = list;
  }
}
