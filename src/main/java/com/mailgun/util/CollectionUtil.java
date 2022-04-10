package com.mailgun.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@UtilityClass
public class CollectionUtil {

    public <T> Set<T> addToSet(Set<T> internalSet, T provideValue) {
        if (Objects.isNull(internalSet)) {
            internalSet = new LinkedHashSet<>();
        }
        internalSet.add(provideValue);
        return internalSet;
    }

    public <T> Set<T> addToSet(Set<T> internalSet, Collection<T> provideValues) {
        if (Objects.isNull(internalSet)) {
            internalSet = new LinkedHashSet<>();
        }
        internalSet.addAll(provideValues);
        return internalSet;
    }

}
