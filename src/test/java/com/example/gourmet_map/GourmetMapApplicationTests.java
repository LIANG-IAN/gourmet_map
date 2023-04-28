package com.example.gourmet_map;

import com.example.gourmet_map.constants.RtnCode;
import com.example.gourmet_map.service.impl.RestaurantImpl;
import com.example.gourmet_map.vo.RestaurantResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class GourmetMapApplicationTests {

  @Autowired
  RestaurantImpl restaurant;

  // 新增餐廳：成功
  @Test
  public void addRestaurant() {
    RestaurantResponse response = restaurant.addRestaurant("鼎泰豐", "台北");
    Assert.isTrue(response.getMessage().equals(RtnCode.ADD_RESTAURANT_SUCCESS.getMessage()), "");
  }

   // 新增菜單：成功
  @Test
  public void addMenu() {
    RestaurantResponse response = restaurant.addMenu("鼎泰豐", "魚排", 150, 1.0);
    //Assert.isTrue(response.getMessage().equals(RtnCode.ADD_MEAL_SUCCESS.getMessage()), "");
    System.out.println(response.getMessage());
  }

  // 搜尋該城市有的餐廳：成功
  @Test
  public void findRestaurantByCity(){
    RestaurantResponse response =  restaurant.findRestaurantByCity("台北","0");
    System.out.println(response.getMessage());
  }

  // 搜尋指定評價以上的餐廳：成功
  @Test
  public void findRestaurantByReviewGreaterThan(){
    RestaurantResponse response =  restaurant.findRestaurantByReviewGreaterThan(1);
    System.out.println(response.getMessage());
  }

  // 搜尋指定評價以上的餐廳及菜單：成功
  @Test
  public void findRestaurantAndMenuByReview(){
    RestaurantResponse response =  restaurant.findRestaurantAndMenuByReview(3.5);

  }

  //刪除餐廳及菜單：成功
  @Test
  public void deleteRestaurant(){
    RestaurantResponse response =  restaurant.deleteRestaurant("麥當勞");

  }
}
