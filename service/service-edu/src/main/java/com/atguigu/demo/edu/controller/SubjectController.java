package com.atguigu.demo.edu.controller;


import com.atguigu.commonutils.Res;
import com.atguigu.demo.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-05
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // 添加课程分类
    @PostMapping("addSubject")
    public Res addSubject(MultipartFile file) {
        subjectService.saveSubject(file,subjectService);
        return Res.ok();
    }
}

