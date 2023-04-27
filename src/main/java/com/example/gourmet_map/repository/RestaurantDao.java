package com.example.gourmet_map.repository;

import com.example.gourmet_map.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RestaurantDao extends JpaRepository<Menu,String> {
}
