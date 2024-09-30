package com.java_lessons.spring_jpa_pizzeria.repositories;

import com.java_lessons.spring_jpa_pizzeria.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository <Offer, Long> {
}
