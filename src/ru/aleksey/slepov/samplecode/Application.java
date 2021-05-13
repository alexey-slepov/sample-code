package ru.aleksey.slepov.samplecode;

import ru.aleksey.slepov.samplecode.bean.Bean;
import ru.aleksey.slepov.samplecode.bean.Repository;
import ru.aleksey.slepov.samplecode.bean.Service;
import ru.aleksey.slepov.samplecode.util.BeanLoader;
import ru.aleksey.slepov.samplecode.util.BeanStorage;
import ru.aleksey.slepov.samplecode.util.InMemoryBeanStorage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Runs sample code
 *
 * @author A
 */
public class Application {
    BeanStorage storage;
    private final Set<Bean> services = new HashSet<>();
    private final Set<Bean> repositories = new HashSet<>();

    /**
     * Runs the application
     * @param args input arguments
     */
    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("BeanLoader instance name must be as the first argument " +
                    " and source path as the second argument" );
        }
        Application app = new Application();
        app.init(args[0], args[1]);
    }

    private void init(String beanLoaderName, String sources) {
        BeanStorage storage = new InMemoryBeanStorage();

        BeanLoader loader;
        try {
            loader = lookForLoader(beanLoaderName, storage);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Failed to find class with name " + beanLoaderName, e);
        }
        loader.load(sources);
        try {
            run();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new IllegalStateException("Failed to load beans");
        }
    }

    private BeanLoader lookForLoader(String name, BeanStorage storage) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(name);
        Class<?>[] interfaces = clazz.getInterfaces();
        boolean notABean = true;
        for(Class iface: interfaces) {
            if(iface.getName().equals("ru.aleksey.slepov.samplecode.util.BeanLoader")) {
                notABean = false;
                break;
            }
        }
        if(notABean) {
            throw new IllegalStateException("Class candidate is of the wrong type");
        }
        try {
            Constructor<?> constructor = clazz.getConstructor(BeanStorage.class);
            return (BeanLoader) constructor.newInstance(storage);
        } catch (InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to create a bean", e);
        }
    }

    private void run() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Bean bean: storage.getAll()) {
            if (bean.get().getClass().isAnnotationPresent(Service.class)) {
                services.add(bean);
            } else if (bean.get().getClass().isAnnotationPresent(Repository.class)) {
                repositories.add(bean);
            }
        }
        for (Bean service : services) {
            Method runMethod = null;
            try {
                runMethod = service.get().getClass().getMethod("run");
                runMethod.setAccessible(true);
                runMethod.invoke(service);
            } finally {
                if (runMethod != null) {
                    runMethod.setAccessible(false);
                }
            }
        }
    }
}
