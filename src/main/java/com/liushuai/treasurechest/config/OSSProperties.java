package com.liushuai.treasurechest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description oss属性
 * @Author ls
 * @Date 2019/7/13 21:00
 */
@Data
@Configuration
@ConfigurationProperties("aliyun.oss")
public class OSSProperties {

    private String accessId;
    private String accessKeySecret;
    private String bucketName;
    private String endpoint;

}
