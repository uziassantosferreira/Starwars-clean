package com.starwars.people.di;

import com.starwars.core.di.AppComponent;
import com.starwars.people.di.scope.PeopleComponentScope;

import dagger.Component;

@PeopleComponentScope
@Component(dependencies = AppComponent.class, modules = { PeopleModule.class })
public interface PeopleComponent {
}
