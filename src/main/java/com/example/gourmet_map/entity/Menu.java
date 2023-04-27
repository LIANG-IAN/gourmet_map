package com.example.gourmet_map.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "meal")
  private String meal;

  @Column(name = "review")
  private double review;

  public Menu() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMeal() {
    return meal;
  }

  public void setMeal(String meal) {
    this.meal = meal;
  }

  public double getReview() {
    return review;
  }

  public void setReview(double review) {
    this.review = review;
  }
}
