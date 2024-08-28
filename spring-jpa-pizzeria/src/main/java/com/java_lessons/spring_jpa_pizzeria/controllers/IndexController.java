package com.java_lessons.spring_jpa_pizzeria.controllers;

import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.models.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PizzaRepository pizzaRepository;

    //INDEX
    @GetMapping
    public String index (Model model){
        //Prendo i dati da consegnare al modello
        List<Pizza> pizzas = pizzaRepository.findAll();

        //Inserisco dati nel modello
        model.addAttribute("pizzas",pizzas);
        return "index";
    }

    //SHOW
    @GetMapping("/ordina/{id}")
    public String ordina(@PathVariable("id") Long id, Model model){

        model.addAttribute("pizza",pizzaRepository.findById(id).get());

        return "ordina";
    }

    // SEARCH PIZZA
    @GetMapping("/search")
    public String findByName(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Pizza> pizzas;

        if (name == null || name.isEmpty()) {
            pizzas = pizzaRepository.findAll();
        } else {
            pizzas = pizzaRepository.findByNameContainingIgnoreCase(name);
        }

        model.addAttribute("pizzas", pizzas);
        return "index";
    }
}
