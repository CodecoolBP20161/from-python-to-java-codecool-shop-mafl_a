package com.codecool.shop.controller;



import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;


public class VideoServiceControllerTest {

    @Test
    public void testGetJson() throws IOException, URISyntaxException {
        String product = "iphone";
        VideoServiceController controller = VideoServiceController.getInstance();
        System.out.println(controller.getJson(product));
    }
}