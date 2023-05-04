package com.example.gourmet_map.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "menu")
public class Menu {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "res_name")
  private String resName;

  @Column(name = "meal")
  private String meal;

  @Column(name = "price")
  private int price;

  @Column(name = "review")
  private double review;


  public Menu() {

  }

  public Menu(int id, String resName, String meal, int price, double review) {
    this.id = id;
    this.resName = resName;
    this.meal = meal;
    this.price = price;
    this.review = review;
  }

  public Menu(String resName, String meal, int price, double review) {
    this.resName = resName;
    this.meal = meal;
    this.price = price;
    this.review = review;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getResName() {
    return resName;
  }

  public void setResName(String name) {
    this.resName = name;
  }

  public String getMeal() {
    return meal;
  }

  public void setMeal(String meal) {
    this.meal = meal;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public double getReview() {
    return review;
  }

  public void setReview(double review) {
    this.review = review;
  }
}
