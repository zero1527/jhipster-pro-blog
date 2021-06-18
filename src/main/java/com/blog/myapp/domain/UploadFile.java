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
 * 上传文件
 */

@Entity
@Table(name = "upload_file")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UploadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 完整文件名\n不含路径
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 文件名\n不含扩展名
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
     * Url地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 本地路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 存储目录
     */
    @Column(name = "folder")
    private String folder;

    /**
     * 实体名称
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
     * 被引次数
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

    public UploadFile id(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return this.fullName;
    }

    public UploadFile fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return this.name;
    }

    public UploadFile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return this.ext;
    }

    public UploadFile ext(String ext) {
        this.ext = ext;
        return this;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getType() {
        return this.type;
    }

    public UploadFile type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public UploadFile url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return this.path;
    }

    public UploadFile path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolder() {
        return this.folder;
    }

    public UploadFile folder(String folder) {
        this.folder = folder;
        return this;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public UploadFile entityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public ZonedDateTime getCreateAt() {
        return this.createAt;
    }

    public UploadFile createAt(ZonedDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public UploadFile fileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getReferenceCount() {
        return this.referenceCount;
    }

    public UploadFile referenceCount(Long referenceCount) {
        this.referenceCount = referenceCount;
        return this;
    }

    public void setReferenceCount(Long referenceCount) {
        this.referenceCount = referenceCount;
    }

    public User getUser() {
        return this.user;
    }

    public UploadFile user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResourceCategory getCategory() {
        return this.category;
    }

    public UploadFile category(ResourceCategory resourceCategory) {
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
        if (!(o instanceof UploadFile)) {
            return false;
        }
        return id != null && id.equals(((UploadFile) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UploadFile{" +
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
            ", referenceCount=" + getReferenceCount() +
            "}";
    }
}
