package ru.aleksey.slepov.samplecode.util;

import ru.aleksey.slepov.samplecode.bean.GenericBean;
import ru.aleksey.slepov.samplecode.outsource.BeanLoaderHelper;

import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * Simple implementation of BeanLoader interface
 *
 * @author Aleksey Selpov
 */
public class SimpleBeanLoader implements BeanLoader {
    private final BeanStorage storage;

    public SimpleBeanLoader(BeanStorage storage) {
        this.storage = storage;
    }

    /**
     * Loads beans into the storage by scanning packages
     * @param source list of packages divided with ',' to look into for beans
     */
    @Override
    public void load(String source) {
        if (source == null) {
            throw new IllegalArgumentException("Source must be present");
        }
        String[] packages = source.split(",");

        for(String aPackage: packages){
            try {
                Class[] classes = BeanLoaderHelper.getClasses(aPackage);
                for (Class clazz: classes) {
                    Object base = resolveUnitConstruction(clazz);
                    GenericBean bean = new GenericBean();
                    bean.put(base);
                    storage.put(bean);
                }
            } catch (ClassNotFoundException | IOException e) {
                throw new IllegalStateException("Failed to load classes of a package: " + aPackage);
            }
        }
    }

    private Object resolveUnitConstruction(Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        //TODO make code to create units with constructors
        throw new UnsupportedOperationException("No implementation yet");
    }
}
