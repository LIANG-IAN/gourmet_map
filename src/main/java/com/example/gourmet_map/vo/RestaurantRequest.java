package com.example.gourmet_map.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantRequest {

  @JsonProperty("res_name")
  private String resName;

  @JsonProperty("address")
  private String address;

  @JsonProperty("meal")
  private String meal;

  @JsonProperty("price")
  private int price;

  @JsonProperty("review")
  private double review;

  @JsonProperty("city")
  private String city;

  @JsonProperty("index")
  private String index;

  public RestaurantRequest() {
  }

  public String getResName() {
    return resName;
  }

  public String getAddress() {
    return address;
  }

  public String getMeal() {
    return meal;
  }

  public int getPrice() {
    return price;
  }

  public double getReview() {
    return review;
  }

  public String getCity() {
    return city;
  }

  public String getIndex() {
    return index;
  }
}
