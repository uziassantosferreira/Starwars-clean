package com.starwars.core.networking;

import android.app.Application;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.starwars.core.di.AppComponent;
import com.starwars.core.di.AppModule;
import com.starwars.core.di.DaggerAppComponent;
import com.starwars.core.utils.json.JsonResourceLoader;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import javax.inject.Inject;

@RunWith(MockitoJUnitRunner.class)
public abstract class MockRestApi<T> {

    @Mock
    private Application application;

    public @Inject T restApi;

    private MockWebServer server;
    private String json;
    private AppComponent testAppComponent;

    @Before
    public void setUp() throws IOException {
        setStringJson();
        setupServer();
        injectDependencies();
    }

    private void setStringJson() throws IOException{
        json = JsonResourceLoader
                .forResource(getResource())
                .getJson();
    }

    public abstract String getResource();

    private void setupServer() throws IOException {
        server = new MockWebServer();

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(json));
        server.start();
    }

    private void injectDependencies(){
        String BASE_URL = "/";

        testAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .networkModule(new NetworkModuleTest())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    public String getJson(){
        return json;
    }

    public AppComponent getTestAppComponent() {
        return testAppComponent;
    }

}
