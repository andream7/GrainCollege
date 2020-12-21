package com.atguigu.demo.edu.controller;


import com.atguigu.commonutils.Res;
import com.atguigu.demo.edu.entity.Teacher;
import com.atguigu.demo.edu.entity.vo.TeacherQuery;
import com.atguigu.demo.edu.service.TeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-20
 */

@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    // 注入service
    @Autowired
    private TeacherService teacherService;
    //1 查询讲师表所有数据

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Res findAllTeacher() {
        List<Teacher> list = teacherService.list(null);
        return Res.ok().data("items", list);
    }

    //2 逻辑删除
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Res removeById(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag)  return Res.ok();
        else return Res.error();
    }

    // 分页查询
    @GetMapping("pageTeacher/{current}/{limit}")
    public Res pageListTeacher(@PathVariable long current, @PathVariable long limit) {

        Page<Teacher> pageTeacher = new Page<>(current,limit);

//        try {
//            int i = 0;
//            int m = 10 / i;
//        } catch (Exception e) {
//            throw new GuliException(20001, "GuiliException");
//        }


        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return Res.ok().data(map);
    }

    // 4 条件查询+分页
    @GetMapping("pageTeacherCondition/{current}/{limit}")
    public Res pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
    TeacherQuery teacherQuery) {

        Page<Teacher> pageTeacher = new Page<>(current,limit);

//        构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        // 多条件组合 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            wrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        teacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return Res.ok().data(map);
    }

    // 添加讲师接口的方法
    @PostMapping("addTeacher")
    public Res addTeacher(@RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) return Res.ok();
        else return Res.error();
    }

    //根据id查询讲师
    @GetMapping("getTeacher/{id}")
    public Res getById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return Res.ok().data("item",teacher);

    }

    // 讲师修改
    @PostMapping("updateTeacher")
    public Res updateTeacher(@RequestBody Teacher teacher) {
        boolean update = teacherService.updateById(teacher);
        if (update) return Res.ok();
        else return Res.error();
    }


}

