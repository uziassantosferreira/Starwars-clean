package com.starwars.core.networking.http;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class HttpHeadersInterceptor implements Interceptor {

    @Inject
    public HttpHeadersInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(new HttpRequestBuilder(chain.request())
                .build());
    }

}
