package com.codecool.shop.dao.JdbcTests;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.model.ProductCategory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;


/**
 * Created by kekesaron on 2016.12.13..
 */
public class ProductCategoryDaoJdbcTest {
    private static ProductCategoryDao productCategoryDaoJDBC;
    private static ProductCategory tablet;
    private static ProductCategory smartphone;
    private static List<ProductCategory> productCategoryListJDBC = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        smartphone = new ProductCategory("Smartphone", "Hardware", "A pocket sized device, thin, flat mobile computer with a touchscreen display, that lets you stay in touch with the world.");
        productCategoryDaoJDBC.add(tablet);
        productCategoryDaoJDBC.add(smartphone);
        productCategoryListJDBC.add(tablet);
        productCategoryListJDBC.add(smartphone);
    }

    @Test
    public void testAddToDaoJDBC() {
        assertEquals(2, productCategoryDaoJDBC.getAll().size());
    }

    @Test
    public void testFindInDaoJDBC() {
        assertEquals(tablet, productCategoryDaoJDBC.find(tablet.getId()));
    }

    @Test
    public void testFindInDaoJDBC_NoReturn() {
        assertEquals(null, productCategoryDaoJDBC.find(3));
    }

    @Test
    public void testGetAllFromDaoJDBC() {
        assertEquals(productCategoryListJDBC, productCategoryDaoJDBC.getAll());
    }
}
