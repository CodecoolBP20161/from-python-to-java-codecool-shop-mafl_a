package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class ProductCategory extends BaseModel {

    @Getter @Setter
    private String department;

    @Getter @Setter
    private ArrayList<Product> products;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public ProductCategory(int id, String name, String description, String department) {
        super(id, name, description);
        this.department = department;
    }

    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }

}