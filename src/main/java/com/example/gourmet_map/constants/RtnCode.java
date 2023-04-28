package com.example.gourmet_map.constants;

public enum RtnCode {

  ADD_RESTAURANT_SUCCESS("200", "新增餐廳成功"),
  ADD_MEAL_SUCCESS("200", "新增菜單成功"),
  DELETE_RESTAURANT_SUCCESS("200", "餐廳刪除成功"),
  FIND_SUCCESS("200", "尋找成功"),
  CITY_NOT_EXIST_ERROR("400", "該城市沒有餐廳存在"),
  DUPLICATE_RESTAURANT_NAME_ERROR("400", "店名重複"),
  DUPLICATE_MEAL_NAME_ERROR("400", "店名重複"),
  INPUT_NOT_ALLOWED_VALUE_ERROR("400", "輸入不正確"),
  QUANTITY_OVER_LIMIT_ERROR("400", "無法增加，餐點數量超過"),
  RESTAURANT_NOT_FOUND_ERROR("404", "找不到該餐廳");


  private String code;

  private String message;

  RtnCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
