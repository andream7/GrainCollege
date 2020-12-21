package com.atguigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Res {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    private Res(){}

    public static Res ok() {
        Res r = new Res();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;

    }

    public static Res error() {
        Res r = new Res();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public Res success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Res code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Res message(String message) {
        this.setMessage(message);
        return this;
    }
    public Res data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Res data(Map<String, Object> map) {
        for (String key : map.keySet()) {
            this.data.put(key, map.get(key));
        }
        return this;
    }
}
