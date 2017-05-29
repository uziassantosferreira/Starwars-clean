package com.starwars.core.database.di;

import android.app.Application;

import com.starwars.BuildConfig;
import com.starwars.Models;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.meta.EntityModel;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.Configuration;
import io.requery.sql.ConfigurationBuilder;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

@Module
@Singleton
public class DatabaseModule {

    @Provides
    ReactiveEntityStore<Persistable> provideDatabase(Configuration configuration) {
        return ReactiveSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));
    }

    @Provides
    Configuration provideConfiguration(DatabaseSource source, EntityModel entityModel) {
        return new ConfigurationBuilder(source, entityModel)
                .useDefaultLogging()
                .setWriteExecutor(Executors.newSingleThreadExecutor())
                .build();
    }

    @Provides
    EntityModel provideModels() {
        return Models.DEFAULT;
    }

    @Provides
    DatabaseSource provideDatabaseSource(Application application, EntityModel entityModel) {
        final DatabaseSource source = new DatabaseSource(application, entityModel, 1);
        if (BuildConfig.DEBUG) {
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
        }
        return source;
    }

}