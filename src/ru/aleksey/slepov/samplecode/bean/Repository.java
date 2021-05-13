package ru.aleksey.slepov.samplecode.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Gives access to the data in storage(s)
 *
 * @author Aleksey Selpov
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
}
