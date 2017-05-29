package com.starwars.people.details.presentation.view;

import com.starwars.core.presentation.LoadingView;
import com.starwars.films.presentation.model.PresentationFilm;
import com.starwars.people.details.presentation.model.PresentationPerson;

public interface PersonDetailsView extends LoadingView {
    void showDetails(PresentationPerson person);
    void addFilm(PresentationFilm film);
}
