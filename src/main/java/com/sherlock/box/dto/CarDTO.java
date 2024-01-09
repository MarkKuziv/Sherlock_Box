package com.sherlock.box.dto;

import java.util.List;

public class CarDTO {

    private Long id;
    private String name;
    private String model;
    private float engineCapacity;

    public CarDTO() {
    }

    public CarDTO(Long id, String name, String model, float engineCapacity) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.engineCapacity = engineCapacity;
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

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", engineCapacity=" + engineCapacity +
                '}';
    }
}
