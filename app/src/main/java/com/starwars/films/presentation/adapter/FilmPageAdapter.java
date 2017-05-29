package com.starwars.films.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.starwars.films.presentation.FilmFragment;
import com.starwars.films.presentation.model.PresentationFilm;

import java.util.ArrayList;
import java.util.List;

public class FilmPageAdapter extends FragmentStatePagerAdapter {

    private List<PresentationFilm> presentationFilms;

    public FilmPageAdapter(FragmentManager fm) {
        super(fm);
        presentationFilms = new ArrayList<>();
    }

    public void addItem(PresentationFilm presentationFilm){
        presentationFilms.add(presentationFilm);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return FilmFragment.newInstance(presentationFilms.get(position));
    }

    @Override
    public int getCount() {
        return presentationFilms.size();
    }
}
