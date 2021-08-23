package homework7.mytesterlib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Tester {
    private Method beforeSuiteMethod = null;
    private Method afterSuiteMethod = null;
    //private final Map<Method, Integer> testMethodsWithPriority = new HashMap<>();
    private final List<Method> testMethods = new ArrayList<>();

    public static void start(Class testClass) {
        Tester tester = new Tester(); //чтоб при каждом новом вызове этого метода инициализировались пустые свойства
        tester.fillMethodsVars(testClass);
        tester.invokeMethods(testClass);
    }


    private void fillMethodsVars(Class testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        //Map<Method, Integer> testMethodsWithPriority = new HashMap<>();
        for (Method method : methods) {
            //Сделал, что BeforeSuite и AfterSuite могут быть не более, чем в одном экземпляре (т.е. их либо может не быть, либо в одном экземпляре, иначе RuntimeException)
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeSuiteMethod == null) {
                    beforeSuiteMethod = method;
                } else {
                    throw new RuntimeException("Метод с аннотацией BeforeSuite может быть только в одном экземпляре!");
                }
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterSuiteMethod == null) {
                    afterSuiteMethod = method;
                } else {
                    throw new RuntimeException("Метод с аннотацией AfterSuite может быть только в одном экземпляре!");
                }
            }
            if (method.getAnnotation(Test.class) != null) {
                //testMethodsWithPriority.put(method, method.getAnnotation(Test.class).value());
                testMethods.add(method);
            }
        }
    }

    private void invokeMethods(Class testClass) {
        try {
            Object testObject = testClass.newInstance();
            if (beforeSuiteMethod != null) {
                beforeSuiteMethod.setAccessible(true);
                beforeSuiteMethod.invoke(testObject);
            }

            //запускаем в порядке приоритетов от 1 до 10 (что что за этими пределами - запущено не будет):
            for (int i = 1; i <= 10; i++) {
                for (Method testMethod : testMethods) {
                    if (testMethod.getAnnotation(Test.class).value() == i) {
                        testMethod.setAccessible(true);
                        testMethod.invoke(testObject);
                    }
                }
            }

            if (afterSuiteMethod != null) {
                afterSuiteMethod.setAccessible(true);
                afterSuiteMethod.invoke(testObject);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
