package lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    public static void start(Class<?> c) {

        Method[] methods = c.getMethods();
        Map<Class<?>, Method> boundaryMethods = getBeforeAndAfter(methods);
        List<PriorityMethod> testMethods = getTestMethods(methods);

        try {
            Object test = c.newInstance();
            Method before = boundaryMethods.get(BeforeSuite.class);
            Method after = boundaryMethods.get(AfterSuite.class);

            if (before != null) before.invoke(test);
            for (PriorityMethod priorityMethod : testMethods) {
                priorityMethod.method.invoke(test);
            }
            if (after != null) after.invoke(test);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void start(String className) {

        try {
            Class<?> c = Class.forName(className);
            start(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Map<Class<?>, Method> getBeforeAndAfter(Method[] methods) {

        int beforeCount = 0;
        int afterCount = 0;
        Map<Class<?>, Method> result = new HashMap<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeCount++;
                result.put(BeforeSuite.class, method);
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                afterCount++;
                result.put(AfterSuite.class, method);
            }
            if (beforeCount > 1) throw new RuntimeException("Method @BeforeSuite must be 0 or 1");
            if (afterCount > 1) throw new RuntimeException("Method @AfterSuite must be 0 or 1");
        }
        return result;
    }

    private static List<PriorityMethod> getTestMethods(Method[] methods) {

        List<PriorityMethod> result = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                result.add(new PriorityMethod(method, method.getAnnotation(Test.class).priority()));
            }
        }
        Collections.sort(result);
        return result;
    }

    private static class PriorityMethod implements Comparable<PriorityMethod> {
        private final Method method;
        private final int priority;

        public PriorityMethod(Method method, int priority) {
            this.method = method;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityMethod m) {
            return this.priority - m.priority;
        }
    }
}
