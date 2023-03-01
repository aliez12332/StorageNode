package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void startServer(int port){
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("服务端启动"+port);

            while(true){
                Socket socket = server.accept();
                System.out.println("客户端"+socket.getPort()+"连接到服务器");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());

                Object obj = ois.readObject();
                System.out.println("收到客户端的请求对象！");

                if(obj instanceof RpcRequest){
                    System.out.println("已收到RpcRequest对象！");
                    RpcRequest paras = (RpcRequest) obj ;
                    if(paras.className.equals("Server.IDao")){
                        Class clazz = Class.forName("Server.IDaoImpl");
                        Object impObj = clazz.newInstance();
                        Method method = clazz.getMethod(paras.methodName,paras.parameterTypes);
                        Object result = method.invoke(impObj,paras.parameters);
                        ous.writeObject(result);
                        System.out.println("已给客户端返回结果："+ result.toString());
                        ous.flush();
                        socket.close();
                    }else{
                        System.out.println("unKnow interName:"+paras.className);
                    }
                }else{
                    System.out.println("unKonw obj:"+obj);
                }

            }



        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        for (int i = 20000; i < 20010; i++) {
            Thread thread = new Thread(new StartServerThread(i));
            thread.setName(Integer.toString(i));
            thread.start();
        }
    }
}
