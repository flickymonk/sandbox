package com.alevel.textboard.model;

import java.util.Objects;

public final class Persisted<ID, T> {

    private final ID id;

    private final T value;

    public Persisted(ID id, T value) {
        this.id = id;
        this.value = value;
    }

    public ID getId() {
        return id;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persisted)) return false;
        Persisted<?, ?> persisted = (Persisted<?, ?>) o;
        return Objects.equals(persisted.id, id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
