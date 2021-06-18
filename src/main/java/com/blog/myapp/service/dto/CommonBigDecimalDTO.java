package com.blog.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**

 * {@link com.blog.myapp.domain.CommonBigDecimal}的DTO。
 */
@ApiModel(description = "通用大数\n")
public class CommonBigDecimalDTO implements Serializable {

    private Long id;

    /**
     * 宿主类别名称
     */
    @ApiModelProperty(value = "宿主类别名称")
    private String ownerType;

    /**
     * 宿主Id
     */
    @ApiModelProperty(value = "宿主Id")
    private Long ownerId;

    /**
     * 对应属性名
     */
    @ApiModelProperty(value = "对应属性名")
    private String fieldName;

    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    private BigDecimal value;

    // jhipster-needle-dto-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    // jhipster-needle-dto-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommonBigDecimalDTO)) {
            return false;
        }

        CommonBigDecimalDTO commonBigDecimalDTO = (CommonBigDecimalDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, commonBigDecimalDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommonBigDecimalDTO{" +
            "id=" + getId() +
            ", ownerType='" + getOwnerType() + "'" +
            ", ownerId=" + getOwnerId() +
            ", fieldName='" + getFieldName() + "'" +
            ", value=" + getValue() +
            "}";
    }
}
