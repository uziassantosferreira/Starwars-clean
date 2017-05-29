package com.starwars.people.details.presentation.presenter;

import com.starwars.core.presentation.Presenter;
import com.starwars.people.details.presentation.view.PersonDetailsView;
import com.starwars.people.domain.usecase.GetPerson;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class PersonDetailsPresenter extends Presenter<PersonDetailsView> {

    public abstract void setUrl(String url);
}
