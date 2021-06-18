package com.blog.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.blog.myapp.domain.CommonFloat} entity. This class is used
 * in {@link com.blog.myapp.web.rest.CommonFloatResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /common-floats?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CommonFloatCriteria implements Serializable, Criteria {

    private String jhiCommonSearchKeywords;

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ownerType;

    private LongFilter ownerId;

    private StringFilter fieldName;

    private FloatFilter value;

    public CommonFloatCriteria() {}

    public CommonFloatCriteria(CommonFloatCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ownerType = other.ownerType == null ? null : other.ownerType.copy();
        this.ownerId = other.ownerId == null ? null : other.ownerId.copy();
        this.fieldName = other.fieldName == null ? null : other.fieldName.copy();
        this.value = other.value == null ? null : other.value.copy();
    }

    @Override
    public CommonFloatCriteria copy() {
        return new CommonFloatCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOwnerType() {
        return ownerType;
    }

    public StringFilter ownerType() {
        if (ownerType == null) {
            ownerType = new StringFilter();
        }
        return ownerType;
    }

    public void setOwnerType(StringFilter ownerType) {
        this.ownerType = ownerType;
    }

    public LongFilter getOwnerId() {
        return ownerId;
    }

    public LongFilter ownerId() {
        if (ownerId == null) {
            ownerId = new LongFilter();
        }
        return ownerId;
    }

    public void setOwnerId(LongFilter ownerId) {
        this.ownerId = ownerId;
    }

    public StringFilter getFieldName() {
        return fieldName;
    }

    public StringFilter fieldName() {
        if (fieldName == null) {
            fieldName = new StringFilter();
        }
        return fieldName;
    }

    public void setFieldName(StringFilter fieldName) {
        this.fieldName = fieldName;
    }

    public FloatFilter getValue() {
        return value;
    }

    public FloatFilter value() {
        if (value == null) {
            value = new FloatFilter();
        }
        return value;
    }

    public void setValue(FloatFilter value) {
        this.value = value;
    }

    public String getJhiCommonSearchKeywords() {
        return jhiCommonSearchKeywords;
    }

    public void setJhiCommonSearchKeywords(String jhiCommonSearchKeywords) {
        this.jhiCommonSearchKeywords = jhiCommonSearchKeywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CommonFloatCriteria that = (CommonFloatCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(ownerType, that.ownerType) &&
            Objects.equals(ownerId, that.ownerId) &&
            Objects.equals(fieldName, that.fieldName) &&
            Objects.equals(value, that.value)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerType, ownerId, fieldName, value);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommonFloatCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ownerType != null ? "ownerType=" + ownerType + ", " : "") +
                (ownerId != null ? "ownerId=" + ownerId + ", " : "") +
                (fieldName != null ? "fieldName=" + fieldName + ", " : "") +
                (value != null ? "value=" + value + ", " : "") +
            "}";
    }
}
