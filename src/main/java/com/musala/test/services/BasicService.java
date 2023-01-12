package com.musala.test.services;

import java.util.List;
import java.util.Optional;

public interface BasicService<T> {
    public T save(T obj);

    public Optional<T> fetch(long objId);

    public List<T> fetchAll();

    public void delete(T obj);

    public T getReferenceById(long objId);
}
