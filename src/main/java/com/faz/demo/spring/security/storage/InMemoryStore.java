package com.faz.demo.spring.security.storage;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryStore<E extends Storable> implements Store<E> {

    private final Map<Class<E>, Map<String, E>> cache = new HashMap<>();

    @Override
    public void save(E e, Class<E> clazz) {
        Map<String, E> entityStore = getEntityCache(clazz);
        entityStore.put(e.getId(), e);
    }

    @Override
    public E get(String id, Class<E> clazz) {
        Map<String, E> entityStore = getEntityCache(clazz);
        return entityStore.get(id);
    }

    @Override
    public List<E> list(Class<E> clazz) {
        Map<String, E> entityStore = getEntityCache(clazz);
        return new ArrayList<>(entityStore.values());
    }

    private Map<String, E> getEntityCache(Class<E> clazz) {
        if (!cache.containsKey(clazz)) {
            cache.put(clazz, new HashMap<>());
        }
        return cache.get(clazz);
    }
}
