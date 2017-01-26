package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.http.NoHttpResponseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController {

    public static int counter = 0;

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

    public static ModelAndView completeOrder(Request req, Response res) throws IOException, URISyntaxException {
        Order order;
        Map userData = new HashMap();
        order = req.session().attribute("order");
        User user = order.getUser();
        System.out.println(user);
        String userName = user.getFirstName() + " " + user.getLastName();

        userData.put("name", userName);
        userData.put("country", user.getCountry());
        userData.put("city", user.getCity());
        userData.put("address", user.getAddress());
        userData.put("zipcode", user.getZipCode());

        PdfServiceController controller = PdfServiceController.getInstance();
        Map params = new HashMap();
        counter += 1;
        try {
            controller.sendData(userData);
        } catch (IOException e) {
            while (counter < 10) {
                completeOrder(req, res);
            }
            throw new IOException("Service is not available");
        }
        controller.getPdfLabel();
        return new ModelAndView(params, "checkout_success");
    }
}
