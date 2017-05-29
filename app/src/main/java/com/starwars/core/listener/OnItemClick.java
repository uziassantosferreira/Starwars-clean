package com.starwars.core.listener;

public interface OnItemClick<T> {
    void onClick(T object);
}