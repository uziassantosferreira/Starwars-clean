package com.starwars.core.database.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import io.requery.Converter;

public class StringListConverter implements Converter<List<String>, String> {

    @Override
    public Class<List<String>> getMappedType() {
        return (Class) List.class;
    }

    @Override
    public Class<String> getPersistedType() {
        return String.class;
    }

    @Nullable
    @Override
    public Integer getPersistedSize() {
        return null;
    }

    @Override
    public String convertToPersisted(List<String> strings) {
        if (strings == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String value : strings) {
            if (index > 0) {
                sb.append(",");
            }
            sb.append(value);
            index++;
        }
        return sb.toString();
    }

    @Override
    public List<String> convertToMapped(Class<? extends List<String>> aClass, @Nullable String value) {
        List<String> list = new ArrayList<>();
        if (value != null) {
            String[] parts = value.split(",");
            Collections.addAll(list, parts);
        }
        return list;
    }
}
