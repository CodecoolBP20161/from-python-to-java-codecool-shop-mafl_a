package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.Mem.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoTest {
    static ProductDao productDao;
    static Supplier amazon;
    static ProductCategory tablet;
    static ProductCategory smartphone;
    static Product product;
    static Product product2;
    static List<Product> productListSupplier = new ArrayList<>();
    static List<Product> productListCategory = new ArrayList<>();


    @BeforeClass
    public static void setUp(){
        productDao = ProductDaoMem.getInstance();
        amazon = new Supplier("Amazon", "Digital content and services");
        smartphone = new ProductCategory("Smartphone", "Hardware", "A pocket sized device, thin, flat mobile computer with a touchscreen display, that lets you stay in touch with the world.");
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        product = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", smartphone, amazon);
        product2 = new Product("Amazon", 53.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDao.add(product);
        productDao.add(product2);
        productListSupplier.add(product);
        productListSupplier.add(product2);
        productListCategory.add(product);
    }

    @Test
    public void testAdd() {
        assertEquals(2, productDao.getAll().size());
    }

    @Test
    public void testFind() {
        assertEquals(product, productDao.find(product.getId()));
    }

    @Test
    public void testFind_NoReturn() {
        assertEquals(null, productDao.find(3));
    }

    @Test
    public void testGetAll() {
        assertEquals(productListSupplier, productDao.getAll());
    }

    @Test
    public void testGetBySupplier() {
        assertEquals(productListSupplier, productDao.getBy(amazon));
    }

    @Test
    public void testGetByProductCategory() {
        assertEquals(productListCategory, productDao.getBy(smartphone));
    }

    @Test
    public void testGetByProductCategory_EmptyList() {
        ProductCategory phablet = new ProductCategory("Phablet", "Hardware", "Some nice shit.");
        assertEquals(new ArrayList<>(), productDao.getBy(phablet));
    }

}