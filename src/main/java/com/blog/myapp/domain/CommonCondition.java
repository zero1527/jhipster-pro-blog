package com.blog.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
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
 * 通用条件
 */

@Entity
@Table(name = "common_condition")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommonCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @NotNull
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 最后更新时间
     */
    @UpdateTimestamp
    @Column(name = "last_modified_time")
    private ZonedDateTime lastModifiedTime;

    /**
     * 条件项目
     */
    @OneToMany(mappedBy = "commonCondition")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "commonCondition" }, allowSetters = true)
    private Set<CommonConditionItem> items = new LinkedHashSet<>();

    /**
     * 所属模型
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

    public CommonCondition id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public CommonCondition name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public CommonCondition description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public CommonCondition lastModifiedTime(ZonedDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
        return this;
    }

    public void setLastModifiedTime(ZonedDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Set<CommonConditionItem> getItems() {
        return this.items;
    }

    public CommonCondition items(Set<CommonConditionItem> commonConditionItems) {
        this.setItems(commonConditionItems);
        return this;
    }

    public CommonCondition addItems(CommonConditionItem commonConditionItem) {
        this.items.add(commonConditionItem);
        commonConditionItem.setCommonCondition(this);
        return this;
    }

    public CommonCondition removeItems(CommonConditionItem commonConditionItem) {
        this.items.remove(commonConditionItem);
        commonConditionItem.setCommonCondition(null);
        return this;
    }

    public void setItems(Set<CommonConditionItem> commonConditionItems) {
        if (this.items != null) {
            this.items.forEach(i -> i.setCommonCondition(null));
        }
        if (commonConditionItems != null) {
            commonConditionItems.forEach(i -> i.setCommonCondition(this));
        }
        this.items = commonConditionItems;
    }

    public CommonTable getCommonTable() {
        return this.commonTable;
    }

    public CommonCondition commonTable(CommonTable commonTable) {
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
        if (!(o instanceof CommonCondition)) {
            return false;
        }
        return id != null && id.equals(((CommonCondition) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommonCondition{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", lastModifiedTime='" + getLastModifiedTime() + "'" +
            "}";
    }
}
