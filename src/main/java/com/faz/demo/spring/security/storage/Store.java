package com.faz.demo.spring.security.storage;

import java.util.List;

public interface Store<E extends Storable> {

    void save(E e, Class<E> clazz);

    E get(String id, Class<E> clazz);

    List<E> list(Class<E> clazz);
}
