package com.pahanedu.persistence.resource.factory;

// Generic interface for creating products
public interface ProductFactory<T> {
    T create(Object... params);
}
