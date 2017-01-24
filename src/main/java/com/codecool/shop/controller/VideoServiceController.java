package com.codecool.shop.controller;

<<<<<<< HEAD
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
=======
import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class VideoServiceController {

    private static final String SERVICE_URL = "http://localhost:60020";
    private static VideoServiceController INSTANCE;

    public static VideoServiceController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VideoServiceController();
        }
        return INSTANCE;
    }

    private VideoServiceController() {}


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

>>>>>>> e9ac7d4d789fccb065c874e5daae2c7abb7ca1c5
}
