package ru.aleksey.slepov.samplecode.work;

import ru.aleksey.slepov.samplecode.bean.Service;

/**
 * Simple service that does nothing
 *
 * @author Aleksey Selpov
 */
@Service
public class MySimpleService {
    private final MySimpleRepository repository;

    public MySimpleService(MySimpleRepository repository) {
        this.repository = repository;
    }

    public void run() {

    }
}
