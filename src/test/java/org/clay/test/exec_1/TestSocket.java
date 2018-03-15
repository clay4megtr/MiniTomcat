package org.clay.test.exec_1;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

public class TestSocket {

    @Test
    public void test1() throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1",8080);
        OutputStream os = socket.getOutputStream();
        boolean autoFlush = true;
        PrintWriter out = new PrintWriter(socket.getOutputStream(),autoFlush);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //send an HTTP request to the web server
        out.print("GET /index.jsp HTTP/1.1");
        out.print("Host: localhost:8080");
        out.print("Connection: Close");
        out.println();

        //read the response
        boolean loop = true;
        StringBuffer sb = new StringBuffer();
        while(loop){
            if(in.ready()){
                int i = 0;
                while(i != -1){
                    i = in.read();
                    sb.append((char)i);
                }
            loop = false;
            }
            Thread.currentThread().sleep(50);
        }

        //dsiplay the response to the out console
        System.out.println(sb.toString());
        socket.close();
    }
}
