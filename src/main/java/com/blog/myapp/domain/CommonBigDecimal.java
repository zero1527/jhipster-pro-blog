package com.blog.myapp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 通用大数
 *
 */

@Entity
@Table(name = "common_big_decimal")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommonBigDecimal implements Serializable, CommonField {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 宿主类别名称
     */
    @Column(name = "owner_type")
    private String ownerType;

    /**
     * 宿主Id
     */
    @Column(name = "owner_id")
    private Long ownerId;

    /**
     * 对应属性名
     */
    @Column(name = "field_name")
    private String fieldName;

    /**
     * 值
     */
    @Column(name = "value", precision = 21, scale = 2)
    private BigDecimal value;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommonBigDecimal id(Long id) {
        this.id = id;
        return this;
    }

    public String getOwnerType() {
        return this.ownerType;
    }

    public CommonBigDecimal ownerType(String ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public CommonBigDecimal ownerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public CommonBigDecimal fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public CommonBigDecimal value(BigDecimal value) {
        this.value = value;
        return this;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommonBigDecimal)) {
            return false;
        }
        return id != null && id.equals(((CommonBigDecimal) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommonBigDecimal{" +
            "id=" + getId() +
            ", ownerType='" + getOwnerType() + "'" +
            ", ownerId=" + getOwnerId() +
            ", fieldName='" + getFieldName() + "'" +
            ", value=" + getValue() +
            "}";
    }
}
