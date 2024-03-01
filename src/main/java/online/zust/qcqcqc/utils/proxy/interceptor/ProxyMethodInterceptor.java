package online.zust.qcqcqc.utils.proxy.interceptor;

import online.zust.qcqcqc.utils.annotation.MethodHandler;
import online.zust.qcqcqc.utils.proxy.ProxyMethodHandler;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 21:53
 */
public class ProxyMethodInterceptor<T> implements MethodInterceptor {
    private final T targetInstance;
    private final T targetProxy;
    private final HashMap<Method, ProxyMethodHandler[]> handler;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (handler.containsKey(method)) {
            ProxyMethodHandler[] proxyMethodHandlers = handler.get(method);
            for (ProxyMethodHandler proxyMethodHandler : proxyMethodHandlers) {
                proxyMethodHandler.beforeInvoke(method, objects);
            }
            Object invoke;
            invoke = method.invoke(targetInstance, objects);
            for (int i = proxyMethodHandlers.length - 1; i >= 0; i--) {
                ProxyMethodHandler proxyMethodHandler = proxyMethodHandlers[i];
                proxyMethodHandler.afterInvoke(invoke);
            }
            return invoke;
        }
        return method.invoke(targetInstance, objects);
    }

    public ProxyMethodInterceptor(Class<T> targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(this);
        try {
            targetInstance = targetClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Object o = enhancer.create();
        if (targetClass.isInstance(o)) {
            targetProxy = targetClass.cast(o);
        } else {
            throw new RuntimeException("Failed to create proxy instance");
        }
        handler = new HashMap<>();
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            MethodHandler annotation = declaredMethod.getAnnotation(MethodHandler.class);
            if (annotation != null) {
                handleAnnotation(declaredMethod, annotation);
            }
        }
    }

    private void handleAnnotation(Method declaredMethod, MethodHandler annotation) {
        try {
            Class<? extends ProxyMethodHandler>[] value = annotation.value();
            ProxyMethodHandler[] proxyMethodHandlers = new ProxyMethodHandler[value.length];
            for (int i = 0; i < value.length; i++) {
                proxyMethodHandlers[i] = value[i].getConstructor().newInstance();
            }
            handler.put(declaredMethod, proxyMethodHandlers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get the target proxy
     * @return the target proxy
     */
    public T getTargetProxy() {
        return targetProxy;
    }

    /**
     * create a new instance of the class
     * @param clazz the class of the instance
     * @return the instance
     * @param <T> the type of the instance
     */
    public static <T> T newInstance(Class<T> clazz) {
        return new ProxyMethodInterceptor<>(clazz).getTargetProxy();
    }
}
