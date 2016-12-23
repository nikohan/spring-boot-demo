package com.test.demo.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @see <a>http://www.cnblogs.com/LBSer/p/4853234.html</a>
 * Created by nikohan on 2016/12/6.
 */
public class RpcProxyClient implements InvocationHandler{

    private Object target;

    public RpcProxyClient(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //通信的相关的逻辑写在这里...
        System.out.println("connecting remote method...");
        Object result = method.invoke(target, args);
        return result;
    }

    //获取被代理的对象
    public static Object getProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new RpcProxyClient(obj));
    }

    public static void main(String[] args) {
        RemoteService service = (RemoteService) getProxy(new RemoteServiceImpl());
        service.printHello("nikohan");
    }
}
