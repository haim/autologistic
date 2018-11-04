package com.estafeta.test.ui.preconditions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dnikiforov
 * @project estafeta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginRequired {
  String login() default "estafeta";

  String password() default "1";

  String company() default "Communication Company Estafeta";
}
