package com.starwars.core.utils.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonResourceLoader {

    private InputStream jsonResourceInputStream;
    private String resourceString;

    private JsonResourceLoader(String resourceName) {
        this.jsonResourceInputStream =
                getClass().getClassLoader().getResourceAsStream(resourceName);
    }

    public static JsonResourceLoader forResource(String resourceName) {
        return new JsonResourceLoader(resourceName);
    }

    public String getJson() throws IOException {
        if(null != this.resourceString) {
            return this.resourceString;
        }

        this.resourceString = loadResourceStream(this.jsonResourceInputStream);
        return this.resourceString;
    }

    private String loadResourceStream(InputStream resourceStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));
        StringBuilder resourceStringBuilder = new StringBuilder();

        String streamLine;
        while ((streamLine = reader.readLine()) != null) {
            resourceStringBuilder.append(streamLine).append("\n");
        }

        return resourceStringBuilder.toString();
    }

}
