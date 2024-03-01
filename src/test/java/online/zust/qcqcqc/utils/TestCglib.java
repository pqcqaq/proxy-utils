package online.zust.qcqcqc.utils;

import online.zust.qcqcqc.utils.cglib.TransactionInterceptor;
import online.zust.qcqcqc.utils.cglib.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @author qcqcqc
 * Date: 2024/3/1
 * Time: 11:57
 */
public class TestCglib {

    @Test
    public void test() {
        // 将cglib生成的代理类字节码输出到指定位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./cglib");
        // 1. 创建目标实例
        UserService userService = new UserService();
        // 2. 创建事务拦截器
        TransactionInterceptor<UserService> transactionInterceptor = new TransactionInterceptor<>(userService);
        // 3. 创建代理实例
        UserService userServiceProxy = transactionInterceptor.getTargetProxy();
        // 4. 使用代理实例调用目标方法
        userServiceProxy.getUserName(6L);
        userServiceProxy.test();
    }

}
