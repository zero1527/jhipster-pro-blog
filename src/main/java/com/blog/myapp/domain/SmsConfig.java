package com.blog.myapp.domain;

import com.blog.myapp.domain.enumeration.SmsProvider;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 短信配置
 */

@Entity
@Table(name = "sms_config")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 提供商
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private SmsProvider provider;

    /**
     * 资源编号
     */
    @Column(name = "sms_code")
    private String smsCode;

    /**
     * 模板ID
     */
    @Column(name = "template_id")
    private String templateId;

    /**
     * accessKey
     */
    @Column(name = "access_key")
    private String accessKey;

    /**
     * secretKey
     */
    @Column(name = "secret_key")
    private String secretKey;

    /**
     * regionId
     */
    @Column(name = "region_id")
    private String regionId;

    /**
     * 短信签名
     */
    @Column(name = "sign_name")
    private String signName;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 启用
     */
    @Column(name = "enabled")
    private Boolean enabled;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SmsConfig id(Long id) {
        this.id = id;
        return this;
    }

    public SmsProvider getProvider() {
        return this.provider;
    }

    public SmsConfig provider(SmsProvider provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(SmsProvider provider) {
        this.provider = provider;
    }

    public String getSmsCode() {
        return this.smsCode;
    }

    public SmsConfig smsCode(String smsCode) {
        this.smsCode = smsCode;
        return this;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public SmsConfig templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public SmsConfig accessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public SmsConfig secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegionId() {
        return this.regionId;
    }

    public SmsConfig regionId(String regionId) {
        this.regionId = regionId;
        return this;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSignName() {
        return this.signName;
    }

    public SmsConfig signName(String signName) {
        this.signName = signName;
        return this;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getRemark() {
        return this.remark;
    }

    public SmsConfig remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public SmsConfig enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SmsConfig)) {
            return false;
        }
        return id != null && id.equals(((SmsConfig) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SmsConfig{" +
            "id=" + getId() +
            ", provider='" + getProvider() + "'" +
            ", smsCode='" + getSmsCode() + "'" +
            ", templateId='" + getTemplateId() + "'" +
            ", accessKey='" + getAccessKey() + "'" +
            ", secretKey='" + getSecretKey() + "'" +
            ", regionId='" + getRegionId() + "'" +
            ", signName='" + getSignName() + "'" +
            ", remark='" + getRemark() + "'" +
            ", enabled='" + getEnabled() + "'" +
            "}";
    }
}
