package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.ResourceCategory;
import com.blog.myapp.repository.ResourceCategoryRepository;
import com.blog.myapp.service.dto.ResourceCategoryDTO;
import com.blog.myapp.service.mapper.ResourceCategoryMapper;
import java.beans.PropertyDescriptor;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Service Implementation for managing {@link ResourceCategory}.
 */
@Service
@Transactional
public class ResourceCategoryService {

    private final Logger log = LoggerFactory.getLogger(ResourceCategoryService.class);
    private final List<String> relationCacheNames = Arrays.asList(
        com.blog.myapp.domain.UploadFile.class.getName() + ".category",
        com.blog.myapp.domain.ResourceCategory.class.getName() + ".parent",
        com.blog.myapp.domain.UploadImage.class.getName() + ".category",
        com.blog.myapp.domain.ResourceCategory.class.getName() + ".children"
    );

    private final ResourceCategoryRepository resourceCategoryRepository;

    private final CacheManager cacheManager;

    private final ResourceCategoryMapper resourceCategoryMapper;

    public ResourceCategoryService(
        ResourceCategoryRepository resourceCategoryRepository,
        CacheManager cacheManager,
        ResourceCategoryMapper resourceCategoryMapper
    ) {
        this.resourceCategoryRepository = resourceCategoryRepository;
        this.cacheManager = cacheManager;
        this.resourceCategoryMapper = resourceCategoryMapper;
    }

    /**
     * Save a resourceCategory.
     *
     * @param resourceCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceCategoryDTO save(ResourceCategoryDTO resourceCategoryDTO) {
        log.debug("Request to save ResourceCategory : {}", resourceCategoryDTO);
        ResourceCategory resourceCategory = resourceCategoryMapper.toEntity(resourceCategoryDTO);
        clearChildrenCache();
        resourceCategory = resourceCategoryRepository.save(resourceCategory);
        // 更新缓存
        if (resourceCategory.getParent() != null) {
            resourceCategory.getParent().addChildren(resourceCategory);
        }
        return resourceCategoryMapper.toDto(resourceCategory);
    }

    /**
     * Partially update a resourceCategory.
     *
     * @param resourceCategoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceCategoryDTO> partialUpdate(ResourceCategoryDTO resourceCategoryDTO) {
        log.debug("Request to partially update ResourceCategory : {}", resourceCategoryDTO);

        return resourceCategoryRepository
            .findById(resourceCategoryDTO.getId())
            .map(
                existingResourceCategory -> {
                    resourceCategoryMapper.partialUpdate(existingResourceCategory, resourceCategoryDTO);
                    return existingResourceCategory;
                }
            )
            .map(resourceCategoryRepository::save)
            .map(resourceCategoryMapper::toDto);
    }

    /**
     * Get all the resourceCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ResourceCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResourceCategories");
        return resourceCategoryRepository.findAll(pageable).map(resourceCategoryMapper::toDto);
    }

    /**
     * Get all the resourceCategories for parent is null.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ResourceCategoryDTO> findAllTop(Pageable pageable) {
        log.debug("Request to get all ResourceCategories for parent is null");
        return resourceCategoryRepository.findAllByParentIsNull(pageable).map(resourceCategoryMapper::toDto);
    }

    /**
     * Get all the resourceCategories for parent is parentId.
     *
     * @param parentId the Id of parent
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ResourceCategoryDTO> findChildrenByParentId(Long parentId) {
        log.debug("Request to get all ResourceCategories for parent is parentId");
        return resourceCategoryRepository.findAllByParentId(parentId, Pageable.unpaged()).map(resourceCategoryMapper::toDto).getContent();
    }

    /**
     * count all the resourceCategories.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all ResourceCategories");
        return resourceCategoryRepository.count();
    }

    /**
     * Get one resourceCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceCategoryDTO> findOne(Long id) {
        log.debug("Request to get ResourceCategory : {}", id);
        return resourceCategoryRepository.findById(id).map(resourceCategoryMapper::toDto);
    }

    /**
     * Get one resourceCategory by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceCategoryDTO> findOneByExample(Example<ResourceCategory> example) {
        log.debug("Request to get ResourceCategory by example");
        return resourceCategoryRepository.findOne(example).map(resourceCategoryMapper::toDto);
    }

    /**
     * Get all the resourceCategories by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<ResourceCategoryDTO> findAllByExample(Example<ResourceCategory> example, Pageable pageable) {
        log.debug("Request to get ResourceCategory by example");
        return resourceCategoryRepository.findAll(example, pageable).map(resourceCategoryMapper::toDto);
    }

    /**
     * Delete the resourceCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceCategory : {}", id);
        ResourceCategory resourceCategory = resourceCategoryRepository.getOne(id);
        if (resourceCategory.getParent() != null) {
            resourceCategory.getParent().removeChildren(resourceCategory);
        }
        if (resourceCategory.getChildren() != null) {
            resourceCategory
                .getChildren()
                .forEach(
                    subResourceCategory -> {
                        subResourceCategory.setParent(null);
                    }
                );
        }
        resourceCategoryRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by resourceCategory
     */
    public ResourceCategoryDTO updateByIgnoreSpecifiedFields(ResourceCategoryDTO changeResourceCategoryDTO, Set<String> unchangedFields) {
        ResourceCategoryDTO resourceCategoryDTO = findOne(changeResourceCategoryDTO.getId()).get();
        BeanUtil.copyProperties(changeResourceCategoryDTO, resourceCategoryDTO, unchangedFields.toArray(new String[0]));
        resourceCategoryDTO = save(resourceCategoryDTO);
        return resourceCategoryDTO;
    }

    /**
     * Update specified fields by resourceCategory
     */
    public ResourceCategoryDTO updateBySpecifiedFields(ResourceCategoryDTO changeResourceCategoryDTO, Set<String> fieldNames) {
        ResourceCategoryDTO resourceCategoryDTO = findOne(changeResourceCategoryDTO.getId()).get();
        ResourceCategoryDTO finalResourceCategoryDTO = resourceCategoryDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalResourceCategoryDTO, fieldName, BeanUtil.getFieldValue(changeResourceCategoryDTO, fieldName));
            }
        );
        resourceCategoryDTO = save(finalResourceCategoryDTO);
        return resourceCategoryDTO;
    }

    /**
     * Update specified field by resourceCategory
     */
    public ResourceCategoryDTO updateBySpecifiedField(ResourceCategoryDTO changeResourceCategoryDTO, String fieldName) {
        ResourceCategoryDTO resourceCategoryDTO = findOne(changeResourceCategoryDTO.getId()).get();
        BeanUtil.setFieldValue(resourceCategoryDTO, fieldName, BeanUtil.getFieldValue(changeResourceCategoryDTO, fieldName));
        resourceCategoryDTO = save(resourceCategoryDTO);
        return resourceCategoryDTO;
    }

    // 清除children缓存
    private void clearChildrenCache() {
        Objects.requireNonNull(cacheManager.getCache(com.blog.myapp.domain.ResourceCategory.class.getName() + ".children")).clear();
    }

    private void clearRelationsCache() {
        this.relationCacheNames.forEach(
                cacheName -> {
                    if (cacheManager.getCache(cacheName) != null) {
                        cacheManager.getCache(cacheName).clear();
                    }
                }
            );
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
