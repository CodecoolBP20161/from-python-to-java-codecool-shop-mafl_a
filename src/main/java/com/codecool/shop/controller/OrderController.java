package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class OrderController {
    public static ModelAndView updateCart(Request req, Response res) {
        Order order;
        order = req.session().attribute("order");
        ArrayList inputs = new ArrayList();

        ArrayList <LineItem> waitingForDeletion = new ArrayList();
        for (LineItem lineItem : order.getLineItems()) {
            lineItem.setQuantity(Integer.parseInt(req.queryParams(Integer.toString(lineItem.getId()))));
            if (lineItem.getQuantity() < 1) {
                waitingForDeletion.add(lineItem);
            }
        }
        for ( LineItem lineItem : waitingForDeletion){
            order.deleteLineItem(lineItem);
        }
        res.redirect("/");
        return null;
    }
}
