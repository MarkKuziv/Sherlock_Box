package com.sherlock.box.dto;

public class OrderDTO {

    private Long id;
    private String type;

    public OrderDTO() {
    }

    public OrderDTO(Long id, String type) {
        this.id = id;
        this.type = type;
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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

