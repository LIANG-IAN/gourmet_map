package com.example.gourmet_map.service.impl;

import com.example.gourmet_map.constants.RtnCode;
import com.example.gourmet_map.entity.Menu;
import com.example.gourmet_map.entity.Restaurant;
import com.example.gourmet_map.repository.MenuDao;
import com.example.gourmet_map.repository.RestaurantDao;
import com.example.gourmet_map.service.ifs.RestaurantService;
import com.example.gourmet_map.vo.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantImpl implements RestaurantService {

  @Autowired
  RestaurantDao rDao;

  @Autowired
  MenuDao mDao;


  @Override
  public RestaurantResponse addRestaurant(String resName, String address) {
    // 檢查是否為空
    if (isNull(resName, address)) {
      return new RestaurantResponse(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage());
    }
    // 透過SQL語法，檢查店名是否重複，並儲存至餐廳資料庫
    int temp = rDao.addRestaurant(resName, address);
    if (temp == 0) {
      return new RestaurantResponse(RtnCode.DUPLICATE_RESTAURANT_NAME_ERROR.getMessage());
    }
    return new RestaurantResponse(RtnCode.ADD_RESTAURANT_SUCCESS.getMessage());
  }

  @Override
  public RestaurantResponse addMenu(String resName, String meal, int price, double review) {
    // 檢查是否為空、價錢不為負數、評價1~5，但允許0元料理
    if (isNull(resName, meal)
            || price < 0
            || review < 1
            || review > 5) {
      return new RestaurantResponse(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage());
    }
    // 檢查是否有該店名餐廳存在
    // 僅允許先新增餐廳，再新增餐點
    Optional<Restaurant> opRes = rDao.findById(resName);
    if (opRes.isEmpty()) {
      return new RestaurantResponse(RtnCode.RESTAURANT_NOT_FOUND_ERROR.getMessage());
    }
    // 檢查餐廳是否有重複餐點
    if (mDao.existsByResNameAndMeal(resName, meal)) {
      return new RestaurantResponse(RtnCode.DUPLICATE_MEAL_NAME_ERROR.getMessage());
    }
    Restaurant res = opRes.get();
    // 取得同餐廳所有餐點
    List<Menu> menuList = mDao.findByResName(resName);
    // 取得餐點數量，即評價的分母
    int count = menuList.size() + 1;
    // 檢查菜單數量不得超過3道
    if (count > 3) {
      return new RestaurantResponse(RtnCode.QUANTITY_OVER_LIMIT_ERROR.getMessage());
    }
    Menu menu = new Menu(resName, meal, price, review);
    // 儲存菜單至菜單資料庫
    mDao.save(menu);
    // 取的所有評價的總分
    for (Menu m : menuList) {
      double menuReview = m.getReview();
      review = review + menuReview;
    }
    // 計算評價平均
    double temp = review / count;
    // 四捨五入到小數第一位
    double roundedNumber = Math.round(temp * 10.0) / 10.0;
    res.setReview(roundedNumber);
    // 儲存餐廳評價至資料庫
    rDao.save(res);
    return new RestaurantResponse(res, RtnCode.ADD_MEAL_SUCCESS.getMessage());
  }

  @Override
  public RestaurantResponse findRestaurantByCity(String city, String index) {
    // 檢查地址是否為空、index是否為null，但index允許為空格
    if (isNull(city) || index == null) {
      return new RestaurantResponse(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage());
    }
    // 輸入0或空格時顯示所有資料，為了方便輸入負數時也會顯示所有資料
    // 字轉轉成數字，並檢查是否為空格
    int i ;
    if (index.trim().isBlank() || Integer.parseInt(index) <= 0) {
      i = 999999;
    }
    else {
      i = Integer.parseInt(index);
    }
    // 透過SQL語法，尋找相符地址且特定筆數的餐廳資料
    List<Restaurant> restaurantList = rDao.findByAddressWithIndex(city, i);
    // 檢查尋找的地址是否有餐廳
    if (CollectionUtils.isEmpty(restaurantList)) {
      return new RestaurantResponse(RtnCode.CITY_NOT_EXIST_ERROR.getMessage());
    }
    // 取得餐廳的菜單
    // 創建集合收集各餐廳的菜單
    List<Menu> menuList = new ArrayList<>();
    for (Restaurant r : restaurantList) {
      String resName = r.getResName();
      // 取得同店名的所有菜單
      List<Menu> temp = mDao.findByResName(resName);
      // 檢查該餐廳是否有菜單存在，無存在就跳過換下一家餐廳
      if (CollectionUtils.isEmpty(temp)) {
        continue;
      }
      // 菜單存入集合，帶出迴圈
      menuList.addAll(temp);
    }
    return new RestaurantResponse(restaurantList, menuList, RtnCode.FIND_SUCCESS.getMessage());
  }

  @Override
  public RestaurantResponse findRestaurantByReviewGreaterThan(double review) {
    // 判斷輸入評價1~5
    if (review < 1 || review > 5) {
      return new RestaurantResponse(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage());
    }
    // 透過SQL語法取得review以上的所有餐廳及餐廳菜單
    List<Object[]> reviewList = rDao.findRestaurantByReviewGreaterThan(review);
    // 將獲得資料轉成字串
    List<String> gourmetList = new ArrayList<>();
    for (Object[] obj : reviewList) {
      String temp = obj[0] + " " + obj[1] + " " + obj[2] + " " + obj[3] + " " + obj[4] + " " + obj[5];
      gourmetList.add(temp);
    }
    return new RestaurantResponse(gourmetList, RtnCode.FIND_SUCCESS.getMessage());
  }

  @Override
  public RestaurantResponse findRestaurantAndMenuByReview(double review) {
    // 判斷輸入評價1~5
    if (review < 1 || review > 5) {
      return new RestaurantResponse(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage());
    }
    // 透過SQL語法取得review以上的所有餐廳及餐廳菜單
    List<Object[]> reviewList = rDao.findRestaurantAndMenuByReview(review);
    // 將獲得資料轉成字串
    List<String> gourmetList = new ArrayList<>();
    for (Object[] obj : reviewList) {
      String temp = obj[0] + " " + obj[1] + " " + obj[2] + " " + obj[3] + " " + obj[4] + " " + obj[5];
      gourmetList.add(temp);
    }
    return new RestaurantResponse(gourmetList, RtnCode.FIND_SUCCESS.getMessage());
  }

  public RestaurantResponse deleteRestaurant(String resName) {
    // 檢查是否為空
    if (isNull(resName)) {
      return new RestaurantResponse(RtnCode.INPUT_NOT_ALLOWED_VALUE_ERROR.getMessage());
    }
    mDao.deleteByResName(resName);
    rDao.deleteByResName(resName);
    return new RestaurantResponse(RtnCode.DELETE_RESTAURANT_SUCCESS.getMessage());
  }

  private boolean isNull(String... str) {
    for (String s : str) {
      if (!StringUtils.hasText(s)) {
        return true;
      }
    }
    return false;
  }


}
