package com.estafeta.test.ui.listeners;

import com.estafeta.test.ui.preconditions.PrepareTest;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.ConstructorOrMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class AmtListener extends TestListenerAdapter {

  private static void invokeAnnotatedMethods(
      Object testCase, ConstructorOrMethod testMethod, Class<? extends Annotation> annotation)
      throws InvocationTargetException, IllegalAccessException {
    Method[] methods = testCase.getClass().getMethods();
    for (Method method : methods) {
      if (method.isAnnotationPresent(annotation)) {
        method.setAccessible(true);
        method.invoke(testCase, testMethod.getMethod());
      }
    }
  }

  @Override
  public void onTestStart(ITestResult result) {
    try {
      Object testCase = result.getInstance();
      ConstructorOrMethod testMethod = result.getMethod().getConstructorOrMethod();
      invokeAnnotatedMethods(testCase, testMethod, PrepareTest.class);
    } catch (Throwable e) {
      System.out.println("on test start catch: " + e);
    }
  }
}
