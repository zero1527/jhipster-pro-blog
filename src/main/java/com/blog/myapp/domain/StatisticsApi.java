package com.blog.myapp.domain;

import com.blog.myapp.domain.enumeration.StatSourceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 统计Api
 */

@Entity
@Table(name = "statistics_api")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StatisticsApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    @Size(max = 200)
    @Column(name = "title", length = 200)
    private String title;

    /**
     * ApiKey
     */

    @Column(name = "api_key", unique = true)
    private String apiKey;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "create_at")
    private ZonedDateTime createAt;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(name = "update_at")
    private ZonedDateTime updateAt;

    /**
     * 来源类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "source_type")
    private StatSourceType sourceType;

    /**
     * 主体内容
     */
    @Lob
    @Column(name = "api_body")
    private String apiBody;

    /**
     * 可能存放的结果
     */
    @Lob
    @Column(name = "result")
    private String result;

    /**
     * 更新间隔(秒)
     */
    @Column(name = "update_interval")
    private Integer updateInterval;

    /**
     * 最新运行时间
     */
    @Column(name = "last_sql_run_time")
    private ZonedDateTime lastSQLRunTime;

    /**
     * 是否可用
     */
    @Column(name = "enable")
    private Boolean enable;

    /**
     * 所属表
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "commonTableFields", "relationships", "metaModel", "creator", "businessType" }, allowSetters = true)
    private CommonTable commonTable;

    /**
     * 创建人
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "department", "position" }, allowSetters = true)
    private User creator;

    /**
     * 修改人
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "department", "position" }, allowSetters = true)
    private User modifier;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticsApi id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public StatisticsApi title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public StatisticsApi apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ZonedDateTime getCreateAt() {
        return this.createAt;
    }

    public StatisticsApi createAt(ZonedDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public ZonedDateTime getUpdateAt() {
        return this.updateAt;
    }

    public StatisticsApi updateAt(ZonedDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public void setUpdateAt(ZonedDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public StatSourceType getSourceType() {
        return this.sourceType;
    }

    public StatisticsApi sourceType(StatSourceType sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public void setSourceType(StatSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getApiBody() {
        return this.apiBody;
    }

    public StatisticsApi apiBody(String apiBody) {
        this.apiBody = apiBody;
        return this;
    }

    public void setApiBody(String apiBody) {
        this.apiBody = apiBody;
    }

    public String getResult() {
        return this.result;
    }

    public StatisticsApi result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getUpdateInterval() {
        return this.updateInterval;
    }

    public StatisticsApi updateInterval(Integer updateInterval) {
        this.updateInterval = updateInterval;
        return this;
    }

    public void setUpdateInterval(Integer updateInterval) {
        this.updateInterval = updateInterval;
    }

    public ZonedDateTime getLastSQLRunTime() {
        return this.lastSQLRunTime;
    }

    public StatisticsApi lastSQLRunTime(ZonedDateTime lastSQLRunTime) {
        this.lastSQLRunTime = lastSQLRunTime;
        return this;
    }

    public void setLastSQLRunTime(ZonedDateTime lastSQLRunTime) {
        this.lastSQLRunTime = lastSQLRunTime;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public StatisticsApi enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public CommonTable getCommonTable() {
        return this.commonTable;
    }

    public StatisticsApi commonTable(CommonTable commonTable) {
        this.setCommonTable(commonTable);
        return this;
    }

    public void setCommonTable(CommonTable commonTable) {
        this.commonTable = commonTable;
    }

    public User getCreator() {
        return this.creator;
    }

    public StatisticsApi creator(User user) {
        this.setCreator(user);
        return this;
    }

    public void setCreator(User user) {
        this.creator = user;
    }

    public User getModifier() {
        return this.modifier;
    }

    public StatisticsApi modifier(User user) {
        this.setModifier(user);
        return this;
    }

    public void setModifier(User user) {
        this.modifier = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatisticsApi)) {
            return false;
        }
        return id != null && id.equals(((StatisticsApi) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StatisticsApi{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", apiKey='" + getApiKey() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            ", sourceType='" + getSourceType() + "'" +
            ", apiBody='" + getApiBody() + "'" +
            ", result='" + getResult() + "'" +
            ", updateInterval=" + getUpdateInterval() +
            ", lastSQLRunTime='" + getLastSQLRunTime() + "'" +
            ", enable='" + getEnable() + "'" +
            "}";
    }
}
