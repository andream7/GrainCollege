package com.atguigu.demo.edu.controller;

import com.atguigu.commonutils.Res;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin // 解决跨域问题
public class EduLoginController {
    // login
    @PostMapping("login")
    public Res login(){
        return Res.ok().data("token","admin");
    }

    // info
    @GetMapping("info")
    public Res info() {
        return Res.ok().data("roles","[admin]")
                       .data("name","admin")
                       .data("avatar","");
    }
}
