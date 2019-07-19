package com.liushuai.treasurechest.http;

import org.hibernate.validator.internal.util.StringHelper;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * @Description 封装常见的HTTP方法
 * @Author ls
 * @Date 2019/7/19 23:04
 */
public class HttpHelper {
    /**
     * 将所有的请求参数转换字符串
     * 例如:
     * uri?param1=111&param2=111,222
     *
     * @param request
     * @return
     */
    public static String requestParamsToString(ServletRequest request) {
        StringBuffer sbuf = new StringBuffer();
        Map<String, String[]> map = request.getParameterMap();
        if (map == null || map.size() == 0) {
            return "";
        }
        int i = 0;

        String arrayJoiner = ",";
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String joiner = "&";
            if (i == 0) {
                joiner = "?";
            }
            String key = entry.getKey();
            String[] value = entry.getValue();
            if (value != null && value.length == 1) {
                sbuf.append(joiner + key + "=" + value[0]);
            } else {
                sbuf.append(joiner + key + "=" + StringHelper.join(value, arrayJoiner));
            }
        }
        return sbuf.toString();
    }
}
