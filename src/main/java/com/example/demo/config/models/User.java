package com.example.demo.config.models;


import com.example.demo.config.models.Car;
import jakarta.persistence.*;

@Entity
@Table(name = "my_users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = ("number"))
    private String number;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;

    public User() {

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User(String name, String lastName, String number, Car car) {
        this.name = name;
        this.lastName = lastName;
        this.number = number;
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number='" + number + '\'' +
                ", car=" + car +
                '}';
    }
}
