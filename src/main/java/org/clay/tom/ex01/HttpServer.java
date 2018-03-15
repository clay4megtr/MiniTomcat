package org.clay.tom.ex01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "HowTomcatWorks" + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {

        HttpServer server = new HttpServer();
        server.await();
    }

    public void await(){

        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        while(!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream outPut = null;

            try{
                socket = serverSocket.accept();
                input = socket.getInputStream();
                outPut = socket.getOutputStream();

                //创建一个request对象并解析
                Request request = new Request(input);
                request.parse();

                //创建一个response对象
                Response response = new Response(outPut);
                response.setRequest(request);
                response.sendStaticResource();

                //关闭连接
                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }catch (Exception e){
                e.getLocalizedMessage();
                continue;
            }
        }
    }
}
