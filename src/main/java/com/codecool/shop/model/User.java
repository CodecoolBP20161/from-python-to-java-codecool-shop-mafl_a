package com.codecool.shop.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class User {

    @Getter @Setter
    private char id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String phoneNumber;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String country;

    @Getter @Setter
    private String zipCode;

    public User(char id) {
        this.id = id;
    }

    public User(String firstName, String lastName, String email, String phoneNumber, String address,
                String city, String country, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

}
