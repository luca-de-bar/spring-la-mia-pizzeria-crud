package com.java_lessons.spring_jpa_pizzeria.controllers;

import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.models.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PizzaRepository pizzaRepository;

    //HOME
    @GetMapping("/")
    public String index (Model model){
        //Prendo i dati da consegnare al modello
        List<Pizza> pizzas = pizzaRepository.findAll();

        //Inserisco dati nel modello
        model.addAttribute("pizza",pizzas);
        return "home";
    }
}
