package com.codecool.shop.controller;


import org.apache.http.NoHttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class PdfServiceController {

    private static final String SERVICE_URL = "http://localhost:4567";

    private static PdfServiceController INSTANCE;

    public static PdfServiceController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PdfServiceController();
        }
        return INSTANCE;
    }

    private PdfServiceController() {
    }

    public String sendData(Map userData) throws URISyntaxException, IOException {
        JSONObject userDataJson = new JSONObject(userData);
        String data = userDataJson.toString();
        URIBuilder builder = new URIBuilder(SERVICE_URL);
        return execute(builder.build(), data);
    }

    private String execute(URI uri, String data) throws IOException{
        StringEntity json = new StringEntity(data);
        return Request.Post(uri)
                .body(json)
                .setHeader("Content-type", "application/json")
                .execute()
                .returnContent()
                .asString();
    }

    public InputStream getPdfLabel() throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(SERVICE_URL);
        return executeGet(builder.build());
    }

    private InputStream executeGet(URI uri) throws IOException {
        return Request.Get(uri).addHeader("Accept-charset", "UTF-8")
                .execute().returnResponse().getEntity().getContent();
    }
}
