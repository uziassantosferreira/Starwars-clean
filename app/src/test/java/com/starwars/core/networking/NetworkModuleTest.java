package com.starwars.core.networking;

import com.starwars.core.networking.di.NetworkModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import okhttp3.Interceptor;

public class NetworkModuleTest extends NetworkModule {

    List<Interceptor> providesOkHttpInterceptors(@Named("httpHeadersInterceptor") Interceptor httpHeadersInterceptor,
                                                 @Named("loggingInterceptor") Interceptor loggingInterceptor) {
        List<Interceptor> interceptors = new ArrayList<>();
        return interceptors;
    }

}
