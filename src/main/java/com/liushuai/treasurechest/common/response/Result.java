package com.liushuai.treasurechest.common.response;

import com.liushuai.treasurechest.exception.AssertUtils;
import com.liushuai.treasurechest.exception.DefaultException;
import lombok.Data;

/**
 * @Description 前端统一返回结构
 * @Author ls
 * @Date 2019/7/13 10:20
 */
@Data
public class Result<T> {
    private String code;
    private String msg;
    private String err;
    private T data;

    /**
     * 默认返回的模型
     *
     * @return
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.DEFAULT_SUCCESS);
        result.setMsg("操作成功");
        return result;
    }

    /**
     * 返回带内容的模型
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.DEFAULT_SUCCESS);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 返回默认失败的模型
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        return Result.error(ResultCode.DEFAULT_FAIL, msg);
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 根据异常信息返回默认的失败模型
     *
     * @param defaultException
     * @return
     */
    public static Result error(DefaultException defaultException) {
        Result<Object> result = new Result<>();
        result.setCode(defaultException.getCode());
        result.setMsg(defaultException.getMessage());
        result.setErr(defaultException.getErr());
        result.setData(defaultException.getData());
        return result;
    }

    /**
     * 验证code并返回data
     *
     * @return
     */
    public T detection() {
        AssertUtils.isTrue(this.getCode().equals(ResultCode.DEFAULT_SUCCESS), this.msg);
        return this.data;
    }

    /**
     * 验证code 如果code != 1
     * 返回result自判断处理进行总结出来
     *
     * @param resultFallback
     * @return
     */
    public T detection(ResultFallback<T> resultFallback) {
        return resultFallback.callback(this);
    }

}
