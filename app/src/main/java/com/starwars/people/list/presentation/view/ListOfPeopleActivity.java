package com.starwars.people.list.presentation.view;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.starwars.R;
import com.starwars.core.fab.OptionsFabLayout;
import com.starwars.core.listener.OnItemClick;
import com.starwars.core.presentation.BaseActivity;
import com.starwars.films.di.FilmsModule;
import com.starwars.people.di.DaggerPeopleComponent;
import com.starwars.people.di.PeopleComponent;
import com.starwars.people.di.PeopleModule;
import com.starwars.people.list.presentation.adapter.ListOfPeopleAdapter;
import com.starwars.people.list.presentation.model.PresentationPerson;
import com.starwars.people.list.presentation.presenter.ListOfPeoplePresenter;
import com.starwars.people.navigation.Navigation;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOfPeopleActivity extends BaseActivity implements ListOfPeopleView, View.OnClickListener, OptionsFabLayout.OnMiniFabSelectedListener, OnItemClick<String> {

    @Inject
    ListOfPeoplePresenter listOfPeoplePresenter;

    @Inject
    ListOfPeopleAdapter listOfPeopleAdapter;

    @Inject
    Navigation navigation;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.optionsFabLayout)
    OptionsFabLayout optionsFabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_people);
        injectDependencies();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listOfPeopleAdapter);
        listOfPeopleAdapter.setOnItemClick(this);

        listOfPeoplePresenter.attachTo(this);

        optionsFabLayout.setMiniFabsColors(
                R.color.colorPrimary,
                R.color.colorPrimary);
        optionsFabLayout.setMainFabOnClickListener(this);
        optionsFabLayout.setMiniFabSelectedListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listOfPeoplePresenter.destroy();
    }

    private void injectDependencies() {
        ButterKnife.bind(this);
        PeopleComponent peopleComponent = DaggerPeopleComponent.builder()
                    .appComponent(getAppComponent())
                    .peopleModule(new PeopleModule())
                    .filmsModule(new FilmsModule(getSupportFragmentManager()))
                    .build();
        peopleComponent.inject(this);
    }

    @Override
    public void showLoading() {
        //TODO
    }

    @Override
    public void hideLoading() {
        //TODO
    }

    @Override
    public void addPresentationPerson(PresentationPerson presentationPeople) {
        listOfPeopleAdapter.addPresentationPerson(presentationPeople);
    }

    @Override
    public void openCustomDialogFabLink() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflated = getLayoutInflater().inflate(R.layout.input_link, null);
        final EditText editText = (EditText) viewInflated.findViewById(R.id.editText);
        builder.setView(viewInflated);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            listOfPeoplePresenter.searchByLink(editText.getText().toString());
            dialog.dismiss();
        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void openQrcodeView() {
        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(this)
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withBackfacingCamera()
                .withText(getString(R.string.scanning))
                .withResultListener(barcode -> listOfPeoplePresenter.resultMaterialBarcode(barcode.rawValue))
                .build();
        materialBarcodeScanner.startScan();
    }

    //TODO WORKAROUND TO START LIB AFTER PERMISSION.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode ==2 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
           openQrcodeView();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showFailureAddPerson() {
        Toast.makeText(this, "ocorreu algo de errado", Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToDetails(String url) {
        navigation.goToDetails(this, url);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mega_fab && optionsFabLayout.isOptionsMenuOpened()){
            optionsFabLayout.closeOptionsMenu();
        }
    }

    @Override
    public void onMiniFabSelected(MenuItem fabItem) {
        switch (fabItem.getItemId()){
            case R.id.fab_link:
                listOfPeoplePresenter.fabLinkClicked();
                break;
            case R.id.fab_qrcode:
                listOfPeoplePresenter.fabQrcodeClicked();
                break;
        }
    }

    @Override
    public void onClick(String url) {
        listOfPeoplePresenter.clickedOnItem(url);
    }

}
