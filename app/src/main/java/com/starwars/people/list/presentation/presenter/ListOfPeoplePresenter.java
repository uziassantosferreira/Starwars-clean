package com.starwars.people.list.presentation.presenter;

import com.starwars.core.presentation.Presenter;
import com.starwars.people.list.presentation.view.ListOfPeopleView;

public abstract class ListOfPeoplePresenter extends Presenter<ListOfPeopleView> {
    public abstract void searchByLink(String link);
    public abstract void fabLinkClicked();
    public abstract void fabQrcodeClicked();
    public abstract void resultMaterialBarcode(String rawValue);
    public abstract void clickedOnItem(String url);
}
