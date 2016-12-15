package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


public class Supplier extends BaseModel {

    @Getter @Setter
    private ArrayList<Product> products;

    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }

    public Supplier(int id, String name, String description) {
        super(id, name, description);
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}