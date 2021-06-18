package com.blog.myapp.domain;

import com.blog.myapp.domain.enumeration.GpsType;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * GPS信息
 */

@Entity
@Table(name = "gps_info")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GpsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * gps坐标类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private GpsType type;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 地址描述
     */
    @Column(name = "address")
    private String address;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GpsInfo id(Long id) {
        this.id = id;
        return this;
    }

    public GpsType getType() {
        return this.type;
    }

    public GpsInfo type(GpsType type) {
        this.type = type;
        return this;
    }

    public void setType(GpsType type) {
        this.type = type;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public GpsInfo latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public GpsInfo longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return this.address;
    }

    public GpsInfo address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GpsInfo)) {
            return false;
        }
        return id != null && id.equals(((GpsInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GpsInfo{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
