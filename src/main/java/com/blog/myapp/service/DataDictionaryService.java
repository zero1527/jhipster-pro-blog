package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.DataDictionary;
import com.blog.myapp.repository.DataDictionaryRepository;
import com.blog.myapp.service.dto.DataDictionaryDTO;
import com.blog.myapp.service.mapper.DataDictionaryMapper;
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
 * Service Implementation for managing {@link DataDictionary}.
 */
@Service
@Transactional
public class DataDictionaryService {

    private final Logger log = LoggerFactory.getLogger(DataDictionaryService.class);
    private final List<String> relationCacheNames = Arrays.asList(
        com.blog.myapp.domain.DataDictionary.class.getName() + ".parent",
        com.blog.myapp.domain.DataDictionary.class.getName() + ".children"
    );

    private final DataDictionaryRepository dataDictionaryRepository;

    private final CacheManager cacheManager;

    private final DataDictionaryMapper dataDictionaryMapper;

    public DataDictionaryService(
        DataDictionaryRepository dataDictionaryRepository,
        CacheManager cacheManager,
        DataDictionaryMapper dataDictionaryMapper
    ) {
        this.dataDictionaryRepository = dataDictionaryRepository;
        this.cacheManager = cacheManager;
        this.dataDictionaryMapper = dataDictionaryMapper;
    }

    /**
     * Save a dataDictionary.
     *
     * @param dataDictionaryDTO the entity to save.
     * @return the persisted entity.
     */
    public DataDictionaryDTO save(DataDictionaryDTO dataDictionaryDTO) {
        log.debug("Request to save DataDictionary : {}", dataDictionaryDTO);
        DataDictionary dataDictionary = dataDictionaryMapper.toEntity(dataDictionaryDTO);
        clearChildrenCache();
        dataDictionary = dataDictionaryRepository.save(dataDictionary);
        // 更新缓存
        if (dataDictionary.getParent() != null) {
            dataDictionary.getParent().addChildren(dataDictionary);
        }
        return dataDictionaryMapper.toDto(dataDictionary);
    }

    /**
     * Partially update a dataDictionary.
     *
     * @param dataDictionaryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DataDictionaryDTO> partialUpdate(DataDictionaryDTO dataDictionaryDTO) {
        log.debug("Request to partially update DataDictionary : {}", dataDictionaryDTO);

        return dataDictionaryRepository
            .findById(dataDictionaryDTO.getId())
            .map(
                existingDataDictionary -> {
                    dataDictionaryMapper.partialUpdate(existingDataDictionary, dataDictionaryDTO);
                    return existingDataDictionary;
                }
            )
            .map(dataDictionaryRepository::save)
            .map(dataDictionaryMapper::toDto);
    }

    /**
     * Get all the dataDictionaries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DataDictionaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataDictionaries");
        return dataDictionaryRepository.findAll(pageable).map(dataDictionaryMapper::toDto);
    }

    /**
     * Get all the dataDictionaries for parent is null.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DataDictionaryDTO> findAllTop(Pageable pageable) {
        log.debug("Request to get all DataDictionaries for parent is null");
        return dataDictionaryRepository.findAllByParentIsNull(pageable).map(dataDictionaryMapper::toDto);
    }

    /**
     * Get all the dataDictionaries for parent is parentId.
     *
     * @param parentId the Id of parent
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<DataDictionaryDTO> findChildrenByParentId(Long parentId) {
        log.debug("Request to get all DataDictionaries for parent is parentId");
        return dataDictionaryRepository.findAllByParentId(parentId, Pageable.unpaged()).map(dataDictionaryMapper::toDto).getContent();
    }

    /**
     * count all the dataDictionaries.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all DataDictionaries");
        return dataDictionaryRepository.count();
    }

    /**
     * Get one dataDictionary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DataDictionaryDTO> findOne(Long id) {
        log.debug("Request to get DataDictionary : {}", id);
        return dataDictionaryRepository.findById(id).map(dataDictionaryMapper::toDto);
    }

    /**
     * Get one dataDictionary by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DataDictionaryDTO> findOneByExample(Example<DataDictionary> example) {
        log.debug("Request to get DataDictionary by example");
        return dataDictionaryRepository.findOne(example).map(dataDictionaryMapper::toDto);
    }

    /**
     * Get all the dataDictionaries by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<DataDictionaryDTO> findAllByExample(Example<DataDictionary> example, Pageable pageable) {
        log.debug("Request to get DataDictionary by example");
        return dataDictionaryRepository.findAll(example, pageable).map(dataDictionaryMapper::toDto);
    }

    /**
     * Delete the dataDictionary by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DataDictionary : {}", id);
        DataDictionary dataDictionary = dataDictionaryRepository.getOne(id);
        if (dataDictionary.getParent() != null) {
            dataDictionary.getParent().removeChildren(dataDictionary);
        }
        if (dataDictionary.getChildren() != null) {
            dataDictionary
                .getChildren()
                .forEach(
                    subDataDictionary -> {
                        subDataDictionary.setParent(null);
                    }
                );
        }
        dataDictionaryRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by dataDictionary
     */
    public DataDictionaryDTO updateByIgnoreSpecifiedFields(DataDictionaryDTO changeDataDictionaryDTO, Set<String> unchangedFields) {
        DataDictionaryDTO dataDictionaryDTO = findOne(changeDataDictionaryDTO.getId()).get();
        BeanUtil.copyProperties(changeDataDictionaryDTO, dataDictionaryDTO, unchangedFields.toArray(new String[0]));
        dataDictionaryDTO = save(dataDictionaryDTO);
        return dataDictionaryDTO;
    }

    /**
     * Update specified fields by dataDictionary
     */
    public DataDictionaryDTO updateBySpecifiedFields(DataDictionaryDTO changeDataDictionaryDTO, Set<String> fieldNames) {
        DataDictionaryDTO dataDictionaryDTO = findOne(changeDataDictionaryDTO.getId()).get();
        DataDictionaryDTO finalDataDictionaryDTO = dataDictionaryDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalDataDictionaryDTO, fieldName, BeanUtil.getFieldValue(changeDataDictionaryDTO, fieldName));
            }
        );
        dataDictionaryDTO = save(finalDataDictionaryDTO);
        return dataDictionaryDTO;
    }

    /**
     * Update specified field by dataDictionary
     */
    public DataDictionaryDTO updateBySpecifiedField(DataDictionaryDTO changeDataDictionaryDTO, String fieldName) {
        DataDictionaryDTO dataDictionaryDTO = findOne(changeDataDictionaryDTO.getId()).get();
        BeanUtil.setFieldValue(dataDictionaryDTO, fieldName, BeanUtil.getFieldValue(changeDataDictionaryDTO, fieldName));
        dataDictionaryDTO = save(dataDictionaryDTO);
        return dataDictionaryDTO;
    }

    // 清除children缓存
    private void clearChildrenCache() {
        Objects.requireNonNull(cacheManager.getCache(com.blog.myapp.domain.DataDictionary.class.getName() + ".children")).clear();
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
