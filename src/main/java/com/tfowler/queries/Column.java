package com.tfowler.queries;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that the annotated field represents column in a database.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
}
