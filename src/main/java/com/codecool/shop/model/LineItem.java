package com.codecool.shop.model;

public class LineItem {
    private int id;
    private Product product;
    private int quantity = 1;

    public LineItem(final Product product){
        this.product = product;
    }

    public int getId() {return this.id; }

    public void setId(int id) {this.id = id;}

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() { return this.quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    // returns the total price of the lineitem
    public float getTotalPrice() {
        return product.getDefaultPrice() * this.quantity;
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
