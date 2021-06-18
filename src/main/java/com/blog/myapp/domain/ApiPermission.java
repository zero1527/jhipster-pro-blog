package com.blog.myapp.domain;

import com.blog.myapp.domain.enumeration.ApiPermissionType;
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
 * API权限
 * 菜单或按钮下有相关的api权限
 */

@Entity
@Table(name = "api_permission")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApiPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 服务名称
     */
    @Column(name = "service_name")
    private String serviceName;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 权限代码(ROLE_开头)
     */
    @Column(name = "code")
    private String code;

    /**
     * 权限描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ApiPermissionType type;

    /**
     * 请求类型
     */
    @Column(name = "method")
    private String method;

    /**
     * url 地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 子节点
     */
    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "children", "parent", "authorities" }, allowSetters = true)
    private Set<ApiPermission> children = new LinkedHashSet<>();

    /**
     * 上级
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "children", "parent", "authorities" }, allowSetters = true)
    private ApiPermission parent;

    /**
     * 角色列表
     */
    @ManyToMany(mappedBy = "apiPermissions")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "departments", "apiPermissions", "viewPermissions" }, allowSetters = true)
    private Set<Authority> authorities = new LinkedHashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiPermission id(Long id) {
        this.id = id;
        return this;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public ApiPermission serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getName() {
        return this.name;
    }

    public ApiPermission name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public ApiPermission code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public ApiPermission description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApiPermissionType getType() {
        return this.type;
    }

    public ApiPermission type(ApiPermissionType type) {
        this.type = type;
        return this;
    }

    public void setType(ApiPermissionType type) {
        this.type = type;
    }

    public String getMethod() {
        return this.method;
    }

    public ApiPermission method(String method) {
        this.method = method;
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return this.url;
    }

    public ApiPermission url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<ApiPermission> getChildren() {
        return this.children;
    }

    public ApiPermission children(Set<ApiPermission> apiPermissions) {
        this.setChildren(apiPermissions);
        return this;
    }

    public ApiPermission addChildren(ApiPermission apiPermission) {
        this.children.add(apiPermission);
        apiPermission.setParent(this);
        return this;
    }

    public ApiPermission removeChildren(ApiPermission apiPermission) {
        this.children.remove(apiPermission);
        apiPermission.setParent(null);
        return this;
    }

    public void setChildren(Set<ApiPermission> apiPermissions) {
        if (this.children != null) {
            this.children.forEach(i -> i.setParent(null));
        }
        if (apiPermissions != null) {
            apiPermissions.forEach(i -> i.setParent(this));
        }
        this.children = apiPermissions;
    }

    public ApiPermission getParent() {
        return this.parent;
    }

    public ApiPermission parent(ApiPermission apiPermission) {
        this.setParent(apiPermission);
        return this;
    }

    public void setParent(ApiPermission apiPermission) {
        this.parent = apiPermission;
    }

    public Set<Authority> getAuthorities() {
        return this.authorities;
    }

    public ApiPermission authorities(Set<Authority> authorities) {
        this.setAuthorities(authorities);
        return this;
    }

    public ApiPermission addAuthorities(Authority authority) {
        this.authorities.add(authority);
        return this;
    }

    public ApiPermission removeAuthorities(Authority authority) {
        this.authorities.remove(authority);
        return this;
    }

    public void setAuthorities(Set<Authority> authorities) {
        if (this.authorities != null) {
            this.authorities.forEach(i -> i.removeApiPermissions(this));
        }
        if (authorities != null) {
            authorities.forEach(i -> i.addApiPermissions(this));
        }
        this.authorities = authorities;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiPermission)) {
            return false;
        }
        return id != null && id.equals(((ApiPermission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiPermission{" +
            "id=" + getId() +
            ", serviceName='" + getServiceName() + "'" +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", method='" + getMethod() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
