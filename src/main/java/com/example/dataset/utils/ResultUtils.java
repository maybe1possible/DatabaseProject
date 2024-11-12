package com.example.dataset.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultUtils<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

    public static <T> ResultUtils<T> success() {
        ResultUtils<T> result = new ResultUtils<T>();
        result.code = 1;
        return result;
    }

    public static <T> ResultUtils<T> success(T object) {
        ResultUtils<T> result = new ResultUtils<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> ResultUtils<T> error(String msg) {
        ResultUtils result = new ResultUtils();
        result.msg = msg;
        result.code = 0;
        return result;
    }
}
