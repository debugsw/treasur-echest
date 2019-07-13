package com.liushuai.treasurechest.common.response;

/**
 * @Description 获取data失败之后的回调
 * @Author 获取data失败之后的回调
 * @Date 2019/7/13 10:30
 */
public interface ResultFallback<T> {
    T callback(Result<T> result);
}
