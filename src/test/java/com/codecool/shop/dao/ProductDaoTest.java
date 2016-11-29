package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kekesaron on 2016.11.29..
 */
public class ProductDaoTest {
    static ProductDao productDao;
    static Supplier amazon;
    static ProductCategory tablet;
    static ProductCategory smartphone;
    static Product product;
    static Product product2;
    static List<Product> productList = new ArrayList<>();

    @BeforeClass
    public static void setUp(){
        productDao = ProductDaoMem.getInstance();
        amazon = new Supplier("Amazon", "Digital content and services");
        smartphone = new ProductCategory("Smartphone", "Hardware", "A pocket sized device, thin, flat mobile computer with a touchscreen display, that lets you stay in touch with the world.");
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        product = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", smartphone, amazon);
        product2 = new Product("Amazon", 53.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
    }

    @After
    public void tearDown() {
        productDao.getAll().clear();
        productList.clear();
    }

    @Test
    public void testAdd() {
        productDao.add(product);
        assertEquals(1, productDao.getAll().size());
    }

    @Test
    public void testFind() {
        productDao.add(product);
        assertEquals(product, productDao.find(product.getId()));
    }

    @Test
    public void testGetAll() {
        productDao.add(product);
        productDao.add(product2);
        productList.add(product);
        productList.add(product2);
        assertEquals(productList, productDao.getAll());
    }

    @Test
    public void testGetBySupplier() {
        productDao.add(product);
        productDao.add(product2);
        productList.add(product);
        productList.add(product2);
        assertEquals(productList, productDao.getBy(amazon));
    }

    @Test
    public void testGetByProductCategory() {
        productDao.add(product);
        productDao.add(product2);
        productList.add(product);
        assertEquals(productList, productDao.getBy(smartphone));
    }

}