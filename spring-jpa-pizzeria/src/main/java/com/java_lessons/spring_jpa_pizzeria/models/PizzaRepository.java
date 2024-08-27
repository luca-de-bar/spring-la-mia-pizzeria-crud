package com.java_lessons.spring_jpa_pizzeria.models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository <Pizza,Long>{

}
