package com.sherlock.box.dto;

public class UserDTO {

    private Long id;
    private String firstName;
    private String number;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String number) {
        this.id = id;
        this.firstName = firstName;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
