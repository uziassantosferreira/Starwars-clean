package com.starwars.people.di;

import com.starwars.core.di.AppComponent;
import com.starwars.films.di.FilmsModule;
import com.starwars.people.details.presentation.view.PersonDetailsActivity;
import com.starwars.people.di.scope.PeopleComponentScope;
import com.starwars.people.list.presentation.view.ListOfPeopleActivity;

import dagger.Component;

@PeopleComponentScope
@Component(dependencies = AppComponent.class, modules = { PeopleModule.class, FilmsModule.class })
public interface PeopleComponent {

    void inject(ListOfPeopleActivity listOfPeopleActivity);
    void inject(PersonDetailsActivity personDetailsActivity);

}
