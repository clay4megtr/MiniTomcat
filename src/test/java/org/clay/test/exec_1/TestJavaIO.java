package org.clay.test.exec_1;

import org.junit.Test;

import java.io.*;

public class TestJavaIO {

    //创建文件
    @Test
    public void testNewFile() {

        String fileName = "D:" + File.separator + "hello.txt";
        File f = new File(fileName);
        try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileConstant() {
        System.out.println(File.separator);  //     分隔符，windowns为反斜杠，代码的跨平台性
        System.out.println(File.pathSeparator);//  ;
    }

    @Test
    public void testDropFile() {

        String fileName = "D:" + File.separator + "hello.txt";
        File f = new File(fileName);

        if (f.exists()) {
            f.delete();
        } else {
            System.out.println("文件不存在");
        }
    }

    @Test
    public void testMkDir() {

        String fileName = "D:" + File.separator + "hello";
        File f = new File(fileName);
        f.mkdir();//创建一个目录
    }

    //列出指定目录的全部文件（包括隐藏文件）   只列出文件名称
    @Test
    public void listAlFlile() {

        String fileName = "D:" + File.separator + "Typora";
        File f = new File(fileName);

        String[] str = f.list();
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }

    //列出指定目录的全部文件（包括隐藏文件）  区别是全路径列出
    @Test
    public void listAllFilePath() {
        String fileName = "D:" + File.separator + "Typora";
        File f = new File(fileName);

        File[] list = f.listFiles();
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    @Test
    public void testIsDirectory() {

        String fileName = "D:" + File.separator;
        File f = new File(fileName);
        if (f.isDirectory()) {
            System.out.println("Yes");
        }
    }

    //搜索指定目录的全部内容:  递归
    @Test
    public void recurseListAll() {

        String fileName = "D:" + File.separator;
        File f = new File(fileName);
        print(f);
    }

    public void print(File file) {

        if (file != null) {
            if (file.isDirectory()) {
                File[] list = file.listFiles();
                if (list != null) {
                    for (int i = 0; i < list.length; i++) {
                        print(list[i]);
                    }
                }
            } else {
                System.out.println(file);
            }
        }
    }

    @Test
    public void testRandomAccessFile() throws Exception {

        String fileName = "D:" + File.separator + "hello.txt";
        File f = new File(fileName);

        RandomAccessFile demo = new RandomAccessFile(f, "rw");
        demo.writeBytes("asdasd");
        demo.writeInt(12);
        demo.writeBoolean(true);
        demo.writeChar('A');
        demo.writeFloat(1.21f);
        demo.writeDouble(12.123);
        demo.close();
    }

    //============================================字节流=====================================================

    @Test
    public void testFileOutputStream() throws Exception {
        String fileName = "D:" + File.separator + "hello.txt";
        File file = new File(fileName);

        OutputStream out = new FileOutputStream(file);
        String str = "你好";
        byte[] b = str.getBytes();
        out.write(b);
        out.close();
    }

    /**
     * 字节流
     * 向文件中一个字节一个字节的写入字符串
     */
    @Test
    public void testFileOutputStreamOneByte() throws Exception {

        String fileName = "D:" + File.separator + "hello.txt";
        File f = new File(fileName);
        OutputStream out = new FileOutputStream(f);
        String str = "你好";
        byte[] b = str.getBytes();
        for (int i = 0; i < b.length; i++) {
            out.write(b[i]);
        }
        out.close();
    }

    @Test
    public void testFileOutputStreamAppend() throws Exception {
        String fileName = "D:" + File.separator + "hello.txt";
        File f = new File(fileName);

        OutputStream out = new FileOutputStream(f, true);
        String str = "你好";
        byte[] b = str.getBytes();
        for (int i = 0; i < b.length; i++) {
            out.write(b[i]);
        }
        out.close();
    }


    /**
     * 读取文件内容
     */
    @Test
    public void testFileInputStream() throws IOException {
        String fileName = "D:" + File.separator + "hello.txt";
        File file = new File(fileName);
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[(int) file.length()];
        int len = in.read(b);
        System.out.println("读入长度：" + len);
        in.close();
        System.out.println(new String(b, 0, len));
    }

    /**
     * 读取文件内容
     * 一个一个读
     */
    @Test
    public void testOneFileInputStream() throws IOException {
        String fileName = "D:" + File.separator + "hello.txt";
        File file = new File(fileName);
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[(int) file.length()];

        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) in.read();
        }
        in.close();
        System.out.println(new String(b));
    }


    /**
     * 读取文件内容
     * 不知道文件有多大
     */
    @Test
    public void testNoFileInputStream() throws IOException {
        String fileName = "D:" + File.separator + "hello.txt";
        File file = new File(fileName);
        InputStream in = new FileInputStream(file);
        byte[] b = new byte[1024];

        int count = 0;
        int temp = 0;
        while((temp = in.read()) != -1){
            b[count++] = (byte) temp;
        }

        in.close();
        System.out.println(new String(b));
    }


    //============================================字符流=====================================================

    /**
     * 字符流
     * 写入数据
     * 其实这个例子上之前的例子没什么区别，只是你可以直接输入字符串，而不需要你将字符串转化为字节数组。
     * 当你如果想问文件中追加内容的时候，可以使用将上面的声明out的哪一行换为：
     * Writer out =new FileWriter(f,true);
     *  这样，当你运行程序的时候，会发现文件内容变为：
     *  hellohello如果想在文件中换行的话，需要使用“\r\n”
     *  比如将str变为String str="\r\nhello";
     *  这样文件追加的str的内容就会换行了。
     */
    @Test
    public void testWriter() throws IOException {

        String fileName="D:"+File.separator+"hello.txt";
        File f=new File(fileName);
        Writer out = new FileWriter(f);
        String str = "你好";
        out.write(str);
        out.flush();//只有刷新才有数据
        out.close();
    }

    /**
     * 不知道文件到底有多大
     * 循环读取
     */
    @Test
    public void testReader() throws IOException {
        String fileName="D:"+File.separator+"hello.txt";
        File f=new File(fileName);
        char[] ch = new char[100];
        Reader in = new FileReader(f);
        int temp = 0;
        int count = 0;
        while((temp = in.read()) != -1){
            ch[count++] = (char)temp;
        }

        in.close();
        System.out.println("内容为 " + new String(ch,0,count));
    }


    @Test
    public void testCopy() throws IOException {

        File file1=new File("");
        File file2=new File("");

        InputStream input = new FileInputStream(file1);
        OutputStream outPut = new FileOutputStream(file2);

        if((input != null) && (outPut != null)){
            int temp = 0;
            while((temp = input.read()) != -1){
                outPut.write(temp);
            }
        }

        input.close();
        outPut.close();
    }
}
