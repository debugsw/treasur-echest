package com.liushuai.treasurechest.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.liushuai.treasurechest.common.response.Result;
import com.liushuai.treasurechest.config.OSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description OSS操作
 * @Author ls
 * @Date 2019/7/13 9:59
 */
@RestController
@RequestMapping("/oss")
public class OSSController {

    @Autowired
    private OSSProperties ossProperties;

    @GetMapping("/getOSSUplOad")
    public Result<Map> getOSSUplOad(){

        OSS ossclient = new OSSClientBuilder().build(ossProperties.getEndpoint(),ossProperties.getAccessId(),ossProperties.getAccessKeySecret());

        //上传字符串
        String str ="hello oss";
        PutObjectResult hello = ossclient.putObject(ossProperties.getBucketName(), "object", new ByteArrayInputStream(str.getBytes()));
        String eTag = hello.getETag();

        //关闭oss
        ossclient.shutdown();

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("result","ok");
        respMap.put("msg",eTag);

        return Result.ok(respMap);
    }
}
