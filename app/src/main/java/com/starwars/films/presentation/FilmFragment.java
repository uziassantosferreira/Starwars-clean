package com.starwars.films.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.starwars.R;
import com.starwars.films.presentation.model.PresentationFilm;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmFragment extends Fragment {

    private static final String EXTRA_FILM_TITLE = "title";
    private static final String EXTRA_FILM_OPENING_CRAWL = "opening_crawl";

    @BindView(R.id.imageview)
    ImageView imageView;

    @BindView(R.id.textview_title)
    TextView textViewTitle;

    @BindView(R.id.textview_openingcrawl)
    TextView textViewOpeningCrawl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        ButterKnife.bind(this, view);

        textViewTitle.setText(getArguments().getString(EXTRA_FILM_TITLE));
        textViewOpeningCrawl.setText(getArguments().getString(EXTRA_FILM_OPENING_CRAWL));
        return view;
    }

    public static Fragment newInstance(PresentationFilm presentationFilm) {
        FilmFragment filmFragment = new FilmFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_FILM_TITLE, presentationFilm.title());
        bundle.putString(EXTRA_FILM_OPENING_CRAWL, presentationFilm.openingCrawl());
        filmFragment.setArguments(bundle);
        return filmFragment;
    }

}
