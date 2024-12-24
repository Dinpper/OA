package com.example.labSystem.bean.pagination;

import com.example.labSystem.common.IResultCode;
import com.example.labSystem.common.SupResultCode;
import org.springframework.http.HttpStatus;


public class Res<T> {

    private String code; // 返回码
    private String msg; // 返回信息
    private int httpStatus; // HTTP请求状态码
    private T data; // 数据结构

    // 默认构造函数
    public Res() {
    }

    public Res(T data) {
        this.code = SupResultCode.RESP_000000.getCode();
        this.msg = SupResultCode.RESP_000000.getMsg();
        this.httpStatus = HttpStatus.OK.value();
        this.data = data;
    }

    public Res(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = HttpStatus.OK.value();
        this.data = data;
    }

    public Res(String code, String msg, int httpStatus, T data) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public Res(IResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.httpStatus = HttpStatus.OK.value();
        this.data = data;
    }

    public Res(IResultCode resultCode, int httpStatus, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Res{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", httpStatus=" + httpStatus +
                ", data=" + data +
                '}';
    }
}
