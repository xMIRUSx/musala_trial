package com.musala.test.services;

import java.util.Optional;

public interface BasicService<T> {
    public T save(T obj);

    public Optional<T> fetch(long objId);

    public void delete(T obj);
}
