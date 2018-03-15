package org.clay.tom.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output){
        this.output = output;
    }

    //设置请求。
    public void setRequest(Request request){
        this.request = request;
    }

    //发送静态资源
    public void sendStaticResource(){
        byte[] bufffer = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        try{
            File file = new File(HttpServer.WEB_ROOT,request.getUri());  //根目录 + uri 对应的文件。
            if(file.exists()){
                fis = new FileInputStream(file);
                int length = fis.read(bufffer,0,BUFFER_SIZE);  //把对应的静态文件内容读取到bytes数组中,先读这么多
                while(length != -1){
                    output.write(bufffer,0,length);
                    length = fis.read(bufffer,0,BUFFER_SIZE);  //如果没有读完，继续读
                }
            }else {
                // 找不到用户请求的文件
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
