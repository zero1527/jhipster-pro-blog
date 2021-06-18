package com.blog.myapp.sms.builder;

import com.blog.myapp.config.ApplicationProperties;
import com.blog.myapp.domain.SmsConfig;
import com.blog.myapp.sms.SmsTemplate;
import com.blog.myapp.sms.qiniu.QiniuSmsTemplate;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import javax.cache.CacheManager;

/**
 * 七牛云短信构建类
 *
 */
public class QiniuSmsBuilder {

    public static SmsTemplate template(SmsConfig sms, CacheManager cacheManager) {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.getSms().setTemplateId(sms.getTemplateId());
        applicationProperties.getSms().setAccessKey(sms.getAccessKey());
        applicationProperties.getSms().setSecretKey(sms.getSecretKey());
        applicationProperties.getSms().setSignName(sms.getSignName());
        Auth auth = Auth.create(applicationProperties.getSms().getAccessKey(), applicationProperties.getSms().getSecretKey());
        SmsManager smsManager = new SmsManager(auth);
        return new QiniuSmsTemplate(applicationProperties, smsManager, cacheManager);
    }
}
