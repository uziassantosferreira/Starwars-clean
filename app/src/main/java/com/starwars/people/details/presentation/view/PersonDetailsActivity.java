package com.starwars.people.details.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.starwars.R;
import com.starwars.core.presentation.BaseActivity;
import com.starwars.people.details.presentation.model.PresentationPerson;
import com.starwars.people.details.presentation.presenter.PersonDetailsPresenter;
import com.starwars.people.di.DaggerPeopleComponent;
import com.starwars.people.di.PeopleComponent;
import com.starwars.people.di.PeopleModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonDetailsActivity extends BaseActivity implements PersonDetailsView {

    public static final String EXTRA_URL = "URL";

    @BindView(R.id.textview_name)
    TextView textViewName;

    @BindView(R.id.textview_url)
    TextView textViewUrl;

    @BindView(R.id.textview_height)
    TextView textViewHeight;

    @BindView(R.id.textview_mass)
    TextView textViewMass;

    @BindView(R.id.textview_hair_color)
    TextView textViewHairColor;

    @BindView(R.id.textview_skin_color)
    TextView textViewSkinColor;

    @BindView(R.id.textview_eye_color)
    TextView textViewEyeColor;

    @BindView(R.id.textview_birth_year)
    TextView textViewBirthYear;

    @BindView(R.id.textview_gender)
    TextView textViewGender;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Inject
    PersonDetailsPresenter personDetailsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        injectDependencies();

        personDetailsPresenter.attachTo(this);
        personDetailsPresenter.setUrl(getIntent().getStringExtra(EXTRA_URL));

    }

    private void injectDependencies() {
        ButterKnife.bind(this);
        PeopleComponent personDetailsComponent = DaggerPeopleComponent.builder()
                .appComponent(getAppComponent())
                .peopleModule(new PeopleModule())
                .build();
        personDetailsComponent.inject(this);

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
    public void showDetails(PresentationPerson person) {
        textViewName.setText(person.name());
        textViewUrl.setText(person.url());
        textViewHeight.setText(person.height());
        textViewMass.setText(person.mass());
        textViewHairColor.setText(person.hairColor());
        textViewSkinColor.setText(person.skinColor());
        textViewEyeColor.setText(person.eyeColor());
        textViewBirthYear.setText(person.birthYear());
        textViewGender.setText(person.gender());
    }

}
