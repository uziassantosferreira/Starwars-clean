package com.starwars.films.di;

import android.support.v4.app.FragmentManager;

import com.starwars.films.data.repository.FilmsRepository;
import com.starwars.films.data.repository.FilmsRepositoryImpl;
import com.starwars.films.data.repository.datasource.FilmsDataSource;
import com.starwars.films.data.repository.datasource.networking.FilmsRestApi;
import com.starwars.films.data.repository.datasource.networking.FilmsRestApiDataSource;
import com.starwars.films.data.repository.datasource.orm.FilmsOrmDataSource;
import com.starwars.films.domain.usecase.GetFilm;
import com.starwars.films.domain.usecase.GetFilmImpl;
import com.starwars.films.presentation.adapter.FilmPageAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import retrofit2.Retrofit;

@Module
public class FilmsModule {

    private FragmentManager fragmentManager;

    public FilmsModule(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

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

    @Provides
    FilmPageAdapter providesFilmPageAdapter(){
        return new FilmPageAdapter(fragmentManager);
    }

    @Provides
    GetFilm providesGetFilm(FilmsRepository filmsRepository){
        return new GetFilmImpl(filmsRepository);
    }

    @Provides
    FilmsRepository providesFilmsRepository(@Named("FilmsOrmDataSource")
                                                    FilmsDataSource requeryDataSource,
                                            @Named("FilmsRestApiDataSource")
                                                    FilmsDataSource restApiDataSource){
        return new FilmsRepositoryImpl(requeryDataSource, restApiDataSource);
    }

}
