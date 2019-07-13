package com.liushuai.treasurechest.exception;

import com.liushuai.treasurechest.common.response.ResultCode;

/**
 * @Description 自定义异常抛出工具
 * @Author ls
 * @Date 2019/7/13 10:33
 */
public class AssertUtils {

    /**
     * 断定字符串不为空
     *
     * @param s
     * @param message
     */
    public static void hasText(String s, String message) {
        AssertUtils.hasText(s, ResultCode.DEFAULT_FAIL, message);
    }

    /**
     * 断定字符串不为空
     *
     * @param s
     * @param code
     * @param message
     */
    public static void hasText(String s, String code, String message) {
        if (null == s || "".equals(s)) {
            throw DefaultException.customException(code, message);
        }
    }

    /**
     * 断定字符串不为空
     *
     * @param s
     * @param message
     */
    public static void isBlank(String s, String message) {
        AssertUtils.isBlank(s, ResultCode.DEFAULT_FAIL, message);
    }

    /**
     * 断定字符串不为空
     *
     * @param s
     * @param code
     * @param message
     */
    public static void isBlank(String s, String code, String message) {
        if (null != s && !"".equals(s)) {
            throw DefaultException.customException(code, message);
        }
    }

    /**
     * 断定不为空
     *
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        AssertUtils.notNull(object, ResultCode.DEFAULT_FAIL, message);
    }

    /**
     * 断定不为空
     *
     * @param object
     * @param code
     * @param message
     */
    public static void notNull(Object object, String code, String message) {
        if (null == object) {
            throw DefaultException.customException(code, message);
        }
    }

    /**
     * 断定为空
     *
     * @param object
     * @param message
     */
    public static void isEmpty(Object object, String message) {
        AssertUtils.isEmpty(object, ResultCode.DEFAULT_FAIL, message);
    }

    /**
     * 断定为空
     *
     * @param object
     * @param code
     * @param message
     */
    public static void isEmpty(Object object, String code, String message) {
        if (null != object) {
            throw DefaultException.customException(code, message);
        }
    }

    /**
     * 断定不为空
     *
     * @param object
     * @param message
     */
    public static void notEmpty(Object object, String message) {
        AssertUtils.notEmpty(object, ResultCode.DEFAULT_FAIL, message);
    }

    /**
     * 断定不为空
     *
     * @param object
     * @param code
     * @param message
     */
    public static void notEmpty(Object object, String code, String message) {
        if (null == object) {
            throw DefaultException.customException(code, message);
        }
    }

    /**
     * 断定为真
     *
     * @param b
     * @param message
     */
    public static void isTrue(Boolean b, String message) {
        AssertUtils.isTrue(b, ResultCode.DEFAULT_FAIL, message);
    }

    /**
     * 断定为真
     *
     * @param b
     * @param code
     * @param message
     */
    public static void isTrue(Boolean b, String code, String message) {
        if (!b) {
            throw DefaultException.customException(code, message);
        }
    }

}
