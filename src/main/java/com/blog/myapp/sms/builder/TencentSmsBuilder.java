package com.blog.myapp.sms.builder;

import com.blog.myapp.config.ApplicationProperties;
import com.blog.myapp.domain.SmsConfig;
import com.blog.myapp.sms.SmsTemplate;
import com.blog.myapp.sms.tencent.TencentSmsTemplate;
import com.github.qcloudsms.SmsMultiSender;
import javax.cache.CacheManager;

/**
 * 腾讯云短信构建类
 *
 */
public class TencentSmsBuilder {

    public static SmsTemplate template(SmsConfig sms, CacheManager cacheManager) {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.getSms().setTemplateId(sms.getTemplateId());
        applicationProperties.getSms().setAccessKey(sms.getAccessKey());
        applicationProperties.getSms().setSecretKey(sms.getSecretKey());
        applicationProperties.getSms().setSignName(sms.getSignName());
        SmsMultiSender smsSender = new SmsMultiSender(Integer.parseInt(applicationProperties.getSms().getAccessKey()), sms.getSecretKey());
        return new TencentSmsTemplate(applicationProperties, smsSender, cacheManager);
    }
}
