package com.atguigu.demo.edu.service;

import com.atguigu.demo.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-05
 */
public interface SubjectService extends IService<Subject> {
    // 添加课程分类
    void saveSubject(MultipartFile file,SubjectService subjectService);
}
