package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private ArrayList<LineItem> lineItems = new ArrayList<>();

    public void setLineItems(ArrayList<LineItem> products) {
        this.lineItems = products;
    }

    public ArrayList getLineItems() {
        return this.lineItems;
    }

    // returns the total price in cart (lineitem price*quantity)
    public float getCartTotalPrice() {
        float total = 0f;
        for (int i = 0; i < lineItems.size(); i++) {
            total += lineItems.get(i).getTotalPrice();
        }
        return total;
    }

    // returns number of items in cart
    public int getTotalQuantity() {
        int total = 0;
        for (int i = 0; i < lineItems.size(); i++) {
            total += lineItems.get(i).getQuantity();
        }
        return total;
    }

    // adds a lineitem with a new id
    public void addLineItems(LineItem lineItem) {
        lineItem.setId(lineItems.size() + 1);
        lineItems.add(lineItem);
    }

}
