package com.starwars.people.di;

import com.starwars.core.di.AppComponent;
import com.starwars.people.data.repository.datasource.orm.PeopleOrmDataSourceTest;

import dagger.Component;

@PeopleScopeTest
@Component(dependencies = AppComponent.class, modules = {})
public interface PeopleComponentTest {
    void inject(PeopleOrmDataSourceTest peopleRequeryDataSourceTest);
}
