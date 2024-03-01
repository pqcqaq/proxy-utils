package online.zust.qcqcqc.utils;

import online.zust.qcqcqc.utils.tests.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 11:16
 */
public class TestProxyUtils {
    @Test
    public void createProxy() {
        // 将cglib生成的代理类字节码输出到指定位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./cglib");
        MyService proxy = ProxyUtils.createProxy(MyService.class);
        String hello = proxy.hello();
        System.out.println(hello);
    }
}
