package com.starwars.people.list.presentation.view;

import com.starwars.core.presentation.LoadingView;
import com.starwars.people.list.presentation.model.PresentationPerson;

public interface ListOfPeopleView extends LoadingView {
    void addPresentationPerson(PresentationPerson presentationPeople);
    void openCustomDialogFabLink();
    void openQrcodeView();
    void showFailureAddPerson();
    void goToDetails(String url);
}
