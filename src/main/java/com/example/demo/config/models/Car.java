package com.example.demo.config.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "my_cars")
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "model")
    private String model;
    @Column (name = "engine_capacity")
    private float engineCapacity;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Order> orders;

    public Car() {

    }

    public Car(String name, String model, float engineCapacity, User user, List<Order> orders) {
        this.name = name;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.user = user;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", user=" + user +
                ", orders=" + orders +
                '}';
    }
}
