package com.sismics.sapparot.collection;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jtremeaux
 */
public class Differ<E> {
    private List<E> oldCollection;
    private List<E> newCollection;
    private Function<E, String> toId;
    private Set<String> idsToCreate;
    private Set<String> idsToUpdate;
    private Set<String> idsToDelete;

    public Differ(List<E> oldCollection, List<E> newCollection, Function<E, String> toId) {
        this.oldCollection = oldCollection;
        this.newCollection = newCollection;
        this.toId = toId;
        diff();
    }

    private Differ<E> diff() {
        Set<String> oldAppIds = oldCollection.stream().map(toId).collect(Collectors.toSet());
        Set<String> newAppIds = newCollection.stream().map(toId).collect(Collectors.toSet());

        idsToCreate = Sets.difference(newAppIds, oldAppIds);
        idsToUpdate = Sets.intersection(newAppIds, oldAppIds);
        idsToDelete = Sets.difference(oldAppIds, newAppIds);

        return this;
    }

    private List<E> getElementsToCreate() {
        return newCollection.stream()
                .filter(e -> idsToCreate.contains(toId.apply(e)))
                .collect(Collectors.toList());
    }

    private List<E> getElementsToUpdate() {
        return newCollection.stream()
                .filter(e -> idsToUpdate.contains(toId.apply(e)))
                .collect(Collectors.toList());
    }

    private List<E> getElementsToDelete() {
        return oldCollection.stream()
                .filter(e -> idsToDelete.contains(toId.apply(e)))
                .collect(Collectors.toList());
    }

    public Differ<E> createEach(Consumer<E> consumer) {
        getElementsToCreate().forEach(consumer);
        return this;
    }

    public Differ<E> updateEach(Consumer<E> consumer) {
        getElementsToUpdate().forEach(consumer);
        return this;
    }

    public Differ<E> deleteEach(Consumer<E> consumer) {
        getElementsToDelete().forEach(consumer);
        return this;
    }
}