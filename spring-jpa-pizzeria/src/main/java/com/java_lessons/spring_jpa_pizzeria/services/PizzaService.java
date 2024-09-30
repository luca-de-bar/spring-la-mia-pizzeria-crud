package com.java_lessons.spring_jpa_pizzeria.services;

import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PizzaService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @Autowired
    private PizzaRepository pizzaRepository;

    public List <Pizza> findAll(){
        return pizzaRepository.findAll();
    }

    public List <Pizza> findPizzaByName(String name){
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    public Pizza findById(Long id){
        return pizzaRepository.findById(id).get();
    }

    public Pizza save(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public void delete(Long id){
        pizzaRepository.deleteById(id);
    }
}
