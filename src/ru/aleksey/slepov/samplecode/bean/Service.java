package ru.aleksey.slepov.samplecode.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Executes tasks
 * Annotated class must have
 * {@code public void run() method}
 *
 * @author Aleksey Selpov
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
