package com.production.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class HttpGet {
    public static String fetchSyncGetRequest(String url) {
        String responseBody = null;
        try (InputStream is = new URL(url).openStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader bf = new BufferedReader(inputStreamReader);
            responseBody = bf.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}
