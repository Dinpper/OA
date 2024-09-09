package com.example.labsystem.common;

public enum SupResultCode implements IResultCode {

    RESP_000000("000000", "请求服务正常返回"),
    /**
     * 数据库事务预处理操作
     **/
    RESP_900101("900101", "新增事务预处理失败"),
    RESP_900102("900102", "更新事务预处理失败"),
    RESP_900103("900103", "删除事务预处理失败"),
    /**
     * 数据库事务处理操作
     **/
    RESP_900201("900201", "新增事务处理失败"),
    RESP_900202("900202", "更新事务处理失败"),
    RESP_900203("900203", "删除事务处理失败"),
    RESP_900204("900204", "查询结果集为空"),
    /**
     * 系统级错误
     **/
    RESP_900000("900000", "事务处理请求异常"),
    RESP_900001("900001", "未登录"),
    RESP_900002("900002", "登录超时"),
    RESP_900003("900003", "无访问权限"),
    RESP_900400("900400", "请求参数不正确"),
    RESP_900401("900401", "未授权请求"),
    RESP_900404("900404", "请求路径不存在"),
    RESP_900405("900405", "请求方法不正确"),
    RESP_900415("900415", "不支持的媒体类型"),
    RESP_900500("900500", "处理请求异常"),
    RESP_999998("999998", "数据服务请求异常"),
    RESP_999999("999999", "恶意攻击"),;

    private String code;
    private String msg;

    SupResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
