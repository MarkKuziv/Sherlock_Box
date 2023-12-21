package com.example.demo.config.models;

import jakarta.persistence.*;

@Entity
@Table(name = "my_orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "type")
    private String type;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_id")
    private Car car;


    public Order() {

    }

    public Order(String type, Car car) {
        this.type = type;
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", сar=" + car +
                '}';
    }
}
