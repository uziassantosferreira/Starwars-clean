package com.starwars.people.details.presentation.presenter;

import com.starwars.films.domain.usecase.GetFilm;
import com.starwars.films.presentation.mapper.PresentationFilmMapper;
import com.starwars.people.details.presentation.mapper.PresentationPersonMapper;
import com.starwars.people.details.presentation.view.PersonDetailsView;
import com.starwars.people.domain.usecase.GetPerson;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonDetailsPresenterImpl extends PersonDetailsPresenter {

    private PersonDetailsView view;
    private GetPerson getPerson;
    private GetFilm getFilm;

    public PersonDetailsPresenterImpl(GetPerson getPerson, GetFilm getFilm){
        this.getPerson = getPerson;
        this.getFilm = getFilm;
    }

    @Override
    public void attachTo(PersonDetailsView view) {
        this.view = view;
    }

    public void setUrl(String url) {
        getPerson.setUrl(url)
                .run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(PresentationPersonMapper::transformFrom)
                .subscribe(person -> {view.showDetails(person);
                    addFilms(person.films());});
    }


    private void addFilms(List<String> films) {
        getFilm.setUrl(films)
                .run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(PresentationFilmMapper::transformFrom)
                .subscribe(film -> view.addFilm(film));
    }

}
