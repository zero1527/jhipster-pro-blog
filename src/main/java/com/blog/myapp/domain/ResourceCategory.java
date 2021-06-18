package com.blog.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 资源分类
 */

@Entity
@Table(name = "resource_category")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ResourceCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    @Size(max = 40)
    @Column(name = "title", length = 40)
    private String title;

    /**
     * 代码
     */
    @Size(max = 20)
    @Column(name = "code", length = 20)
    private String code;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 文件列表
     */
    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "category" }, allowSetters = true)
    private Set<UploadFile> files = new LinkedHashSet<>();

    /**
     * 下级列表
     */
    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "files", "children", "images", "parent" }, allowSetters = true)
    private Set<ResourceCategory> children = new LinkedHashSet<>();

    /**
     * 图片列表
     */
    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "category" }, allowSetters = true)
    private Set<UploadImage> images = new LinkedHashSet<>();

    /**
     * 上级
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "files", "children", "images", "parent" }, allowSetters = true)
    private ResourceCategory parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceCategory id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public ResourceCategory title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return this.code;
    }

    public ResourceCategory code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return this.sort;
    }

    public ResourceCategory sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Set<UploadFile> getFiles() {
        return this.files;
    }

    public ResourceCategory files(Set<UploadFile> uploadFiles) {
        this.setFiles(uploadFiles);
        return this;
    }

    public ResourceCategory addFiles(UploadFile uploadFile) {
        this.files.add(uploadFile);
        uploadFile.setCategory(this);
        return this;
    }

    public ResourceCategory removeFiles(UploadFile uploadFile) {
        this.files.remove(uploadFile);
        uploadFile.setCategory(null);
        return this;
    }

    public void setFiles(Set<UploadFile> uploadFiles) {
        if (this.files != null) {
            this.files.forEach(i -> i.setCategory(null));
        }
        if (uploadFiles != null) {
            uploadFiles.forEach(i -> i.setCategory(this));
        }
        this.files = uploadFiles;
    }

    public Set<ResourceCategory> getChildren() {
        return this.children;
    }

    public ResourceCategory children(Set<ResourceCategory> resourceCategories) {
        this.setChildren(resourceCategories);
        return this;
    }

    public ResourceCategory addChildren(ResourceCategory resourceCategory) {
        this.children.add(resourceCategory);
        resourceCategory.setParent(this);
        return this;
    }

    public ResourceCategory removeChildren(ResourceCategory resourceCategory) {
        this.children.remove(resourceCategory);
        resourceCategory.setParent(null);
        return this;
    }

    public void setChildren(Set<ResourceCategory> resourceCategories) {
        if (this.children != null) {
            this.children.forEach(i -> i.setParent(null));
        }
        if (resourceCategories != null) {
            resourceCategories.forEach(i -> i.setParent(this));
        }
        this.children = resourceCategories;
    }

    public Set<UploadImage> getImages() {
        return this.images;
    }

    public ResourceCategory images(Set<UploadImage> uploadImages) {
        this.setImages(uploadImages);
        return this;
    }

    public ResourceCategory addImages(UploadImage uploadImage) {
        this.images.add(uploadImage);
        uploadImage.setCategory(this);
        return this;
    }

    public ResourceCategory removeImages(UploadImage uploadImage) {
        this.images.remove(uploadImage);
        uploadImage.setCategory(null);
        return this;
    }

    public void setImages(Set<UploadImage> uploadImages) {
        if (this.images != null) {
            this.images.forEach(i -> i.setCategory(null));
        }
        if (uploadImages != null) {
            uploadImages.forEach(i -> i.setCategory(this));
        }
        this.images = uploadImages;
    }

    public ResourceCategory getParent() {
        return this.parent;
    }

    public ResourceCategory parent(ResourceCategory resourceCategory) {
        this.setParent(resourceCategory);
        return this;
    }

    public void setParent(ResourceCategory resourceCategory) {
        this.parent = resourceCategory;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceCategory)) {
            return false;
        }
        return id != null && id.equals(((ResourceCategory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceCategory{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", sort=" + getSort() +
            "}";
    }
}
