package com.blog.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 报表存储
 */

@Entity
@Table(name = "u_report_file")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UReportFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 内容
     */
    @Lob
    @Column(name = "content")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private ZonedDateTime createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private ZonedDateTime updateAt;

    /**
     * 关联表
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "commonTableFields", "relationships", "metaModel", "creator", "businessType" }, allowSetters = true)
    private CommonTable commonTable;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UReportFile id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public UReportFile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public UReportFile content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getCreateAt() {
        return this.createAt;
    }

    public UReportFile createAt(ZonedDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public ZonedDateTime getUpdateAt() {
        return this.updateAt;
    }

    public UReportFile updateAt(ZonedDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public void setUpdateAt(ZonedDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public CommonTable getCommonTable() {
        return this.commonTable;
    }

    public UReportFile commonTable(CommonTable commonTable) {
        this.setCommonTable(commonTable);
        return this;
    }

    public void setCommonTable(CommonTable commonTable) {
        this.commonTable = commonTable;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UReportFile)) {
            return false;
        }
        return id != null && id.equals(((UReportFile) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UReportFile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", content='" + getContent() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
}
