package com.liushuai.treasurechest.common.response;

/**
 * @Description 前端异常编码
 * @Author ls
 * @Date 2019/7/13 10:23
 */
public class ResultCode {
    /**
     * 默认失败
     */
    public static final String DEFAULT_FAIL = "0";

    /**
     * 默认成功
     */
    public static final String DEFAULT_SUCCESS = "1";

    /**
     * 异常失败
     */
    public static final String UNKNOWN_FAIL = "9999";


    /**
     * 服务未发现
     */
    public static final String EXCEPTION_SERVICE_NOT_FOUND = "300";

    /**
     * 服务调用超时
     */
    public static final String EXCEPTION_SERVICE_TIME_OUT = "301";
}
