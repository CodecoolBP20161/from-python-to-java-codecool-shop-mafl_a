package com.codecool.shop.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {

    @Getter
    private List<LineItem> lineItems = new ArrayList<>();


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

    // checks if a lineitem exists, if not, it creates a new
    public void checkLineItem(Product product) {
        boolean hasLineItem = false;
        if (lineItems.size() > 0) {
            for (int i = 0, len = lineItems.size(); i < len; i++) {
                LineItem item = lineItems.get(i);
                if (item.getProduct().getId() == product.getId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    hasLineItem = true;
                }
            }
            if (!hasLineItem) lineItems.add(new LineItem(product));
        } else {
            addLineItems(new LineItem(product));
        }
    }

}
