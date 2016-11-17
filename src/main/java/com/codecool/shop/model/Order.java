package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order {

    private List<LineItem> lineItems = new ArrayList<>();

//    public void setLineItems(List<LineItem> products) {
//        this.lineItems = products;
//    }

    public List getLineItems() {
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

    public ArrayList productNames() {
        ArrayList<Product> prodNames = new ArrayList<>();
        for (int i = 0; i < lineItems.size(); i++) {
            prodNames.add(lineItems.get(i).getProduct());
        }
        return prodNames;
    }

    // adds a lineitem with a new id
    public void addLineItems(LineItem lineItem) {
        lineItem.setId(lineItems.size() + 1);
        lineItems.add(lineItem);
    }

    // checks if a lineitem exists, if not, it creates a new
    public void checkLineItem(Product product) {
        if (lineItems.size() != 0) {
            if (productNames().contains(product)) {
                for (int i = 0; i < lineItems.size(); i++) {
                    LineItem item = lineItems.get(i);
                    System.out.println(item);
                    if (product == item.getProduct()) {
                        int oldQuantity = item.getQuantity();
                        item.setQuantity(oldQuantity + 1);
                        lineItems.set(i, item);
                        System.out.println(item.getQuantity() + "blabla");
                    }
                }
            } else {
                addLineItems(new LineItem(product));
            }
        } else {
            addLineItems(new LineItem(product));
        }
        System.out.println(lineItems);
    }
}

