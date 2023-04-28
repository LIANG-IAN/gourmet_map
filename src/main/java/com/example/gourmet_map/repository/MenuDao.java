package com.example.gourmet_map.repository;

import com.example.gourmet_map.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface MenuDao extends JpaRepository<Menu, Integer> {

  public List<Menu> findByResName(String resName);

  public boolean existsByResNameAndMeal(String resName, String meal);

  @Transactional
  @Modifying
  public void deleteByResName(String resName);
}
