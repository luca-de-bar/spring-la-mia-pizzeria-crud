package com.java_lessons.spring_jpa_pizzeria.services;

import com.java_lessons.spring_jpa_pizzeria.models.Offer;
import com.java_lessons.spring_jpa_pizzeria.models.Pizza;
import com.java_lessons.spring_jpa_pizzeria.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository repository;

    public List <Offer> findAll(){
        return repository.findAll();
    }

    public Offer findById(Long id){
        return repository.findById(id).get();
    }

    public Offer save(Offer offer){
        return repository.save(offer);
    }
}
