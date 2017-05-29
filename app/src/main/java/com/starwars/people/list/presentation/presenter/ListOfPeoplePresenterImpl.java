package com.starwars.people.list.presentation.presenter;

import com.starwars.people.domain.usecase.GetListPeople;
import com.starwars.people.domain.usecase.GetPerson;
import com.starwars.people.list.presentation.mapper.PresentationPersonMapper;
import com.starwars.people.list.presentation.view.ListOfPeopleView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListOfPeoplePresenterImpl extends ListOfPeoplePresenter {

    private final GetListPeople getListPeople;
    private GetPerson getPerson;
    private ListOfPeopleView view;

    public ListOfPeoplePresenterImpl(GetListPeople getListPeople, GetPerson getPerson){
        this.getListPeople = getListPeople;
        this.getPerson = getPerson;
    }

    @Override
    public void attachTo(ListOfPeopleView view) {
        this.view = view;
        loadList();
    }

    private void loadList() {
        getCompositeDispose().add(getListPeople.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(PresentationPersonMapper.transformFrom())
                .subscribe(person -> view.addPresentationPerson(person))
        );
    }

    @Override
    public void searchByLink(String link) {
        getPerson(link);
    }

    @Override
    public void fabLinkClicked() {
            view.openCustomDialogFabLink();
    }

    @Override
    public void fabQrcodeClicked() {
        view.openQrcodeView();
    }

    @Override
    public void resultMaterialBarcode(String rawValue) {
        getPerson(rawValue);
    }

    @Override
    public void clickedOnItem(String url) {
        view.goToDetails(url);
    }

    private void getPerson(String url){
        getCompositeDispose().add(getPerson.setUrl(url)
                .run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(PresentationPersonMapper.transformFrom())
                .subscribe(person -> {
                        view.addPresentationPerson(person);
                        view.goToDetails(person.url());
                        }, throwable -> view.showFailureAddPerson()));
    }

}
