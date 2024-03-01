package online.zust.qcqcqc.utils.annotation;

import online.zust.qcqcqc.utils.proxy.ProxyMethodHandler;

import java.lang.annotation.*;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 21:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MethodHandler {
    Class<? extends ProxyMethodHandler>[] value();
}
