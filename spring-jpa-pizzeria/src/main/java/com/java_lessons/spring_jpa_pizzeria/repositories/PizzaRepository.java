package com.java_lessons.spring_jpa_pizzeria.repositories;
import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository <Pizza,Long>{
    //search query
    List <Pizza> findByNameContainingIgnoreCase(String name);
}
