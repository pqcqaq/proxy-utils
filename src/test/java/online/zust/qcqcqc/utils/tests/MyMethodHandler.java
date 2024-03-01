package online.zust.qcqcqc.utils.tests;

import online.zust.qcqcqc.utils.proxy.ProxyMethodHandler;

import java.lang.reflect.Method;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 21:52
 */
public class MyMethodHandler implements ProxyMethodHandler {

    @Override
    public void beforeInvoke(Method method, Object[] args) {
        System.out.println("Before invoke method: " + method.getName());
    }

    @Override
    public void afterInvoke(Object result) {
        result = "Hello, world! (handled)";
        System.out.println("After invoke method: " + result);
    }
}
