package com.starwars.films.di;

import com.starwars.core.di.AppComponent;
import com.starwars.films.di.scope.FilmsComponentScope;

import dagger.Component;

@FilmsComponentScope
@Component(dependencies = AppComponent.class, modules = { FilmsModule.class })
public interface FilmsComponent {

}
