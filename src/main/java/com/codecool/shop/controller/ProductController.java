package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        int catId = Integer.parseInt(req.params(":catId"));
        int supId = Integer.parseInt(req.params(":supId"));

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(catId));
        params.put("supplier", productSupplierDataStore.find(supId));
        params.put("categories",productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(catId)));
        params.put("products", productDataStore.getBy(productSupplierDataStore.find(supId)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderAll(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("categories",productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

}
