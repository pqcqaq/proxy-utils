package online.zust.qcqcqc.utils.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 21:51
 */
public interface ProxyMethodHandler {

    /**
     * get the class of the method handler
     *
     * @return the class of the method handler
     */
    default Class<? extends ProxyMethodHandler> getHandlerClass() {
        return this.getClass();
    }

    /**
     * before invoke method
     * @param method the method to be invoked
     * @param args the parameters of the method
     */
    void beforeInvoke(Method method, Object[] args);

    /**
     * after invoke method
     * @param result the result of the method
     */
    void afterInvoke(Object result);


}
