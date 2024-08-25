package com.java_lessons.spring_jpa_pizzeria.controllers;

import com.java_lessons.spring_jpa_pizzeria.models.Menu;
import com.java_lessons.spring_jpa_pizzeria.models.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private MenuRepository menuRepository;

    //HOME
    @GetMapping("/")
    public String home (Model model){
        //Prendo i dati da consegnare al modello
        List<Menu> result = menuRepository.findAll();

        //Inserisco dati nel modello
        model.addAttribute("menu",result);
        return "home";
    }
}
