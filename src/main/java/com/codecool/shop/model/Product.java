package com.codecool.shop.model;

import java.util.Currency;

public class Product extends BaseModel {

    private float defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;


    public Product(String name, float defaultPrice, String defaultCurrency, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = Currency.getInstance(defaultCurrency);
        this.productCategory = productCategory;
        this.supplier = supplier;
    }

    public Product(int id, String name, float defaultPrice, Currency defaultCurrency, String description, ProductCategory productCategory, Supplier supplier) {
        super(id, name, description);
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = defaultCurrency;
        this.productCategory = productCategory;
        this.supplier = supplier;
//        this(defaultPrice, defaultCurrency, productCategory, supplier);
    }


//    public void setPrice(float price, String currency) {
//        this.defaultPrice = price;
//        this.defaultCurrency = Currency.getInstance(currency);
//    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
//        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
//        this.supplier.addProduct(this);
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
