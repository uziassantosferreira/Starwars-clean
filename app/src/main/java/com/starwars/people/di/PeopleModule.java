package com.starwars.people.di;

import com.starwars.people.data.repository.datasource.PeopleDataSource;
import com.starwars.people.data.repository.datasource.networking.PeopleRestApi;
import com.starwars.people.data.repository.datasource.networking.PeopleRestApiDataSource;
import com.starwars.people.data.repository.datasource.orm.PeopleOrmDataSource;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import retrofit2.Retrofit;

@Module
public class PeopleModule {

    @Provides
    @Named("PeopleOrmDataSource")
    PeopleDataSource providesRequeryDataSource(ReactiveEntityStore<Persistable> reactiveEntityStore){
        return new PeopleOrmDataSource(reactiveEntityStore);
    }

    @Provides
    @Named("PeopleRestApiDataSource")
    PeopleDataSource providesRestApiDataSource(PeopleRestApi peopleRestApi){
        return new PeopleRestApiDataSource(peopleRestApi);
    }

    @Provides
    PeopleRestApi providesPeopleRestApi(Retrofit retrofit){
        return retrofit.create(PeopleRestApi.class);
    }

}
