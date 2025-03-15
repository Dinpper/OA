package com.example.labSystem.common;


import java.util.HashMap;
import java.util.Map;

/**
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException {



    private Integer code;
    private static Map<Integer, String> codeMap = new HashMap<Integer, String>();
    static {
        codeMap.put(500, "服务器繁忙，请稍后重试。");
        codeMap.put(502, "参数封装有误");
        codeMap.put(399, "查询不到信息");
    }
    public BusinessException() {
        super();
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.setCode(code);
    }

    public BusinessException(int code) {
        super(codeMap.get(code));
        this.setCode(code);
    }

    public BusinessException(Exception exception) {
        super(exception);
    }

    public BusinessException(String msg, Exception e) {
        super(msg, e);
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
