package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.model.User;
import spark.ModelAndView;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request request, Response response) {
        Order order = request.session().attribute("order");
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoJDBC.getInstance();


        int catId = request.params(":catId")!=null ? Integer.parseInt(request.params(":catId")) : -1;
        int supId = request.params(":supId")!=null ? Integer.parseInt(request.params(":supId")) : -1;

        Map params = new HashMap<>();
        if (catId != -1) {
            params.put("category", productCategoryDataStore.find(catId));
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(catId)));
        } else if (supId != -1) {
            params.put("supplier", productSupplierDataStore.find(supId));
            params.put("products", productDataStore.getBy(productSupplierDataStore.find(supId)));
        }
        params.put("order", order);
        params.put("lineItems", order.getLineItems());
        params.put("categories",productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("totalItemQuantity", order.getTotalQuantity());

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderAll(Request request, Response response) {
        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoJDBC.getInstance();

        if (request.session().attribute("order") == null) {
            request.session().attribute("order", new Order());
        }
        Order order = request.session().attribute("order");

        Map params = new HashMap<>();
        params.put("order", order);
        params.put("lineItems", order.getLineItems());
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("totalItemQuantity", order.getTotalQuantity());

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView addItem(Request request, Response response) {
        int quantity = Integer.parseInt(request.queryParams("quantity"));
        Order order = request.session().attribute("order");

        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        for (Product item : productDataStore.getAll()) {
            if (item.getId() == Integer.parseInt(request.params(":prodId"))) {
                for (int i = 0; i <quantity ; i++) {
                    order.checkLineItem(item);
                }

            }
        }
        response.redirect("/");
        return null;
    }

    public static ModelAndView checkout(Request request, Response response) {
        Map params = new HashMap<>();
        Order order = request.session().attribute("order");
        params.put("order", order);
        return new ModelAndView(params, "checkout");
    }

    public  static ModelAndView saveUserData(Request request, Response response) throws IOException, URISyntaxException {
        Map params = new HashMap<>();
        Order order = request.session().attribute("order");
        order.setUser(new User(request.session().id().charAt(0)));

        User user = order.getUser();

        params.put("order", order);
        user.setFirstName(request.queryParams("firstName"));
        System.out.println(user.getFirstName());
        user.setLastName(request.queryParams("lastName"));
        user.setCity(request.queryParams("city"));
        user.setAddress(request.queryParams("address"));
        user.setCountry(request.queryParams("country"));
        user.setEmail(request.queryParams("email"));
        user.setPhoneNumber(request.queryParams("phone"));
        user.setZipCode(request.queryParams("zipcode"));
        return  new ModelAndView(params, "checkout");
    }

    public static ModelAndView renderProductPage(Request request, Response response) throws IOException, URISyntaxException {
        Map params = new HashMap<>();

        // find the product by id in the product data store
        int productId = Integer.parseInt(request.params(":id"));
        ProductDao productDataStore = ProductDaoJDBC.getInstance();

        Product singleProduct = productDataStore.find(productId);
        String productName = singleProduct.getName();

        VideoServiceController serviceController = VideoServiceController.getInstance();
        String json = serviceController.getJson(productName);
        List<String> embedVideoLinks = serviceController.getProductVideos(json);

        Order order = request.session().attribute("order");
        params.put("order", order);

        params.put("product", singleProduct);
        params.put("embed_codes", embedVideoLinks);
        params.put("totalItemQuantity", order.getTotalQuantity());

        return new ModelAndView(params, "product/product");


    }
}
