package com.starwars.core.di;

import android.app.Application;

import com.starwars.core.database.di.DatabaseModule;
import com.starwars.core.networking.di.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DatabaseModule.class
})
public interface AppComponent {

    Retrofit retrofit();
    Application application();
    ReactiveEntityStore<Persistable> database();
}
