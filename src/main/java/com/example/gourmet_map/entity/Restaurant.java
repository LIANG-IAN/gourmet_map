package com.example.gourmet_map.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

  @Id
  @Column(name = "res_name")
  private String resName;

  @Column(name = "address")
  private String address;

  @Column(name = "review")
  private double review;

  public Restaurant() {
  }

  public Restaurant(String resName, String address) {
  }

  public String getResName() {
    return resName;
  }

  public void setResName(String name) {
    this.resName = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getReview() {
    return review;
  }

  public void setReview(double review) {
    this.review = review;
  }
}
