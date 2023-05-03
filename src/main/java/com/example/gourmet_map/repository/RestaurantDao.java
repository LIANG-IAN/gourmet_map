package com.example.gourmet_map.repository;

import com.example.gourmet_map.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository

public interface RestaurantDao extends JpaRepository<Restaurant, String> {

  //@Transactional、@Modifying只用在會回傳的方法
  // select 1，1不重要可以寫任何數字
  @Transactional
  @Modifying
  @Query(value = "insert into restaurant(resName,address)select :inputResName, :inputAddress where " +
          "not exists(select 1 from restaurant  where resName = :inputRes)", nativeQuery = true)
  int addRestaurant(
          @Param("inputResName") String inputResName,
          @Param("inputAddress") String inputAddress
  );


  @Query(value = "select r.res_name, r.address, r.review  from Restaurant r  " +
          "where r.address = :city order by r.res_name limit :i", nativeQuery = true)
  List<Restaurant> findByAddressWithIndex(
          @Param("city") String city,
          @Param("i") int index
  );

  @Query(value = "SELECT r.res_name, r.address, r.review, m.meal, m.price, m.review FROM restaurant r " +
          "JOIN menu m ON r.res_name = m.res_name where r.review >= :i", nativeQuery = true)
  List<Object[]> findRestaurantByReviewGreaterThan(@Param("i") double index);

  @Query(value = "SELECT r.res_name, r.address, r.review, m.meal, m.price, m.review FROM restaurant r JOIN menu m ON r.res_name = m.res_name where r.review >= :i and m.review >=:i", nativeQuery = true)
  List<Object[]> findRestaurantAndMenuByReview(@Param("i") double index);

  @Transactional
  @Modifying
  void deleteByResName(String resName);
}
