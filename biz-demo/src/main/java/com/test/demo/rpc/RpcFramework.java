package com.test.demo.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * rpc框架的简单实现
 * https://my.oschina.net/solidwang/blog/796940
 * Created by nikohan on 2016/12/22.
 */
public class RpcFramework {

    private final static Executor executor = Executors.newSingleThreadExecutor();

    /**
     * 发布服务
     * @param impl 需要发布的服务
     * @param port 服务发布端口
     * @param <T>
     */
    public static <T> void export(final T impl, Integer port) throws IOException {
        System.out.println("export service " + impl.getClass().getName() + "on port " + port);
        final ServerSocket server = new ServerSocket(port);
        executor.execute(() -> {
            while(true) {
                ObjectInputStream input = null;
                ObjectOutputStream output = null;
                Socket reqSocket  = null;
                try {
                    reqSocket = server.accept();
                    input = new ObjectInputStream(reqSocket.getInputStream());
                    String methodName = input.readUTF();
                    Class<?>[] paramTypes = (Class<?>[]) input.readObject();
                    Object[] paramValues = (Object[]) input.readObject();
                    Method method = impl.getClass().getMethod(methodName, paramTypes);
                    Object result = method.invoke(impl, paramValues);
                    output = new ObjectOutputStream(reqSocket.getOutputStream());
                    output.writeObject(result);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(null != input) {
                            input.close();
                        }
                        if(null != output) {
                            output.close();
                        }
                        if(null != reqSocket) {
                            reqSocket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 引用服务
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final Integer port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket(host, port);
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(args);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        return input.readObject();
                    }
                });
    }
}

class Server {
    public static void main(String[] args) throws IOException {
        RemoteService service = new RemoteServiceImpl();
        RpcFramework.export(service, 9999);
    }
}

class Client {
    public static void main(String[] args) {
        RemoteService service = RpcFramework.refer(RemoteService.class, "localhost", 9999);
        for (int i = 0; i < 1000; i++) {
            System.out.println(service.printHello("nikohan" + i));
        }
    }
}
