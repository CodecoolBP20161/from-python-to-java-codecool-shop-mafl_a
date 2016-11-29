package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kekesaron on 2016.11.29..
 */
public class ProductDaoTest {
    ProductDao productDao;
    Supplier amazon;
    ProductCategory tablet;
    Product product;

    @Before
    public void setUp(){
        productDao = ProductDaoMem.getInstance();
        amazon = new Supplier("Amazon", "Digital content and services");
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        product = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDao.add(product);
    }

    @Test
    public void testAdd(){
        assertEquals(1, productDao.getAll().size());
    }

    @Test
    public void testFind(){
        assertEquals(product, productDao.find(product.getId()));
    }


}