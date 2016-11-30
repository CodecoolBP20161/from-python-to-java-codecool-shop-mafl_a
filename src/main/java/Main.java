import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.Mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.Mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.Mem.SupplierDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.model.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Product category routes
        get("/category/:catId", ProductController::renderProducts, new ThymeleafTemplateEngine());

//        Longer version of the snippet above only to understand better how it works
//        get("/category/:id", (Request req, Response res) -> {
//            return new ThymeleafTemplateEngine().render( ProductController.renderAll(req, res) );
//        });

        // Product supplier routes
        get("/supplier/:supId", ProductController::renderProducts, new ThymeleafTemplateEngine());

        // Always add generic routes to the end
        get("/", ProductController::renderAll, new ThymeleafTemplateEngine());

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung", "Smartphones");
        supplierDataStore.add(samsung);
        Supplier apple = new Supplier("Apple", "Electronic devices (Smartphones, Tablets, Notebooks)");
        supplierDataStore.add(apple);
        
        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory smartphone = new ProductCategory("Smartphone", "Hardware", "A pocket sized device, thin, flat mobile computer with a touchscreen display, that lets you stay in touch with the world.");
        productCategoryDataStore.add(smartphone);


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Samsung Galaxy Note 7", 899, "USD", "Samsung's top-of-the-line smartphone. This product is a total BLAST!", smartphone, samsung));
        productDataStore.add(new Product("Apple iPhone 6 Plus Space Gray 64GB", 529.99f, "USD", "Apple's best smartphone, with 64GB of storage and a 3.5mm jack port.", smartphone, apple));
    }


}
