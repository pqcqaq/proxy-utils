package online.zust.qcqcqc.utils;

import online.zust.qcqcqc.utils.proxy.interceptor.ProxyMethodInterceptor;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 11:16
 */
public class ProxyUtils {

    /**
     * Create a proxy object
     *
     * @param clazz the class of the proxy object
     * @param <T>   the type of the proxy object
     * @return the proxy object
     */
    public static <T> T createProxy(Class<T> clazz) {
        return ProxyMethodInterceptor.newInstance(clazz);
    }

}
