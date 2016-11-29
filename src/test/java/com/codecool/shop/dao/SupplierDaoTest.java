package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SupplierDaoTest {

    static SupplierDao supplierDao;
    static Supplier amazon;
    static Supplier lenovo;
    static List<Supplier> supplierList = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        supplierDao = SupplierDaoMem.getInstance();
        amazon = new Supplier("Amazon", "Digital content and services");
        lenovo = new Supplier("Lenovo", "Computers");
        supplierList.add(amazon);
        supplierList.add(lenovo);
        supplierDao.add(amazon);
        supplierDao.add(lenovo);
    }

    @Test
    public void testAdd() {
        assertEquals(2, supplierDao.getAll().size());
    }

    @Test
    public void find() {
        assertEquals(amazon, supplierDao.find(amazon.getId()));
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(supplierList, supplierDao.getAll());
    }

}