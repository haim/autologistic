package com.estafeta.test.ui.preconditions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author dnikiforov
 * @project estafeta
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.METHOD)
public @interface PrepareTest {}
