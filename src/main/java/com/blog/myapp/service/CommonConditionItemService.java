package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonConditionItem;
import com.blog.myapp.repository.CommonConditionItemRepository;
import com.blog.myapp.service.dto.CommonConditionItemDTO;
import com.blog.myapp.service.mapper.CommonConditionItemMapper;
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
 * Service Implementation for managing {@link CommonConditionItem}.
 */
@Service
@Transactional
public class CommonConditionItemService {

    private final Logger log = LoggerFactory.getLogger(CommonConditionItemService.class);
    private final List<String> relationCacheNames = Arrays.asList(com.blog.myapp.domain.CommonCondition.class.getName() + ".items");

    private final CommonConditionItemRepository commonConditionItemRepository;

    private final CacheManager cacheManager;

    private final CommonConditionItemMapper commonConditionItemMapper;

    public CommonConditionItemService(
        CommonConditionItemRepository commonConditionItemRepository,
        CacheManager cacheManager,
        CommonConditionItemMapper commonConditionItemMapper
    ) {
        this.commonConditionItemRepository = commonConditionItemRepository;
        this.cacheManager = cacheManager;
        this.commonConditionItemMapper = commonConditionItemMapper;
    }

    /**
     * Save a commonConditionItem.
     *
     * @param commonConditionItemDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonConditionItemDTO save(CommonConditionItemDTO commonConditionItemDTO) {
        log.debug("Request to save CommonConditionItem : {}", commonConditionItemDTO);
        CommonConditionItem commonConditionItem = commonConditionItemMapper.toEntity(commonConditionItemDTO);
        commonConditionItem = commonConditionItemRepository.save(commonConditionItem);
        return commonConditionItemMapper.toDto(commonConditionItem);
    }

    /**
     * Partially update a commonConditionItem.
     *
     * @param commonConditionItemDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonConditionItemDTO> partialUpdate(CommonConditionItemDTO commonConditionItemDTO) {
        log.debug("Request to partially update CommonConditionItem : {}", commonConditionItemDTO);

        return commonConditionItemRepository
            .findById(commonConditionItemDTO.getId())
            .map(
                existingCommonConditionItem -> {
                    commonConditionItemMapper.partialUpdate(existingCommonConditionItem, commonConditionItemDTO);
                    return existingCommonConditionItem;
                }
            )
            .map(commonConditionItemRepository::save)
            .map(commonConditionItemMapper::toDto);
    }

    /**
     * Get all the commonConditionItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonConditionItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonConditionItems");
        return commonConditionItemRepository.findAll(pageable).map(commonConditionItemMapper::toDto);
    }

    /**
     * count all the commonConditionItems.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonConditionItems");
        return commonConditionItemRepository.count();
    }

    /**
     * Get one commonConditionItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonConditionItemDTO> findOne(Long id) {
        log.debug("Request to get CommonConditionItem : {}", id);
        return commonConditionItemRepository.findById(id).map(commonConditionItemMapper::toDto);
    }

    /**
     * Get one commonConditionItem by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonConditionItemDTO> findOneByExample(Example<CommonConditionItem> example) {
        log.debug("Request to get CommonConditionItem by example");
        return commonConditionItemRepository.findOne(example).map(commonConditionItemMapper::toDto);
    }

    /**
     * Get all the commonConditionItems by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonConditionItemDTO> findAllByExample(Example<CommonConditionItem> example, Pageable pageable) {
        log.debug("Request to get CommonConditionItem by example");
        return commonConditionItemRepository.findAll(example, pageable).map(commonConditionItemMapper::toDto);
    }

    /**
     * Delete the commonConditionItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonConditionItem : {}", id);
        commonConditionItemRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonConditionItem
     */
    public CommonConditionItemDTO updateByIgnoreSpecifiedFields(
        CommonConditionItemDTO changeCommonConditionItemDTO,
        Set<String> unchangedFields
    ) {
        CommonConditionItemDTO commonConditionItemDTO = findOne(changeCommonConditionItemDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonConditionItemDTO, commonConditionItemDTO, unchangedFields.toArray(new String[0]));
        commonConditionItemDTO = save(commonConditionItemDTO);
        return commonConditionItemDTO;
    }

    /**
     * Update specified fields by commonConditionItem
     */
    public CommonConditionItemDTO updateBySpecifiedFields(CommonConditionItemDTO changeCommonConditionItemDTO, Set<String> fieldNames) {
        CommonConditionItemDTO commonConditionItemDTO = findOne(changeCommonConditionItemDTO.getId()).get();
        CommonConditionItemDTO finalCommonConditionItemDTO = commonConditionItemDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(
                    finalCommonConditionItemDTO,
                    fieldName,
                    BeanUtil.getFieldValue(changeCommonConditionItemDTO, fieldName)
                );
            }
        );
        commonConditionItemDTO = save(finalCommonConditionItemDTO);
        return commonConditionItemDTO;
    }

    /**
     * Update specified field by commonConditionItem
     */
    public CommonConditionItemDTO updateBySpecifiedField(CommonConditionItemDTO changeCommonConditionItemDTO, String fieldName) {
        CommonConditionItemDTO commonConditionItemDTO = findOne(changeCommonConditionItemDTO.getId()).get();
        BeanUtil.setFieldValue(commonConditionItemDTO, fieldName, BeanUtil.getFieldValue(changeCommonConditionItemDTO, fieldName));
        commonConditionItemDTO = save(commonConditionItemDTO);
        return commonConditionItemDTO;
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
