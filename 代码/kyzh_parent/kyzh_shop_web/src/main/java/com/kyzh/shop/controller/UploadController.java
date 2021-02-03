package com.kyzh.shop.controller;

import entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.FastDFSClient;

@RestController
public class UploadController  {

    private String server_url = "http://192.168.25.133/";

    @RequestMapping("/upload")
    public Result upload(MultipartFile uploadFile){
        //file上传到fastdfs
        try {
            String filename = uploadFile.getOriginalFilename(); //文件名
            String extName = filename.substring(filename.lastIndexOf(".")+1);//扩展名

            FastDFSClient client = new FastDFSClient("classpath:config/fdfs_client.conf");
            //参数1：文件字节码 参数2：文件扩展名
            String fileId = client.uploadFile(uploadFile.getBytes(), extName);

            String filePath = server_url + fileId;

            return new Result(true, filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"上传失败");
        }
    }
}
