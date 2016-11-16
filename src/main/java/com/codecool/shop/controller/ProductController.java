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


        int catId = req.params(":catId")!=null ? Integer.parseInt(req.params(":catId")) : -1;
        int supId = req.params(":supId")!=null ? Integer.parseInt(req.params(":supId")) : -1;

        Map params = new HashMap<>();
        if (catId != -1) {
            params.put("category", productCategoryDataStore.find(catId));
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(catId)));
        } else if (supId != -1) {
            params.put("supplier", productSupplierDataStore.find(supId));
            params.put("products", productDataStore.getBy(productSupplierDataStore.find(supId)));
        }
        params.put("categories",productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());

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
