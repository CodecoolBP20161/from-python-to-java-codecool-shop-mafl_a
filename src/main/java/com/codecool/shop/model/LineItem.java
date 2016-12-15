package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

public class LineItem {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private Product product;

    @Getter @Setter
    private int quantity;

    public LineItem(Product product){
        this.product = product;
        quantity = 1;
    }

    // returns the total price of the line item
    public float getTotalPrice() {
        return product.getDefaultPrice() * quantity;
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "product: %2$s, " +
                        "quantity: %3$s, ",
                this.id,
                this.product.getName(),
                String.valueOf(this.quantity));
    }
}
