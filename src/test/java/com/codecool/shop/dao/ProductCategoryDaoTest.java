package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
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
public class ProductCategoryDaoTest {
    static ProductCategoryDao productCategoryDao;
    static ProductCategory tablet;
    static ProductCategory smartphone;
    static List<ProductCategory> productCategoryList = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        smartphone = new ProductCategory("Smartphone", "Hardware", "A pocket sized device, thin, flat mobile computer with a touchscreen display, that lets you stay in touch with the world.");
        productCategoryDao.add(tablet);
        productCategoryDao.add(smartphone);
        productCategoryList.add(tablet);
        productCategoryList.add(smartphone);
    }

    @Test
    public void testAdd() {
        assertEquals(2, productCategoryDao.getAll().size());
    }

    @Test
    public void testFind() {
        assertEquals(tablet, productCategoryDao.find(tablet.getId()));
    }

    @Test
    public void testFind_NoReturn() {
        assertEquals(null, productCategoryDao.find(3));
    }

    @Test
    public void testGetAll() {
        assertEquals(productCategoryList, productCategoryDao.getAll());
    }
}