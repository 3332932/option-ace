//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cn.web.resolver;

import com.google.common.collect.Lists;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectUtils {
    private static final String SETTER_PREFIX = "set";
    private static final String GETTER_PREFIX = "get";
    private static final String CGLIB_CLASS_SEPARATOR = "$$";
    private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    public ReflectUtils() {
    }

    public static Object invokeGetter(Object obj, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(obj, getterMethodName, new Class[0], new Object[0]);
    }

    public static void invokeSetter(Object obj, String propertyName, Object value) {
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethodByName(obj, setterMethodName, new Object[]{value});
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        return getFieldValue(obj, fieldName, false);
    }

    public static Object getFieldValue(Object obj, String fieldName, boolean ignoreNotFond) {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null) {
            if (ignoreNotFond) {
                return null;
            } else {
                throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
            }
        } else {
            Object result = null;

            try {
                result = field.get(obj);
            } catch (IllegalAccessException var6) {
                logger.error("不可能抛出的异常{}", var6.getMessage());
            }

            return result;
        }
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        setFieldValue(obj, fieldName, value, false);
    }

    public static void setFieldValue(Object obj, String fieldName, Object value, boolean ignoreNotFond) {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null) {
            if (!ignoreNotFond) {
                throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
            }
        } else {
            try {
                field.set(obj, value);
            } catch (IllegalAccessException var6) {
                logger.error("不可能抛出的异常:{}", var6.getMessage());
            }

        }
    }

    public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] args) {
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
        } else {
            try {
                return method.invoke(obj, args);
            } catch (Exception var6) {
                throw convertReflectionExceptionToUnchecked(var6);
            }
        }
    }

    public static Object invokeMethodByName(Object obj, String methodName, Object[] args) {
        Method method = getAccessibleMethodByName(obj, methodName);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
        } else {
            try {
                return method.invoke(obj, args);
            } catch (Exception var5) {
                throw convertReflectionExceptionToUnchecked(var5);
            }
        }
    }

    public static Field getAccessibleField(Object obj, String fieldName) {
        Validate.notNull(obj, "object can't be null", new Object[0]);
        Validate.notBlank(fieldName, "fieldName can't be blank", new Object[0]);
        Class superClass = obj.getClass();

        while(superClass != Object.class) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                makeAccessible(field);
                return field;
            } catch (NoSuchFieldException var4) {
                superClass = superClass.getSuperclass();
            }
        }

        return null;
    }

    public static Method getAccessibleMethod(Object obj, String methodName, Class... parameterTypes) {
        Validate.notNull(obj, "object can't be null", new Object[0]);
        Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);
        Class searchType = obj.getClass();

        while(searchType != Object.class) {
            try {
                Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
                makeAccessible(method);
                return method;
            } catch (NoSuchMethodException var5) {
                searchType = searchType.getSuperclass();
            }
        }

        return null;
    }

    public static Method getAccessibleMethodByName(Object obj, String methodName) {
        Validate.notNull(obj, "object can't be null", new Object[0]);
        Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);

        for(Class searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
            Method[] methods = searchType.getDeclaredMethods();
            Method[] var4 = methods;
            int var5 = methods.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Method method = var4[var6];
                if (method.getName().equals(methodName)) {
                    makeAccessible(method);
                    return method;
                }
            }
        }

        return null;
    }

    public static void makeAccessible(Method method) {
        if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
            method.setAccessible(true);
        }

    }

    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }

    }

    public static <T> Class<T> getClassGenricType(Class clazz) {
        return getClassGenricType(clazz, 0);
    }

    public static Class getClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                if (!(params[index] instanceof Class)) {
                    logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
                    return Object.class;
                } else {
                    return (Class)params[index];
                }
            } else {
                logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
                return Object.class;
            }
        }
    }

    public static Class<?> getProxyBeanOrgClass(Object instance) {
        Validate.notNull(instance, "Instance must not be null", new Object[0]);
        Class clazz = instance.getClass();
        if (clazz != null && clazz.getName().contains("$$")) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !Object.class.equals(superClass)) {
                return superClass;
            }
        }

        return clazz;
    }

    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if (!(e instanceof IllegalAccessException) && !(e instanceof IllegalArgumentException) && !(e instanceof NoSuchMethodException)) {
            if (e instanceof InvocationTargetException) {
                return new RuntimeException(((InvocationTargetException)e).getTargetException());
            } else {
                return e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException("Unexpected Checked Exception.", e);
            }
        } else {
            return new IllegalArgumentException(e);
        }
    }

    public static boolean isProxyBean(Object bean) {
        boolean isProxy = false;
        Class<?> clazz = bean.getClass();
        if (clazz != null && (clazz.getName().contains("$$") || bean instanceof Proxy)) {
            isProxy = true;
        }

        return isProxy;
    }

    public static String[] getFieldNames(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];

        for(int i = 0; i < fields.length; ++i) {
            System.out.println(fields[i].getType());
            fieldNames[i] = fields[i].getName();
        }

        return fieldNames;
    }

    public static Field[] getAllDeclaredFields(Class<?> type) {
        List<Field> fields = Lists.newArrayList();
        Validate.notNull(type, "type can't be null", new Object[0]);

        for(Class superClass = type; superClass != type; superClass = superClass.getSuperclass()) {
            Field[] curFields = superClass.getDeclaredFields();
            Field[] var4 = curFields;
            int var5 = curFields.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field f = var4[var6];
                makeAccessible(f);
                fields.add(f);
            }
        }

        return (Field[])((Field[])fields.toArray());
    }

    public static String[] getStaticFiledNames(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];

        for(int i = 0; i < fields.length; ++i) {
            System.out.println(fields[i].getType());
            fieldNames[i] = fields[i].getName();
        }

        return fieldNames;
    }

    public static Class<?> getMethodReturnTypeByName(Class<?> clazz, String methodName) {
        Validate.notNull(clazz, "calzz can't be null", new Object[0]);
        Validate.notBlank(methodName, "methodName can't be blank", new Object[0]);
        Method[] methods = clazz.getDeclaredMethods();
        Method[] var3 = methods;
        int var4 = methods.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Method method = var3[var5];
            if (method.getName().equals(methodName)) {
                return method.getReturnType();
            }
        }

        return null;
    }

    public static Field getAccessibleFieldByType(Class<?> type, String fieldName) {
        Validate.notNull(type, "type can't be null", new Object[0]);
        Validate.notBlank(fieldName, "fieldName can't be blank", new Object[0]);
        Class superClass = type;

        while(superClass != type) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                makeAccessible(field);
                return field;
            } catch (NoSuchFieldException var4) {
                superClass = superClass.getSuperclass();
            }
        }

        return null;
    }

    public static Object invokeGetterMethod(Object obj, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(obj, getterMethodName, new Class[0], new Object[0]);
    }

    public static void invokeSetterMethod(Object obj, String propertyName, Object value) {
        invokeSetterMethod(obj, propertyName, value, (Class)null);
    }

    public static void invokeSetterMethod(Object obj, String propertyName, Object value, Class<?> propertyType) {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(obj, setterMethodName, new Class[]{type}, new Object[]{value});
    }

    public static <T> Class<T> getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                if (!(params[index] instanceof Class)) {
                    logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
                    return Object.class;
                } else {
                    return (Class)params[index];
                }
            } else {
                logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
                return Object.class;
            }
        }
    }
}
