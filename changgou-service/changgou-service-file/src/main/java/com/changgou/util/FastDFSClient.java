package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

//用于CRUD图片
@Component
public class FastDFSClient {
    static {
        ClassPathResource classPathResource = new ClassPathResource("fdfs_client.conf");
        try {
            //1、创建一个配置文件 用于配置tracker_sever的ip和端口
            //2、加载配置文件
            ClientGlobal.init(classPathResource.getPath());
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    public static StorageClient storageClient(){
        try {
            //3、创建tracker client对象
            TrackerClient trackerClient = new TrackerClient();
            //4、创建trackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //5、创建storageClient对象
            return new StorageClient(trackerServer, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    };

    //上传图片
    public static String[] upload(FastDFSFile file){
        try {
            StorageClient storageClient = storageClient();
            NameValuePair[] meta_list = new NameValuePair[]{
                    new NameValuePair(file.getName())
            };
            //6、执行一个上传文件
            if (storageClient != null){
                return storageClient.upload_file(file.getContent(),file.getExt(), meta_list);
            }
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return null;
    };

    //下载图片
    public static byte[] downFile(String groupName, String remoteFileName){
        StorageClient storageClient = storageClient();
        try {
            if (storageClient != null){
                return storageClient.download_file(groupName, remoteFileName);
            }
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return null;
    }
    //删除图片
    public static boolean deleteFile(String groupName, String remoteFileName){
        StorageClient storageClient = storageClient();
        try {
            if (storageClient != null){
                int i = storageClient.delete_file(groupName, remoteFileName);
                return i ==0;
            }
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return false;
    }
}
