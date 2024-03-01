package online.zust.qcqcqc.utils.tests;

import online.zust.qcqcqc.utils.proxy.ProxyMethodHandler;

import java.lang.reflect.Method;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 22:29
 */
public class TestMethodHandler implements ProxyMethodHandler {
    @Override
    public void beforeInvoke(Method method, Object[] args) {
        System.out.println("Before invoke method2: " + method.getName());
    }

    @Override
    public void afterInvoke(Object result) {
        System.out.println("After invoke method2: " + result);
    }
}
