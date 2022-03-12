package com.changgou.file;

import com.changgou.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    @Value("${pic.url}")
    public String url;

    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file") MultipartFile file){
        if(!file.isEmpty()){

            try {
                // 获取字节数组
                byte[] bytes = file.getBytes();
                //获取文件原始名字
                String originalFilename = file.getOriginalFilename();
                //获取扩展名
                String filenameExtension = StringUtils.getFilenameExtension(originalFilename);
                String[] strings = FastDFSClient.upload(new FastDFSFile(originalFilename,bytes,filenameExtension));
                return url + strings[0] + "/" + strings[1];
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
