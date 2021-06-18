package com.blog.myapp.domain;

import com.blog.myapp.domain.enumeration.OssProvider;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 对象存储配置
 */

@Entity
@Table(name = "oss_config")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OssConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 提供商
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private OssProvider provider;

    /**
     * 资源编号
     */
    @Column(name = "oss_code")
    private String ossCode;

    /**
     * 资源地址
     */
    @Column(name = "endpoint")
    private String endpoint;

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
     * 空间名
     */
    @Column(name = "bucket_name")
    private String bucketName;

    /**
     * 应用ID
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 地域简称
     */
    @Column(name = "region")
    private String region;

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

    public OssConfig id(Long id) {
        this.id = id;
        return this;
    }

    public OssProvider getProvider() {
        return this.provider;
    }

    public OssConfig provider(OssProvider provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(OssProvider provider) {
        this.provider = provider;
    }

    public String getOssCode() {
        return this.ossCode;
    }

    public OssConfig ossCode(String ossCode) {
        this.ossCode = ossCode;
        return this;
    }

    public void setOssCode(String ossCode) {
        this.ossCode = ossCode;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public OssConfig endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public OssConfig accessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public OssConfig secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public OssConfig bucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAppId() {
        return this.appId;
    }

    public OssConfig appId(String appId) {
        this.appId = appId;
        return this;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRegion() {
        return this.region;
    }

    public OssConfig region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRemark() {
        return this.remark;
    }

    public OssConfig remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public OssConfig enabled(Boolean enabled) {
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
        if (!(o instanceof OssConfig)) {
            return false;
        }
        return id != null && id.equals(((OssConfig) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OssConfig{" +
            "id=" + getId() +
            ", provider='" + getProvider() + "'" +
            ", ossCode='" + getOssCode() + "'" +
            ", endpoint='" + getEndpoint() + "'" +
            ", accessKey='" + getAccessKey() + "'" +
            ", secretKey='" + getSecretKey() + "'" +
            ", bucketName='" + getBucketName() + "'" +
            ", appId='" + getAppId() + "'" +
            ", region='" + getRegion() + "'" +
            ", remark='" + getRemark() + "'" +
            ", enabled='" + getEnabled() + "'" +
            "}";
    }
}
