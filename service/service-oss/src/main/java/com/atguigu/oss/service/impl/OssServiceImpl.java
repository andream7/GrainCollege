package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        try {
            // 创建OSS实例
            OSS ossClient= new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename = uuid + filename;
            
            // 文件按照时间日期进行分类
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath + "/" + filename;
            // 调用方法实现上传
            ossClient.putObject(bucketName, filename, inputStream);

            ossClient.shutdown();
            // 把上传文件后的路径返回
            return "https://"+bucketName+"."+endpoint+"/"+filename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
