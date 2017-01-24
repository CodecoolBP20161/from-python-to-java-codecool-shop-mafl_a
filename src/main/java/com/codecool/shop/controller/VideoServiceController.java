package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.net.URI;

public class VideoServiceController {
    private static final String SERVICE_URL = "http://localhost:60020";

    private static VideoServiceController INSTANCE;

    public static VideoServiceController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VideoServiceController();
        }
        return INSTANCE;
    }

    private VideoServiceController() {
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }


    public String getJson(String product) {
        return null;
    }

    public List getProductVideos(String apiJsonAsString) {
        List<String> embedCodes = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(apiJsonAsString);
        JSONObject unboxingJson = new JSONObject(jsonArray.get(0));
        JSONObject reviewJson = new JSONObject(jsonArray.get(2));

        embedCodes.add(unboxingJson.getString("embed code"));
        embedCodes.add(reviewJson.getString("embed code"));
        return embedCodes;
    }
}
