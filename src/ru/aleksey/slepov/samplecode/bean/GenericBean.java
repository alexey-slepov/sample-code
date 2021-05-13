package ru.aleksey.slepov.samplecode.bean;

/**
 * Generic implementation of the {@link Bean} interface
 *
 * @author Aleksey Selpov
 */
public class GenericBean implements Bean {
    private Object object;

    @Override
    public Object get() {
        return object;
    }

    @Override
    public void put(Object base) {
        this.object = base;
    }
}
