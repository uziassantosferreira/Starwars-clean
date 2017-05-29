package com.starwars.films.di;

import com.starwars.films.data.repository.datasource.FilmsDataSource;
import com.starwars.films.data.repository.datasource.networking.FilmsRestApi;
import com.starwars.films.data.repository.datasource.networking.FilmsRestApiDataSource;
import com.starwars.films.data.repository.datasource.orm.FilmsOrmDataSource;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import retrofit2.Retrofit;

@Module
public class FilmsModule {

    @Provides
    @Named("FilmsOrmDataSource")
    FilmsDataSource providesormDataSource(ReactiveEntityStore<Persistable> reactiveEntityStore){
        return new FilmsOrmDataSource(reactiveEntityStore);
    }

    @Provides
    @Named("FilmsRestApiDataSource")
    FilmsDataSource providesRestApiDataSource(FilmsRestApi filmsRestApi){
        return new FilmsRestApiDataSource(filmsRestApi);
    }

    @Provides
    FilmsRestApi providesFilmsRestApi(Retrofit retrofit){
        return retrofit.create(FilmsRestApi.class);
    }

}
