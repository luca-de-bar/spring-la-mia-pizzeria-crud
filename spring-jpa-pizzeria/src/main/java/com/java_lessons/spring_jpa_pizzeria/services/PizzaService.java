package com.java_lessons.spring_jpa_pizzeria.services;

import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaService extends JpaRepository<Pizza,Long> {
}
