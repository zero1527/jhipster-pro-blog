package com.blog.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 行政区划
 */

@Entity
@Table(name = "administrative_division")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdministrativeDivision implements Serializable {

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
     * 地区代码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 城市代码
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 全名
     */
    @Column(name = "merger_name")
    private String mergerName;

    /**
     * 短名称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 邮政编码
     */
    @Column(name = "zip_code")
    private String zipCode;

    /**
     * 行政区域等级（0: 省级 1:市级 2:县级 3:镇级 4:乡村级）
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 经度
     */
    @Column(name = "lng")
    private Double lng;

    /**
     * 纬度
     */
    @Column(name = "lat")
    private Double lat;

    /**
     * 子节点
     */
    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "children", "parent" }, allowSetters = true)
    private Set<AdministrativeDivision> children = new LinkedHashSet<>();

    /**
     * 上级节点
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "children", "parent" }, allowSetters = true)
    private AdministrativeDivision parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdministrativeDivision id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public AdministrativeDivision name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public AdministrativeDivision areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public AdministrativeDivision cityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getMergerName() {
        return this.mergerName;
    }

    public AdministrativeDivision mergerName(String mergerName) {
        this.mergerName = mergerName;
        return this;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public AdministrativeDivision shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public AdministrativeDivision zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getLevel() {
        return this.level;
    }

    public AdministrativeDivision level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getLng() {
        return this.lng;
    }

    public AdministrativeDivision lng(Double lng) {
        this.lng = lng;
        return this;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return this.lat;
    }

    public AdministrativeDivision lat(Double lat) {
        this.lat = lat;
        return this;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Set<AdministrativeDivision> getChildren() {
        return this.children;
    }

    public AdministrativeDivision children(Set<AdministrativeDivision> administrativeDivisions) {
        this.setChildren(administrativeDivisions);
        return this;
    }

    public AdministrativeDivision addChildren(AdministrativeDivision administrativeDivision) {
        this.children.add(administrativeDivision);
        administrativeDivision.setParent(this);
        return this;
    }

    public AdministrativeDivision removeChildren(AdministrativeDivision administrativeDivision) {
        this.children.remove(administrativeDivision);
        administrativeDivision.setParent(null);
        return this;
    }

    public void setChildren(Set<AdministrativeDivision> administrativeDivisions) {
        if (this.children != null) {
            this.children.forEach(i -> i.setParent(null));
        }
        if (administrativeDivisions != null) {
            administrativeDivisions.forEach(i -> i.setParent(this));
        }
        this.children = administrativeDivisions;
    }

    public AdministrativeDivision getParent() {
        return this.parent;
    }

    public AdministrativeDivision parent(AdministrativeDivision administrativeDivision) {
        this.setParent(administrativeDivision);
        return this;
    }

    public void setParent(AdministrativeDivision administrativeDivision) {
        this.parent = administrativeDivision;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdministrativeDivision)) {
            return false;
        }
        return id != null && id.equals(((AdministrativeDivision) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdministrativeDivision{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", areaCode='" + getAreaCode() + "'" +
            ", cityCode='" + getCityCode() + "'" +
            ", mergerName='" + getMergerName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", level=" + getLevel() +
            ", lng=" + getLng() +
            ", lat=" + getLat() +
            "}";
    }
}
