package ru.aleksey.slepov.samplecode.util;

import ru.aleksey.slepov.samplecode.bean.Repository;
import ru.aleksey.slepov.samplecode.bean.Service;

/**
 * Loads beans by text description of bean packages
 * Recognizes bean annotated with {@link Service} and {@link Repository} annotations
 *
 * @author Aleksey Selpov
 */
public interface BeanLoader {
    void load(String source);
}
