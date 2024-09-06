package com.java_lessons.spring_jpa_pizzeria.controllers;

import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.models.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PizzaRepository pizzaRepository;

    //Index
    @GetMapping
    public String index (Model model){
        //Prendo i dati da consegnare al modello
        List<Pizza> pizzas = pizzaRepository.findAll();

        //Inserisco dati nel modello
        model.addAttribute("pizzas",pizzas);
        return "index";
    }

    //Show
    @GetMapping("/ordina/{id}")
    public String ordina(@PathVariable("id") Long id, Model model){

        model.addAttribute("pizza",pizzaRepository.findById(id).get());

        return "show";
    }


    //Create
    @GetMapping("/create")
    public String create (Model model){
        model.addAttribute("pizza", new Pizza());
        return "create";
    }

    //Store
    @PostMapping("/create")
    public String store (@ModelAttribute Pizza pizza){
        pizzaRepository.save(pizza);
        return "redirect:/create";
    }


    // Search Form
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
