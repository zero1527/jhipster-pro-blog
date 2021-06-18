package com.blog.myapp.domain;

import com.blog.myapp.domain.enumeration.TargetType;
import com.blog.myapp.domain.enumeration.ViewPermissionType;
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
 * 可视权限
 * 权限分为菜单权限、按钮权限等\n
 */

@Entity
@Table(name = "view_permission")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ViewPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限名称
     */
    @Column(name = "text")
    private String text;

    /**
     * i18n主键
     */
    @Column(name = "i_18_n")
    private String i18n;

    /**
     * 显示分组名
     */
    @Column(name = "jhi_group")
    private Boolean group;

    /**
     * 路由
     */
    @Column(name = "link")
    private String link;

    /**
     * 外部链接
     */
    @Column(name = "external_link")
    private String externalLink;

    /**
     * 链接目标
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "target")
    private TargetType target;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 禁用菜单
     */
    @Column(name = "disabled")
    private Boolean disabled;

    /**
     * 隐藏菜单
     */
    @Column(name = "hide")
    private Boolean hide;

    /**
     * 隐藏面包屑
     */
    @Column(name = "hide_in_breadcrumb")
    private Boolean hideInBreadcrumb;

    /**
     * 快捷菜单项
     */
    @Column(name = "shortcut")
    private Boolean shortcut;

    /**
     * 菜单根节点
     */
    @Column(name = "shortcut_root")
    private Boolean shortcutRoot;

    /**
     * 允许复用
     */
    @Column(name = "reuse")
    private Boolean reuse;

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
     * 权限类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ViewPermissionType type;

    /**
     * 排序
     */
    @Column(name = "jhi_order")
    private Integer order;

    /**
     * api权限标识串
     */
    @Column(name = "api_permission_codes")
    private String apiPermissionCodes;

    /**
     * 子节点
     */
    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "children", "parent", "authorities" }, allowSetters = true)
    private Set<ViewPermission> children = new LinkedHashSet<>();

    /**
     * 上级
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "children", "parent", "authorities" }, allowSetters = true)
    private ViewPermission parent;

    /**
     * 角色列表
     */
    @ManyToMany(mappedBy = "viewPermissions")
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

    public ViewPermission id(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public ViewPermission text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String geti18n() {
        return this.i18n;
    }

    public ViewPermission i18n(String i18n) {
        this.i18n = i18n;
        return this;
    }

    public void seti18n(String i18n) {
        this.i18n = i18n;
    }

    public Boolean getGroup() {
        return this.group;
    }

    public ViewPermission group(Boolean group) {
        this.group = group;
        return this;
    }

    public void setGroup(Boolean group) {
        this.group = group;
    }

    public String getLink() {
        return this.link;
    }

    public ViewPermission link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getExternalLink() {
        return this.externalLink;
    }

    public ViewPermission externalLink(String externalLink) {
        this.externalLink = externalLink;
        return this;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public TargetType getTarget() {
        return this.target;
    }

    public ViewPermission target(TargetType target) {
        this.target = target;
        return this;
    }

    public void setTarget(TargetType target) {
        this.target = target;
    }

    public String getIcon() {
        return this.icon;
    }

    public ViewPermission icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getDisabled() {
        return this.disabled;
    }

    public ViewPermission disabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getHide() {
        return this.hide;
    }

    public ViewPermission hide(Boolean hide) {
        this.hide = hide;
        return this;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    public Boolean getHideInBreadcrumb() {
        return this.hideInBreadcrumb;
    }

    public ViewPermission hideInBreadcrumb(Boolean hideInBreadcrumb) {
        this.hideInBreadcrumb = hideInBreadcrumb;
        return this;
    }

    public void setHideInBreadcrumb(Boolean hideInBreadcrumb) {
        this.hideInBreadcrumb = hideInBreadcrumb;
    }

    public Boolean getShortcut() {
        return this.shortcut;
    }

    public ViewPermission shortcut(Boolean shortcut) {
        this.shortcut = shortcut;
        return this;
    }

    public void setShortcut(Boolean shortcut) {
        this.shortcut = shortcut;
    }

    public Boolean getShortcutRoot() {
        return this.shortcutRoot;
    }

    public ViewPermission shortcutRoot(Boolean shortcutRoot) {
        this.shortcutRoot = shortcutRoot;
        return this;
    }

    public void setShortcutRoot(Boolean shortcutRoot) {
        this.shortcutRoot = shortcutRoot;
    }

    public Boolean getReuse() {
        return this.reuse;
    }

    public ViewPermission reuse(Boolean reuse) {
        this.reuse = reuse;
        return this;
    }

    public void setReuse(Boolean reuse) {
        this.reuse = reuse;
    }

    public String getCode() {
        return this.code;
    }

    public ViewPermission code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public ViewPermission description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ViewPermissionType getType() {
        return this.type;
    }

    public ViewPermission type(ViewPermissionType type) {
        this.type = type;
        return this;
    }

    public void setType(ViewPermissionType type) {
        this.type = type;
    }

    public Integer getOrder() {
        return this.order;
    }

    public ViewPermission order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getApiPermissionCodes() {
        return this.apiPermissionCodes;
    }

    public ViewPermission apiPermissionCodes(String apiPermissionCodes) {
        this.apiPermissionCodes = apiPermissionCodes;
        return this;
    }

    public void setApiPermissionCodes(String apiPermissionCodes) {
        this.apiPermissionCodes = apiPermissionCodes;
    }

    public Set<ViewPermission> getChildren() {
        return this.children;
    }

    public ViewPermission children(Set<ViewPermission> viewPermissions) {
        this.setChildren(viewPermissions);
        return this;
    }

    public ViewPermission addChildren(ViewPermission viewPermission) {
        this.children.add(viewPermission);
        viewPermission.setParent(this);
        return this;
    }

    public ViewPermission removeChildren(ViewPermission viewPermission) {
        this.children.remove(viewPermission);
        viewPermission.setParent(null);
        return this;
    }

    public void setChildren(Set<ViewPermission> viewPermissions) {
        if (this.children != null) {
            this.children.forEach(i -> i.setParent(null));
        }
        if (viewPermissions != null) {
            viewPermissions.forEach(i -> i.setParent(this));
        }
        this.children = viewPermissions;
    }

    public ViewPermission getParent() {
        return this.parent;
    }

    public ViewPermission parent(ViewPermission viewPermission) {
        this.setParent(viewPermission);
        return this;
    }

    public void setParent(ViewPermission viewPermission) {
        this.parent = viewPermission;
    }

    public Set<Authority> getAuthorities() {
        return this.authorities;
    }

    public ViewPermission authorities(Set<Authority> authorities) {
        this.setAuthorities(authorities);
        return this;
    }

    public ViewPermission addAuthorities(Authority authority) {
        this.authorities.add(authority);
        return this;
    }

    public ViewPermission removeAuthorities(Authority authority) {
        this.authorities.remove(authority);
        return this;
    }

    public void setAuthorities(Set<Authority> authorities) {
        if (this.authorities != null) {
            this.authorities.forEach(i -> i.removeViewPermissions(this));
        }
        if (authorities != null) {
            authorities.forEach(i -> i.addViewPermissions(this));
        }
        this.authorities = authorities;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewPermission)) {
            return false;
        }
        return id != null && id.equals(((ViewPermission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewPermission{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", i18n='" + geti18n() + "'" +
            ", group='" + getGroup() + "'" +
            ", link='" + getLink() + "'" +
            ", externalLink='" + getExternalLink() + "'" +
            ", target='" + getTarget() + "'" +
            ", icon='" + getIcon() + "'" +
            ", disabled='" + getDisabled() + "'" +
            ", hide='" + getHide() + "'" +
            ", hideInBreadcrumb='" + getHideInBreadcrumb() + "'" +
            ", shortcut='" + getShortcut() + "'" +
            ", shortcutRoot='" + getShortcutRoot() + "'" +
            ", reuse='" + getReuse() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", order=" + getOrder() +
            ", apiPermissionCodes='" + getApiPermissionCodes() + "'" +
            "}";
    }
}
