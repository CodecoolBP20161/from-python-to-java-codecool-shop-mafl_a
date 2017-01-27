package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.commons.io.IOUtils;
import org.apache.http.NoHttpResponseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Date.*;

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

        InputStream inputStream;
        OutputStream os;

        inputStream = controller.getPdfLabel();
        String date = new Date().toString();
        os = new FileOutputStream(date + ".pdf");
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.close();

        return new ModelAndView(params, "checkout_success");
    }
}
