package ru.aleksey.slepov.samplecode.util;

import ru.aleksey.slepov.samplecode.bean.Bean;

/**
 * Stores beans
 *
 * @author Aleksey Selpov
 */
public interface BeanStorage {
    void put(Bean bean);

    Bean get(String name);

    Bean[] getAll();
}
