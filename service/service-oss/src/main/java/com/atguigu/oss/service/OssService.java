package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    // 上传头像至oss
    String uploadFileAvatar(MultipartFile file);
}
