package online.zust.qcqcqc.utils.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TransactionInterceptor<T> implements MethodInterceptor {
    T target;

    public TransactionInterceptor(T target) {
        this.target = target;
    }

    /**
     * proxy：代理对象，CGLib动态生成的代理类实例
     * method：目标对象的方法，上文中实体类所调用的被代理的方法引用
     * args：目标对象方法的参数列表，参数值列表
     * methodProxy：代理对象的方法，生成的代理类对方法的代理引用
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启事务..." + proxy.getClass().getSimpleName());
        Object objValue = null;
        try {
            // 反射调用目标类方法
            objValue = method.invoke(target, args);
            System.out.println("返回值为：" + objValue);
        } catch (Exception e) {
            System.out.println("调用异常!" + e.getMessage());
        } finally {
            System.out.println("调用结束，关闭事务...");
        }
        return objValue;
    }

    /**
     * 获取代理实例
     */
    public T getTargetProxy() {
        // Enhancer类是cglib中的一个字节码增强器，它可以方便的为你所要处理的类进行扩展
        Enhancer eh = new Enhancer();
        // 1.将目标对象所在的类作为Enhancer类的父类
        eh.setSuperclass(target.getClass());
        // 2.通过实现MethodInterceptor实现方法回调
        eh.setCallback(this);
        // 3. 创建代理实例
        return (T) eh.create();
    }
}
