import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author CooliHe
 * For：QQ实时聊天程序 服务端
 * Time：7.23
 * 服务端，多线程监听客户端请求
 * Blog:coolive.top
 */
public class serverSocket extends Thread{

    public void run(){
        try{
            ServerSocket serverSocket = new ServerSocket(8088);
            while (true){
                Socket socket = serverSocket.accept();
                chatSocket socket1 = new chatSocket(socket);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String name = in.readUTF();
                socket1.start();
                chatManager.getCm().add(name,socket1);
                chatManager.getCm().onLine(name);
                System.out.println(name);
                System.out.println("连接成功！");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
