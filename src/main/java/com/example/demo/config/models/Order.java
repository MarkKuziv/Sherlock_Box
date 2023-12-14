package com.example.demo.config.models;

import com.example.demo.config.models.Car;
import jakarta.persistence.*;

@Entity
@Table(name = "my_orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "car", nullable = false)
    private Car сar;


    public Order() {

    }

    public Order(String type, Car сar) {
        this.type = type;
        this.сar = сar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Car getСar() {
        return сar;
    }

    public void setСar(Car сar) {
        this.сar = сar;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", сar=" + сar +
                '}';
    }
}
