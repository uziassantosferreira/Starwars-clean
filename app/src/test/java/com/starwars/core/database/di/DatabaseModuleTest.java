package com.starwars.core.database.di;

import android.app.Application;
import android.support.annotation.Nullable;

import com.starwars.core.database.helper.DatabaseType;

import javax.sql.CommonDataSource;

import io.requery.android.sqlite.DatabaseSource;
import io.requery.cache.EntityCacheBuilder;
import io.requery.meta.EntityModel;
import io.requery.sql.Configuration;
import io.requery.sql.ConfigurationBuilder;
import io.requery.sql.SchemaModifier;
import io.requery.sql.TableCreationMode;
import io.requery.sql.platform.SQLite;

import static org.mockito.Mockito.mock;

public class DatabaseModuleTest extends DatabaseModule {

    @Override
    Configuration provideConfiguration(@Nullable DatabaseSource source, EntityModel entityModel) {
        CommonDataSource dataSource = DatabaseType.getDataSource(new SQLite());
        Configuration configuration = new ConfigurationBuilder(dataSource, entityModel)
                .useDefaultLogging()
                .setEntityCache(new EntityCacheBuilder(entityModel)
                        .useReferenceCache(true)
                        .build())
                .build();
        SchemaModifier tables = new SchemaModifier(configuration);
        tables.dropTables();
        TableCreationMode mode = TableCreationMode.CREATE_NOT_EXISTS;
        System.out.println(tables.createTablesString(mode));
        tables.createTables(mode);
        return configuration;
    }

    @Override
    DatabaseSource provideDatabaseSource(Application application, EntityModel entityModel) {
        return mock(DatabaseSource.class);
    }

}
