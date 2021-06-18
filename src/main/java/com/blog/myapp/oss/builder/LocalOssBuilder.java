package com.blog.myapp.oss.builder;

import com.blog.myapp.config.ApplicationProperties;
import com.blog.myapp.domain.OssConfig;
import com.blog.myapp.oss.OssRule;
import com.blog.myapp.oss.OssTemplate;
import com.blog.myapp.oss.local.LocalOssTemplate;

/**
 * 本地存储构建类
 *
 */
public class LocalOssBuilder {

    public static OssTemplate template(OssConfig ossConfig, OssRule ossRule) {
        // 创建配置类
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.getOss().setEndpoint(ossConfig.getEndpoint());
        applicationProperties.getOss().setAccessKey(ossConfig.getAccessKey());
        applicationProperties.getOss().setSecretKey(ossConfig.getSecretKey());
        applicationProperties.getOss().setBucketName(ossConfig.getBucketName());
        return new LocalOssTemplate(applicationProperties, ossRule);
    }
}
