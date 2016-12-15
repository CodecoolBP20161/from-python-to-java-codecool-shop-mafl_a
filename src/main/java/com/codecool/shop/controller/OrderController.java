package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OrderController {
    public static ModelAndView updateCart(Request req, Response res) {
        Order order;
        order = req.session().attribute("order");

        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        for (Product item : productDataStore.getAll()) {
            if (item.getId() == Integer.parseInt(req.params(":prodId"))) order.checkLineItem(item);
        }
        res.redirect("/");
        return null;
    }
}
