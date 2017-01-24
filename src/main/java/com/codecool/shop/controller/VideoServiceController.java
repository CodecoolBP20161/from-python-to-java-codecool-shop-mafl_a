package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.net.URI;

/**
 * Created by franka on 2017.01.24..
 */
public class VideoServiceController {
    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
