package com.java_lessons.spring_jpa_pizzeria.models;
import java.time.LocalDateTime;
import java.text.DecimalFormat;
import jakarta.persistence.*;

@Entity
@Table(name= "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    private double price;

    private String foto;

    private LocalDateTime insertedAt;

    @Transient
    private DecimalFormat formatter = new DecimalFormat("#,##0.00");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return formatter.format(this.price) + '€';
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(LocalDateTime insertedAt) {
        this.insertedAt = insertedAt;
    }
}
