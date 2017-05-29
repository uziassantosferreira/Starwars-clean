package com.starwars.films.di;

import com.starwars.core.di.AppComponent;
import com.starwars.films.data.repository.datasource.orm.FilmsOrmDataSourceTest;

import dagger.Component;

@FilmsScopeTest
@Component(dependencies = AppComponent.class)
public interface FilmsComponentTest {
    void inject(FilmsOrmDataSourceTest filmsOrmDataSourceTest);
}
