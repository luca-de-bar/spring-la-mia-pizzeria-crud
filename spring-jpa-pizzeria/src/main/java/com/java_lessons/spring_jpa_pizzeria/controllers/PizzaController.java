package com.java_lessons.spring_jpa_pizzeria.controllers;

import com.java_lessons.spring_jpa_pizzeria.models.Offer;
import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.services.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;


    // SEARCH PIZZA
    @GetMapping("/search")
    public String findByName(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Pizza> pizzas;

        if (name == null || name.isEmpty()) {
            pizzas = pizzaService.findAll();
        } else {
            pizzas = pizzaService.findPizzaByName(name);
        }
        model.addAttribute("pizzas", pizzas);
        return "pizza/index";
    }

    //Index
    @GetMapping
    public String index (Model model){
        List<Pizza> pizzaList = pizzaService.findAll();
        model.addAttribute("pizzas",pizzaList);
        return "pizza/index";
    }

    //Show
    @GetMapping("/show/{id}")
    public String show (@PathVariable("id") Long id, Model model){
        model.addAttribute("pizza",pizzaService.findById(id));
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
                         RedirectAttributes attributes){

        if(bindingResult.hasErrors()){
            return "pizza/create";
        }
        pizzaService.save(formPizza);
        attributes.addFlashAttribute("successMessage","La Pizza " + formPizza.getName() + " è stata creata");
        return "redirect:/";
    }

    //Edit + Update
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,Model model){
        model.addAttribute("pizza",pizzaService.findById(id));
        return "pizza/edit";
    }


    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza updatedPizzaForm,
                        BindingResult bindingResult,
                        RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return "pizza/edit";
        }
        pizzaService.save(updatedPizzaForm);
        attributes.addFlashAttribute("successMessage","La Pizza " + updatedPizzaForm.getName() + " è stata modificata");
        return "redirect:/";
    }

    //Delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        pizzaService.delete(id);
        attributes.addFlashAttribute("alertMessage","La Pizza con ID " + id + " è stata eliminata");
        return "redirect:/";
    }

    //Create New Offer
    @GetMapping("/{id}/offer")
    public String offer(@PathVariable("id")Long id,Model model){

        //Set offer start date to LocalDateNow()
        Offer offer = new Offer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDate.now().format(formatter);
        offer.setStartDate(formattedDate);

        //Pass Pizza to form
        offer.setPizza(pizzaService.findById(id));

        model.addAttribute("offer",offer);
        return "/offers/create";
    }
}
