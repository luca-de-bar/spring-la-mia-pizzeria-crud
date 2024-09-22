package com.java_lessons.spring_jpa_pizzeria.services;

import com.java_lessons.spring_jpa_pizzeria.models.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
}
