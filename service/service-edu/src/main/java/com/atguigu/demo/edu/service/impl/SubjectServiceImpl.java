package com.atguigu.demo.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.demo.edu.entity.Subject;
import com.atguigu.demo.edu.entity.excel.SubjectData;
import com.atguigu.demo.edu.listener.SubjectExcelListener;
import com.atguigu.demo.edu.mapper.SubjectMapper;
import com.atguigu.demo.edu.service.SubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            // 获取文件输入流
            InputStream input = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(input, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new GuliException(20002,"添加课程分类失败");
        }
    }
}
