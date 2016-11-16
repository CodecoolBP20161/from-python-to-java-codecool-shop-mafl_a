package com.codecool.shop.model;

import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList<LineItem> lineItems;

    public int getId() {return this.id; }

    public void setId(int id) {this.id = id;}

    public void setLineItems(ArrayList<LineItem> products) {
        this.lineItems = lineItems;
    }

    public ArrayList getLineItems() {
        return this.lineItems;
    }

    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

}
