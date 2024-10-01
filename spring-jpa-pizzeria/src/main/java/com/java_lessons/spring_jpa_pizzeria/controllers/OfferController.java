package com.java_lessons.spring_jpa_pizzeria.controllers;

import com.java_lessons.spring_jpa_pizzeria.models.Offer;
import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.services.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    //index
    @GetMapping
    public String index(Model model){
        model.addAttribute("offers",offerService.findAll());
        return "/offers/index";
    }

    //store
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer formOffer,
                        BindingResult bindingResult,
                        RedirectAttributes attributes,
                        Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("offer",formOffer);
            return "/offers/create";
        }
        offerService.save(formOffer);
        attributes.addFlashAttribute("successMessage","L'offerta " + formOffer.getTitle() + " è stata salvata");
        return "redirect:/offers";
    }

    //edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id, Model model){
        model.addAttribute("offer",offerService.findById(id));
        return "offers/edit";
    }

    //update
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") Offer updatedFormOffer,
                         BindingResult bindingResult,
                         RedirectAttributes attributes,
                         Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("offer",updatedFormOffer);
            return "/offers/edit";
        }
        offerService.save(updatedFormOffer);
        attributes.addFlashAttribute("successMessage","L'offerta " + updatedFormOffer.getTitle() + "è stata modificata con successo");
        return "redirect:/offers";
    }

}
