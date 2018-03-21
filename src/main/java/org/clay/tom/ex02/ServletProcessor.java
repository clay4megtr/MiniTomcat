package org.clay.tom.ex02;

import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.io.File;

public class ServletProcessor {

    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File calssPath = new File(Constants.WEB_ROOT);

            //在一个servlet容器里边，一个类加载器可以找到servlet的地方被称为资源库（respository）
            String repository = (new URL("file", null, calssPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Servlet servlet = null;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);

        try{
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) requestFacade, (ServletResponse) responseFacade);

            // servlet.service((ServletRequest) request, (ServletResponse) response);
            //安全问题：知道这个servlet容器的内部运作的Servlet程序员可以分别把ServletRequest和ServletResponse实例向下转换为
            // ex02.pyrmont.Request和ex02.pyrmont.Response，并调用他们的公共方法。
            // 拥有一个Request实例，它们就可以调用parse方法。拥有一个Response实例，就可以调用sendStaticResource方法。
        }catch (Exception e){
            e.printStackTrace();
        }catch (Throwable e){
            e.printStackTrace();
        }

    }
}
