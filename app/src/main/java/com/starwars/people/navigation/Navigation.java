package com.starwars.people.navigation;

import android.content.Context;
import android.content.Intent;

import com.starwars.people.details.presentation.view.PersonDetailsActivity;

import javax.inject.Singleton;

@Singleton
public class Navigation {

    public void goToDetails(Context context, String url){
        Intent intent = new Intent(context, PersonDetailsActivity.class);
        intent.putExtra(PersonDetailsActivity.EXTRA_URL, url);
        context.startActivity(intent);
    }

}
