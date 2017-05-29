package com.starwars.core.networking.http;

import okhttp3.Request;

class HttpRequestBuilder {

    private final Request request;

    HttpRequestBuilder(Request request) {
        this.request = request;
    }

    public Request build() {
        return request.newBuilder()
                .build();
    }

}
