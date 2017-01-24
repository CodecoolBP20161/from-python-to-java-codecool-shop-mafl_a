import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.OrderController;
import com.codecool.shop.controller.ProductController;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");



        get ("/checkout", ProductController::checkout, new ThymeleafTemplateEngine());

        post("/checkout", ProductController::saveUserData, new ThymeleafTemplateEngine());

        // Product category routes
        get("/category/:catId", ProductController::renderProducts, new ThymeleafTemplateEngine());

        // Product supplier routes
        get("/supplier/:supId", ProductController::renderProducts, new ThymeleafTemplateEngine());

        // Product adding route
        get("/add-product/:prodId", ProductController::addItem);

        get("/update-cart", OrderController::updateCart);

        // Always add generic routes to the end
        get("/", ProductController::renderAll, new ThymeleafTemplateEngine());

        //INSERT METHOD HERE
        get("/product", ProductController::renderProductPage, new ThymeleafTemplateEngine());

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }
}
