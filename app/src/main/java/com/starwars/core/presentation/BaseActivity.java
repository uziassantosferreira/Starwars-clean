package com.starwars.core.presentation;

import android.support.v7.app.AppCompatActivity;

import com.starwars.core.application.StarWarsApplication;
import com.starwars.core.di.AppComponent;

public class BaseActivity extends AppCompatActivity {
    public AppComponent getAppComponent() {
        return  ((StarWarsApplication) getApplication()).getAppComponent();
    }
}
