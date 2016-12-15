package com.codecool.shop.model;


import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

public class BaseModel {

    @Getter @Setter
    protected int id;

    @Getter @Setter
    protected String name;

    @Getter @Setter
    protected String description;

    public BaseModel(String name) {
        this.name = name;
    }

    public BaseModel(String name, String description) {
        this(name);
        this.description = description;
    }

    public BaseModel(int id, String name, String description) {
        this(name, description);
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }

}
