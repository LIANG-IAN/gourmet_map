package com.example.gourmet_map;

import com.example.gourmet_map.constants.RtnCode;
import com.example.gourmet_map.entity.Restaurant;
import com.example.gourmet_map.repository.RestaurantDao;
import com.example.gourmet_map.service.impl.RestaurantImpl;
import com.example.gourmet_map.vo.RestaurantResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class GourmetMapApplicationTests {
/*  public RestaurantResponse addRestaurant(String resName,String address);

  public RestaurantResponse addMenu(String resName,String meal,int price,double review);

  public RestaurantResponse findRestaurantByCity(String city,String index);

  public RestaurantResponse findRestaurantByReviewGreaterThan(double review);

  public RestaurantResponse findRestaurantAndMenuByReview(double review);

  public RestaurantResponse deleteRestaurant(String resName);*/

  @Autowired
  RestaurantImpl restaurant;

  @Autowired
  RestaurantDao restaurantDao;

  // 新增餐廳：成功
  @Test
  public void addRestaurant1() {
    RestaurantResponse response = restaurant.addRestaurant("鼎王", "台北");
    Assert.isTrue(response.getMessage().equals(RtnCode.ADD_RESTAURANT_SUCCESS.getMessage()), "");
  }

  // 新增餐廳：數值為空
  @Test
  public void addRestaurant2() {
    RestaurantResponse response = restaurant.addRestaurant(null, "台北");
    Assert.isTrue(response.getMessage().equals(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage()), "");
  }

  // 新增餐廳：店名重複
  @Test
  public void addRestaurant3() {
    RestaurantResponse response = restaurant.addRestaurant("五十嵐", "台北");
    Assert.isTrue(response.getMessage().equals(RtnCode.DUPLICATE_RESTAURANT_NAME_ERROR.getMessage()), "");
  }


   // 新增菜單：成功
  @Test
  public void addMenu1() {
    RestaurantResponse response = restaurant.addMenu("鼎泰豐", "魚排", 150, 1.0);
    Assert.isTrue(response.getMessage().equals(RtnCode.ADD_MEAL_SUCCESS.getMessage()), "");
  }

  // 新增菜單：價錢為負數
  @Test
  public void addMenu2() {
    RestaurantResponse response = restaurant.addMenu("鼎泰豐", "魚排", -150, 1.0);
    Assert.isTrue(response.getMessage().equals(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage()), "");
  }

  // 新增菜單：新增菜單的店不存在
  @Test
  public void addMenu3() {
    RestaurantResponse response = restaurant.addMenu("孫東寶", "牛排", 150, 1.0);
    Assert.isTrue(response.getMessage().equals(RtnCode.RESTAURANT_NOT_FOUND_ERROR.getMessage()), "");
  }

  // 新增菜單：同餐廳重複餐點
  @Test
  public void addMenu4() {
    RestaurantResponse response = restaurant.addMenu("鼎泰豐", "魚排", 150, 1.0);
    Assert.isTrue(response.getMessage().equals(RtnCode.ADD_MEAL_SUCCESS.getMessage()), "");
  }

  // 新增菜單：餐點超過3道
  @Test
  public void addMenu5() {
    RestaurantResponse response = restaurant.addMenu("鼎泰豐", "小籠包", 150, 1.0);
    Assert.isTrue(response.getMessage().equals(RtnCode.QUANTITY_OVER_LIMIT_ERROR.getMessage()), "");
  }

  // 搜尋該城市有的餐廳：成功
  @Test
  public void findRestaurantByCity1(){
    RestaurantResponse response =  restaurant.findRestaurantByCity("台北","0");
    Assert.isTrue(response.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");

  }

  // 搜尋該城市有的餐廳：數值為空
  @Test
  public void findRestaurantByCity2(){
    RestaurantResponse response =  restaurant.findRestaurantByCity(null,"0");
    Assert.isTrue(response.getMessage().equals(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage()), "");
  }

  // 搜尋該城市有的餐廳：該城市無餐廳
  @Test
  public void findRestaurantByCity3(){
    RestaurantResponse response =  restaurant.findRestaurantByCity("紐約","0");
    Assert.isTrue(response.getMessage().equals(RtnCode.CITY_NOT_EXIST_ERROR.getMessage()), "");
  }

  // 搜尋指定評價以上的餐廳：成功
  @Test
  public void findRestaurantByReviewGreaterThan1(){
    RestaurantResponse response =  restaurant.findRestaurantByReviewGreaterThan(1);
    Assert.isTrue(response.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }

  // 搜尋指定評價以上的餐廳：輸入評價不在範圍內
  @Test
  public void findRestaurantByReviewGreaterThan2(){
    RestaurantResponse response =  restaurant.findRestaurantByReviewGreaterThan(6);
    Assert.isTrue(response.getMessage().equals(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage()), "");
  }

  // 搜尋指定評價以上的餐廳及菜單：成功
  @Test
  public void findRestaurantAndMenuByReview1(){
    RestaurantResponse response =  restaurant.findRestaurantAndMenuByReview(3.5);
    Assert.isTrue(response.getMessage().equals(RtnCode.FIND_SUCCESS.getMessage()), "");
  }

  // 搜尋指定評價以上的餐廳及菜單：輸入評價不在範圍內
  @Test
  public void findRestaurantAndMenuByReview2(){
    RestaurantResponse response =  restaurant.findRestaurantAndMenuByReview(3.5);
    Assert.isTrue(response.getMessage().equals(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage()), "");
  }

  //刪除餐廳及菜單：成功
  @Test
  public void deleteRestaurant1(){
    RestaurantResponse response =  restaurant.deleteRestaurant("麥當勞");
    Assert.isTrue(response.getMessage().equals(RtnCode.DELETE_RESTAURANT_SUCCESS.getMessage()), "");
  }

  //刪除餐廳及菜單：輸入值為空
  @Test
  public void deleteRestaurant2(){
    RestaurantResponse response =  restaurant.deleteRestaurant(null);
    Assert.isTrue(response.getMessage().equals(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage()), "");
  }

  @Test
  public void findByALotName1(){
    List<Restaurant> r = restaurantDao.findByALotName1("十","摩","張");
    for (Restaurant restaurant1 : r) {
      System.out.println(restaurant1.getResName());
    }
  }

  @Test
  public void findByALotName2(){
    List<Restaurant> r = restaurantDao.findByALotName2("十|摩|張");
    for (Restaurant restaurant1 : r) {
      System.out.println(restaurant1.getResName());
    }
  }
}
