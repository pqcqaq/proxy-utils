package online.zust.qcqcqc.utils.tests;

import online.zust.qcqcqc.utils.annotation.MethodHandler;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 21:52
 */
public class MyService {
    @MethodHandler({MyMethodHandler.class, TestMethodHandler.class})
    public String hello() {
        System.out.println("Hello, world!");
        return "Hello, world!";
    }
}
