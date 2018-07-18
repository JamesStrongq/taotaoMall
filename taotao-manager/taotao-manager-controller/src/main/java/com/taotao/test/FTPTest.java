package com.taotao.test;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;

public class FTPTest {

    public void testFTP() throws Exception{
        //创建一个ftpclient对象
        FTPClient ftpClient = new FTPClient();
        //创建ftp连接
        ftpClient.connect("192.168.0.106",21);
        //登录ftp服务器，使用用户名和密码
        ftpClient.login("ftpuser","huang0408");
        //上传文件
        //读取本地文件
        FileInputStream fis = new FileInputStream(new File("D:\\图片\\1491104430861.jpg"));
        //设置上传的路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        //修改上传文件的格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //第一个参数:服务器端的文件名
        //第二个参数:上传文件的inputStream
        ftpClient.storeFile("hello11.jpg",fis);
        //关闭连接
        ftpClient.logout();

    }

    public static void main(String[] args) throws Exception{
        FTPTest f = new FTPTest();
        f.testFTP();
    }

}
