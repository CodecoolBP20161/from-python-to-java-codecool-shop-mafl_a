package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {


    @Getter @Setter
    private int id;

    @Getter
    private User user;

    @Getter @Setter
    private List<LineItem> lineItems = new ArrayList<>();

    // returns the total price in cart (lineitem price*quantity)
    public double getCartTotalPrice() {
        float total = 0f;
        for (int i = 0; i < lineItems.size(); i++) {
            total += lineItems.get(i).getTotalPrice();
        }
        return (double) Math.round(total * 100.0) / 100.0;
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
        lineItem.setId(lineItem.getProduct().getId());
        lineItems.add(lineItem);
    }

    /**
     * This method is currently not used because
     * we handle orders in sessions, but later on
     * we will need it to get unique id per order
     * per lineitem.
     */
    public int getHighestId(){
        int maxId;
        if (lineItems.size() > 0) {
            maxId = lineItems.get(0).getId();
        } else {
            maxId = 1;
        }
        for (LineItem lineItem : lineItems) {
            if (lineItem.getId() > maxId){
                maxId = lineItem.getId();
            }
        }
        return maxId;
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
            if (!hasLineItem) addLineItems(new LineItem(product));
        } else {
            addLineItems(new LineItem(product));
        }
    }

    public void deleteLineItem(LineItem lineItem){
        lineItems.remove(lineItem);
    }
}
