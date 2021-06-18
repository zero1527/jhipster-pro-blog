package com.blog.myapp.sms.builder;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.blog.myapp.config.ApplicationProperties;
import com.blog.myapp.domain.SmsConfig;
import com.blog.myapp.sms.SmsTemplate;
import com.blog.myapp.sms.aliyun.AliSmsTemplate;
import javax.cache.CacheManager;

/**
 * 阿里云短信构建类
 *
 */
public class AliSmsBuilder {

    public static SmsTemplate template(SmsConfig sms, CacheManager cacheManager) {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.getSms().setTemplateId(sms.getTemplateId());
        applicationProperties.getSms().setAccessKey(sms.getAccessKey());
        applicationProperties.getSms().setSecretKey(sms.getSecretKey());
        applicationProperties.getSms().setRegionId(sms.getRegionId());
        applicationProperties.getSms().setSignName(sms.getSignName());
        IClientProfile profile = DefaultProfile.getProfile(
            applicationProperties.getSms().getRegionId(),
            applicationProperties.getSms().getAccessKey(),
            applicationProperties.getSms().getSecretKey()
        );
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return new AliSmsTemplate(applicationProperties, acsClient, cacheManager);
    }
}
