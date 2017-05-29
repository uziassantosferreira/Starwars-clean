package com.starwars.core.application;

import android.app.Application;

import com.starwars.core.database.di.DatabaseModule;
import com.starwars.core.di.AppComponent;
import com.starwars.core.di.AppModule;
import com.starwars.core.di.DaggerAppComponent;
import com.starwars.core.networking.di.NetworkModule;

public class StarWarsApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependency();

    }

    private void injectDependency() {
        appComponent = DaggerAppComponent
                .builder()
                .databaseModule(new DatabaseModule())
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
        .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
