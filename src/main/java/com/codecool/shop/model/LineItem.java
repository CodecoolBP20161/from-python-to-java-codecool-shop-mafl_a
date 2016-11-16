package com.codecool.shop.model;

public class LineItem {
    private int id;
    private Product product;
    private int quantity = 1;

    public int getId() {return this.id; }

    public void setId(int id) {this.id = id;}

    public void setProducts(Product product) {
        this.product = product;
    }

    public Product getProducts() {
        return this.product;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getQuantity() { return this.quantity; }

    public String toString() {
        return String.format("id: %1$d, " +
                        "product: %2$s, " +
                        "quantity: %3$s, ",
                this.id,
                this.product.getName(),
                String.valueOf(this.quantity));
    }
}
