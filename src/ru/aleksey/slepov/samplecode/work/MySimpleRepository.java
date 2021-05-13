package ru.aleksey.slepov.samplecode.work;

import ru.aleksey.slepov.samplecode.bean.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores data in memory
 *
 * @author Aleksey Selpov
 */
@Repository
public class MySimpleRepository {
    private final Map<Integer, String> storage = new HashMap<>();

    public void put(Integer textNumber, String text) {
        storage.put(textNumber, text);
    }

    public String get(Integer textNumber) {
        return storage.get(textNumber);
    }
}
