package com.starwars.people.details.presentation.presenter;

import com.starwars.people.details.presentation.mapper.PresentationPersonMapper;
import com.starwars.people.details.presentation.view.PersonDetailsView;
import com.starwars.people.domain.usecase.GetPerson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonDetailsPresenterImpl extends PersonDetailsPresenter {

    private PersonDetailsView view;
    private GetPerson getPerson;

    public PersonDetailsPresenterImpl(GetPerson getPerson){
        this.getPerson = getPerson;
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
                .subscribe(person -> {view.showDetails(person);});
    }

}
