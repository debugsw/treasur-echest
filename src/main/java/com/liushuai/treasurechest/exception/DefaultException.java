package com.liushuai.treasurechest.exception;

import com.liushuai.treasurechest.common.response.ResultCode;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @Description 统一的业务异常封装
 * @Author ls
 * @Date 2019/7/13 10:28
 */
@Data
public class DefaultException extends RuntimeException {
    private String code;
    private Object data;

    public DefaultException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * 自定义错误
     *
     * @param code 错误编码
     * @param msg  提示语
     * @return
     */
    public static DefaultException customException(String code, String msg) {
        return new DefaultException(code, msg);
    }

    /**
     * 业务默认错误
     *
     * @param msg 提示语
     * @return
     */
    public static DefaultException defaultException(String msg) {
        return new DefaultException(ResultCode.DEFAULT_FAIL, msg);
    }

    /**
     * 默认未知错误
     *
     * @return
     */
    public static DefaultException unknownException() {
        return new DefaultException(ResultCode.UNKNOWN_FAIL, "系统未知错误");
    }

    /**
     * 默认未知错误
     *
     * @return
     */
    public static DefaultException unknownException(String msg) {
        return new DefaultException(ResultCode.UNKNOWN_FAIL, msg);
    }


    /**
     * 如果 flag 不成立 则抛出异常
     * 用法参照Spring Boot 断言 Assert.class
     *
     * @param flag
     * @param message
     */
    public static void isTure(boolean flag, String message) {
        if (!flag) {
            throw DefaultException.defaultException(message);
        }
    }

    /**
     * 如果 str 为空 或者 str 长度为0 则抛出异常
     * 用法参照Spring Boot 断言 Assert.class
     *
     * @param str
     * @param message
     */
    public static void hasText(String str, String message) {
        if (!StringUtils.hasText(str)) {
            throw DefaultException.defaultException(message);
        }
    }

    /**
     * 如果对象为空则抛出异常
     * 用法参照Spring Boot 断言 Assert.class
     *
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw DefaultException.defaultException(message);
        }
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErr() {
        return "暂未实现";
    }

}
