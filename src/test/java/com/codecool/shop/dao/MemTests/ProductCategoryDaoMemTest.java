package com.codecool.shop.dao.MemTests;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.Mem.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kekesaron on 2016.11.29..
 */

public class ProductCategoryDaoMemTest {
    private static ProductCategoryDao productCategoryDaoMem;
    private static ProductCategory tablet;
    private static ProductCategory smartphone;
    private static List<ProductCategory> productCategoryListMem = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        smartphone = new ProductCategory("Smartphone", "Hardware", "A pocket sized device, thin, flat mobile computer with a touchscreen display, that lets you stay in touch with the world.");
        productCategoryDaoMem.add(tablet);
        productCategoryDaoMem.add(smartphone);
        productCategoryListMem.add(tablet);
        productCategoryListMem.add(smartphone);
    }


    @Test
    public void testAddToDaoMem() {
        assertEquals(2, productCategoryDaoMem.getAll().size());
    }

    @Test
    public void testFindInDaoMem() {
        assertEquals(tablet, productCategoryDaoMem.find(tablet.getId()));
    }

    @Test
    public void testFindInDaoMem_NoReturn() {
        assertEquals(null, productCategoryDaoMem.find(3));
    }

    @Test
    public void testGetAllFromDaoMem() {
        assertEquals(productCategoryListMem, productCategoryDaoMem.getAll());
    }

}