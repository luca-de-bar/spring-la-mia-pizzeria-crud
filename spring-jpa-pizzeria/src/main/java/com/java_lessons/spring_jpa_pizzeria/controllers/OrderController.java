package com.java_lessons.spring_jpa_pizzeria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ordina")
public class OrderController {

    @GetMapping
    public String ordina (){
        return "order";
    }
}
