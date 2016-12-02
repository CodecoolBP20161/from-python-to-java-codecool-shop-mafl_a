package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import spark.ModelAndView;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {

        if (req.session().attribute("order") == null) {
            req.session().attribute("order", new Order());
        }

        Order sessionOrder = req.session().attribute("order");
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoJDBC.getInstance();


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
        params.put("totalItemQuantity", sessionOrder.getTotalQuantity());

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderAll(Request req, Response res) {
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoJDBC.getInstance();

        if (req.session().attribute("order") == null) {
            req.session().attribute("order", new Order());
        }
        Order sessionOrder = req.session().attribute("order");

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("totalItemQuantity", sessionOrder.getTotalQuantity());

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView addItem(Request req, Response res) {
        Order order;
        if (req.session().attribute("order") == null) {
            req.session().attribute("order", new Order());
        }
        order = req.session().attribute("order");

        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        for (Product item : productDataStore.getAll()) {
            if (item.getId() == Integer.parseInt(req.params(":prodId"))) order.checkLineItem(item);
        }
        res.redirect("/");
        return null;
    }
}
