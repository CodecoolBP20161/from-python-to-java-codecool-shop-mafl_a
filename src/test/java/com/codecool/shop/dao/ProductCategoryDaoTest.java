package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kekesaron on 2016.11.29..
 */
public class ProductCategoryDaoTest {
    ProductCategoryDao productCategoryDao;
    ProductCategory tablet;
    ProductCategory smartphone;
    List<ProductCategory> productCategoryList = new ArrayList<>();

    @Before
    public void setUp() {
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        smartphone = new ProductCategory("Smartphone", "Hardware", "A pocket sized device, thin, flat mobile computer with a touchscreen display, that lets you stay in touch with the world.");
        productCategoryDao.add(tablet);
        productCategoryDao.add(smartphone);
    }

    @After
    public void tearDown() {
        productCategoryDao.getAll().clear();
    }

    @Test
    public void testAdd() {
        assertEquals(2, productCategoryDao.getAll().size());
    }

    @Test
    public void testFind() {
        assertEquals(tablet,productCategoryDao.find(tablet.getId()));
    }

    @Test
    public void testGetAll() {
        productCategoryList.add(tablet);
        productCategoryList.add(smartphone);
        assertEquals(productCategoryList, productCategoryDao.getAll());
    }

}