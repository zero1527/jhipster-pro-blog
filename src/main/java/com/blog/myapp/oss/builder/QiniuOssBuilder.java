package com.blog.myapp.oss.builder;

import com.blog.myapp.config.ApplicationProperties;
import com.blog.myapp.domain.OssConfig;
import com.blog.myapp.oss.OssRule;
import com.blog.myapp.oss.OssTemplate;
import com.blog.myapp.oss.qiniu.QiniuTemplate;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 七牛云存储构建类
 *
 */
public class QiniuOssBuilder {

    public static OssTemplate template(OssConfig ossConfig, OssRule ossRule) {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.getOss().setEndpoint(ossConfig.getEndpoint());
        applicationProperties.getOss().setAccessKey(ossConfig.getAccessKey());
        applicationProperties.getOss().setSecretKey(ossConfig.getSecretKey());
        applicationProperties.getOss().setBucketName(ossConfig.getBucketName());
        Configuration cfg = new Configuration(Zone.autoZone());
        Auth auth = Auth.create(ossConfig.getAccessKey(), ossConfig.getSecretKey());
        UploadManager uploadManager = new UploadManager(cfg);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        return new QiniuTemplate(auth, uploadManager, bucketManager, applicationProperties, ossRule);
    }
}
