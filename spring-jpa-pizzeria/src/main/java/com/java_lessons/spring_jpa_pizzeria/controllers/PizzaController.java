package com.java_lessons.spring_jpa_pizzeria.controllers;

import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.models.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;


    //Index
    @GetMapping
    public String index (Model model){
        List<Pizza> pizzaList = pizzaRepository.findAll();
        model.addAttribute("pizzas",pizzaList);
        return "pizza/index";
    }

    //Show
    @GetMapping("/show/{id}")
    public String show (@PathVariable("id") Long id, Model model){
        model.addAttribute("book",pizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id)));
        return "pizza/show";
    }

    //Create + Store
    @GetMapping("/create")
    public String create (Model model){
        model.addAttribute("pizza",new Pizza());
        return "pizza/create";
    }

    @PostMapping("/create")
    public String store (@Valid @ModelAttribute("pizza") Pizza formPizza,
                         BindingResult bindingResult,
                         Model model){

        if(bindingResult.hasErrors()){
            return "pizza/create";
        }
        pizzaRepository.save(formPizza);
        return "redirect:/";
    }

    //Edit + Update
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,Model model){
        model.addAttribute("book",pizzaRepository.findById(id).get());
        return "pizza/edit";
    }


    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("id") Pizza updatedPizzaForm,
                        BindingResult bindingResult,
                        Model model){

        if (bindingResult.hasErrors()){
            return "pizza/edit";
        }
        pizzaRepository.save(updatedPizzaForm);
        return "redirect:/";
    }

    //Delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        pizzaRepository.deleteById(id);
        return "redirect/";
    }
}
