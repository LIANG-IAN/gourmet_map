package com.example.gourmet_map.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {

  @Id
  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "review")
  private double review;

  public Restaurant() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
