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
 * 上传图片
 */

@Entity
@Table(name = "upload_image")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UploadImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 完整文件名，不含路径
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 文件名，不含扩展名
     */
    @Column(name = "name")
    private String name;

    /**
     * 扩展名
     */
    @Column(name = "ext")
    private String ext;

    /**
     * 文件类型
     */
    @Column(name = "type")
    private String type;

    /**
     * Web Url地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 本地路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 本地存储目录
     */
    @Column(name = "folder")
    private String folder;

    /**
     * 使用实体名称
     */
    @Column(name = "entity_name")
    private String entityName;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private ZonedDateTime createAt;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private Long fileSize;

    /**
     * 小图Url
     */
    @Column(name = "smart_url")
    private String smartUrl;

    /**
     * 中等图Url
     */
    @Column(name = "medium_url")
    private String mediumUrl;

    /**
     * 文件被引用次数
     */
    @Column(name = "reference_count")
    private Long referenceCount;

    /**
     * 上传者
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "department", "position" }, allowSetters = true)
    private User user;

    /**
     * 所属分类
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "files", "children", "images", "parent" }, allowSetters = true)
    private ResourceCategory category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UploadImage id(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return this.fullName;
    }

    public UploadImage fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return this.name;
    }

    public UploadImage name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return this.ext;
    }

    public UploadImage ext(String ext) {
        this.ext = ext;
        return this;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getType() {
        return this.type;
    }

    public UploadImage type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public UploadImage url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return this.path;
    }

    public UploadImage path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolder() {
        return this.folder;
    }

    public UploadImage folder(String folder) {
        this.folder = folder;
        return this;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public UploadImage entityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public ZonedDateTime getCreateAt() {
        return this.createAt;
    }

    public UploadImage createAt(ZonedDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public UploadImage fileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getSmartUrl() {
        return this.smartUrl;
    }

    public UploadImage smartUrl(String smartUrl) {
        this.smartUrl = smartUrl;
        return this;
    }

    public void setSmartUrl(String smartUrl) {
        this.smartUrl = smartUrl;
    }

    public String getMediumUrl() {
        return this.mediumUrl;
    }

    public UploadImage mediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
        return this;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public Long getReferenceCount() {
        return this.referenceCount;
    }

    public UploadImage referenceCount(Long referenceCount) {
        this.referenceCount = referenceCount;
        return this;
    }

    public void setReferenceCount(Long referenceCount) {
        this.referenceCount = referenceCount;
    }

    public User getUser() {
        return this.user;
    }

    public UploadImage user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResourceCategory getCategory() {
        return this.category;
    }

    public UploadImage category(ResourceCategory resourceCategory) {
        this.setCategory(resourceCategory);
        return this;
    }

    public void setCategory(ResourceCategory resourceCategory) {
        this.category = resourceCategory;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UploadImage)) {
            return false;
        }
        return id != null && id.equals(((UploadImage) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UploadImage{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", name='" + getName() + "'" +
            ", ext='" + getExt() + "'" +
            ", type='" + getType() + "'" +
            ", url='" + getUrl() + "'" +
            ", path='" + getPath() + "'" +
            ", folder='" + getFolder() + "'" +
            ", entityName='" + getEntityName() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", fileSize=" + getFileSize() +
            ", smartUrl='" + getSmartUrl() + "'" +
            ", mediumUrl='" + getMediumUrl() + "'" +
            ", referenceCount=" + getReferenceCount() +
            "}";
    }
}
