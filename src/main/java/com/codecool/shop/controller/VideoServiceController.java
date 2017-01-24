package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.net.URI;

public class VideoServiceController {
    private static final String SERVICE_URL = "http://localhost:60000/apivideos";

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


    public String getJson(String product) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(SERVICE_URL);
        builder.addParameter("search", product);
        System.out.println(builder);
        return execute(builder.build());
    }

    public List<String> getProductVideos(String apiJsonAsString) throws JSONException {
        JSONObject jsonObject = new JSONObject(apiJsonAsString);
        String error = jsonObject.getString("error");
        if(error != null){
            JSONObject errorJson = new JSONObject(error);
            throw new JSONException(errorJson.getString("error message"));
        } else {
            JSONArray jsonArray = new JSONArray(apiJsonAsString);
            List<String> embedCodes = new ArrayList<>();
            JSONObject unboxingJson = new JSONObject(jsonArray.get(0));
            JSONObject reviewJson = new JSONObject(jsonArray.get(2));

            embedCodes.add(unboxingJson.getString("embed code"));
            embedCodes.add(reviewJson.getString("embed code"));
        return embedCodes;
        }
    }
}
