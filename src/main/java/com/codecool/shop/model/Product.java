package com.codecool.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

public class Product extends BaseModel {

    @Getter @Setter
    private float defaultPrice;

    @Getter @Setter
    private Currency defaultCurrency;

    @Getter @Setter
    private ProductCategory productCategory;

    @Getter @Setter
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
