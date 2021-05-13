package ru.aleksey.slepov.samplecode.util;

import ru.aleksey.slepov.samplecode.bean.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Stores beans in memory
 *
 * @author Aleksey Selpov
 */
public class InMemoryBeanStorage implements BeanStorage {
    private final Map<String, Bean> beans = new ConcurrentHashMap<>();

    /**
     * Put a bean into the storage. Bean is specified by its name class
     * @param bean is a bean to put
     */
    @Override
    public void put(Bean bean) { ;
        beans.put(bean.getClass().getName(), bean);
    }

    /**
     * Returns a bean by its name
     * @param name name of a bean to return
     * @return bean
     */
    @Override
    public Bean get(String name) {
        return beans.get(name);
    }

    /**
     * Return all beans
     * @return beans
     */
    @Override
    public Bean[] getAll() {
        return beans.values().toArray(new Bean[0]);
    }
}
