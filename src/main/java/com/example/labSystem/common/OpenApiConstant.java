package com.example.labSystem.common;

/**
 * 业务常量类
 */
public class OpenApiConstant {
    public static final Integer REDIS_DATAV_TIMEOUT= 21600000;
    public static final Integer REDIS_TIMEOUT = 7200;

    public static final String SIMPLEFORMAT24 = "yyyy-MM-dd HH:mm:ss";
    public static final String SIMPLEFORMAT_second = "HH:mm:ss";
    public static final String SIMPLEFORMAT_HOUR = "HH:mm";
    public static final String SIMPLEFORMAT24MIN = "yyyy-MM-dd HH:mm";
    public static final String SIMPLEFORMAT24_ = "yyyyMMddHHmmss";
    public static final String SIMPLEFORMAT24YEAR = "yyyy-MM-dd";
    public static final String SIMPLEFORMAT24YEAR_ = "yyyyMMdd";
    //参数
    public static final String FIELD = "field";
    public static final String SYMBOL_COLON = ":";
    public static final String SYMBOL_COMMA= ",";
    public static final String VALUE = "value";
    public static final String SYMBOL_SEMICOLON = ";";
    public static final String SYMBOL_ACROSS = "-";
    public static final String SYMBOL_LINE = "_";
    public static final String SYMBOL_PERCENT="%";//百分号
    public static final String SYMBOL_VERTICAL="|";//竖

    //msg
    public static final String MESSAGE_SUCCESS = "成功";
    public static final String MESSAGE_ERROR = "失败";

    //厂家名称-易姆讯
    public static final String MANUFACTURER_NAME_INMOTION = "in";
    public static final String MANUFACTURER_NAME_MWPARK = "mw";
    //厂家名称-卡爱是
    public static final String MANUFACTURER_NAME_CAREYES = "CarEyes";

    public static final String PAGE_SIZE_10 = "10";
    public static final String PAGE_SIZE_100 = "100";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String ZERO= "0";
    public static final String APP_PREFIX= "appId_";
    public static final String PARKING_PREFIX= "l_";
    public static final String USER_PREFIX= "u_";
    public static final String NOPLATE= "无牌车";
    public static final String THIRDPARTYCOUPON_TITLE= "third_";


    public static final String GROUPBYMONTH= "MONTH(reportDate),parkingLotCode";

    public static final String HTTP= "http";
    public static final String HTTPS= "https";

    public static final String OSS_Effect_Allow = "\"Allow\"";
    public static final String OSS_forwardSlash = "\"";// 拼接用

    public static final String MQ_HT_ENTER_PRODUCTOR = "enterProductor";
    public static final String MQ_HT_EXIT_PRODUCTOR = "exitProductor";

    public static final String HT_THIRD_PAY = "三方厂商支付";
    /**
     * 工联CRM请求参数拼装-randomseed
     */
    public static final String GLCRM_PARAM_RANDOMSEED = "&randomseed=";
    /**
     * 工联CRM请求参数拼装-token
     */
    public static final String GLCRM_PARAM_TOKEN = "&token=";

}
