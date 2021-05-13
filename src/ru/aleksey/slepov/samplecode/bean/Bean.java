package ru.aleksey.slepov.samplecode.bean;

/**
 * Bean wraps base object
 * Act as a unit of work for loaders, storages
 *
 * @author Aleksey Selpov
 */
public interface Bean {
    Object get();
    void put(Object base);
}
