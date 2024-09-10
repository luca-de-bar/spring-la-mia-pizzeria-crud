package com.java_lessons.spring_jpa_pizzeria.controllers;

import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.models.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    //Index
    @GetMapping
    public String index (Model model){
        //Prendo i dati da consegnare al modello
        List<Pizza> pizzas = pizzaRepository.findAll();

        //Inserisco dati nel modello
        model.addAttribute("pizzas",pizzas);
        return "pizza/index";
    }

    //Show
    @GetMapping("/ordina/{id}")
    public String ordina(@PathVariable("id") Long id, Model model){

        model.addAttribute("pizza",pizzaRepository.findById(id).get());

        return "pizza/show";
    }


    //Create + Store
    @GetMapping("/create")
    public String create (Model model){
        model.addAttribute("pizza", new Pizza());
        return "pizza/create";
    }

    @PostMapping("/create")
    public String store (@Valid @ModelAttribute Pizza pizza,
                         BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "pizza/create";
        } else {
            pizzaRepository.save(pizza);
            return "redirect:/";
        }
    }

    //Edit + Update
    @GetMapping("/edit{Id}")
    public String edit(@PathVariable("id") Long id, Model model){

        model.addAttribute("pizza",pizzaRepository.findById(id).get());
        return "pizza/edit";
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
        return "pizza/index";
    }
}
