package com.starwars.people.list.presentation.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class PresentationPerson {

    public static PresentationPerson create(String name, String url) {
        return new AutoValue_PresentationPerson(name, url);
    }

    public abstract String name();
    public abstract String url();

}