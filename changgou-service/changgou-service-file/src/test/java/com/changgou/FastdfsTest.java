package com.changgou;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class FastdfsTest {

    //上传图片
    @Test
    public void upload() throws MyException, IOException {
        //1、创建一个配置文件 用于配置tracker_sever的ip和端口
        //2、加载配置文件
        ClientGlobal.init("/Users/p/JavaProject/changgou/changgou-service/changgou-service-file/src/main/resources/fdfs_client.conf");
        //3、创建trackerclient对象
        TrackerClient trackerClient = new TrackerClient();
        //4、创建trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5、创建storageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //6、执行一个上传文件
        String[] pngs = storageClient.upload_file("/Users/p/Pictures/image.png", "png", null);
        for (String png : pngs) {
            System.out.println(png);
        }
    }
    //下载图片
    @Test
    public void download() throws MyException, IOException {
        //1、创建一个配置文件 用于配置tracker_sever的ip和端口
        //2、加载配置文件
        ClientGlobal.init("/Users/p/JavaProject/changgou/changgou-service/changgou-service-file/src/main/resources/fdfs_client.conf");
        //3、创建trackerclient对象
        TrackerClient trackerClient = new TrackerClient();
        //4、创建trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5、创建storageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //6、执行一个上传文件
        byte[] group1s = storageClient.download_file("group1", "M00/00/00/wKgDZmIeJKWATKP2AAMql4X92Ws766.png");
        //写入文件
    }
    //删除图片
    @Test
    public void delete() throws MyException, IOException {
        //1、创建一个配置文件 用于配置tracker_sever的ip和端口
        //2、加载配置文件
        ClientGlobal.init("/Users/p/JavaProject/changgou/changgou-service/changgou-service-file/src/main/resources/fdfs_client.conf");
        //3、创建trackerclient对象
        TrackerClient trackerClient = new TrackerClient();
        //4、创建trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5、创建storageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //6、执行一个上传文件
        int group1 = storageClient.delete_file("group1", "M00/00/00/wKgDZmIeJKWATKP2AAMql4X92Ws766.png");
        System.out.println(group1);
        //写入文件
    }
    //获取图片元信息
    @Test
    public void getFileInfo() throws MyException, IOException {
        //1、创建一个配置文件 用于配置tracker_sever的ip和端口
        //2、加载配置文件
        ClientGlobal.init("/Users/p/JavaProject/changgou/changgou-service/changgou-service-file/src/main/resources/fdfs_client.conf");
        //3、创建trackerclient对象
        TrackerClient trackerClient = new TrackerClient();
        //4、创建trackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //5、创建storageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //6、执行一个上传文件
        FileInfo group1 = storageClient.get_file_info("group1", "M00/00/00/wKgDZmIeJzeADBZhAAMql4X92Ws354.png");
        System.out.println(group1);
        //写入文件
    }
}
