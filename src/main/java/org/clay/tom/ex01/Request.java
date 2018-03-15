package org.clay.tom.ex01;

import java.io.IOException;
import java.io.InputStream;

public class Request {

    private InputStream input;
    private String uri;

    public Request(InputStream input){
        this.input = input;
    }

    public String getUri(){
        return uri;
    }

    public void parse(){

        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try{
            i = input.read(buffer); //数据读到缓冲区buffer中。i代表读到的位置。
        }catch (IOException e){
            e.printStackTrace();
            i = -1;//捕获异常，说明读到结尾了。所以置为-1
        }
        for (int j = 0; j < i; j++){
            request.append((char)(buffer[j]));//读取到的字节转化为请求。
        }
        System.out.println(request.toString());//打印。
        uri = parseUri(request.toString());
    }

    //解析请求
    //两个 ' ' 空格 之间的是uri
    //例如：GET /index.html HTTP/1.1  我们要获取的就是     /index.html
    public String parseUri(String requestString){

        int index1,index2;
        index1 = requestString.indexOf(' ');
        if(index1 != -1){  //说明存在空串
            index2 = requestString.indexOf(' ',index1 + 1);
            if(index2 > index1){
                return requestString.substring(index1+1,index2);
            }
        }
        return null;
    }
}
