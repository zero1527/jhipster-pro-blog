package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonCondition;
import com.blog.myapp.repository.CommonConditionRepository;
import com.blog.myapp.service.dto.CommonConditionDTO;
import com.blog.myapp.service.mapper.CommonConditionMapper;
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
 * Service Implementation for managing {@link CommonCondition}.
 */
@Service
@Transactional
public class CommonConditionService {

    private final Logger log = LoggerFactory.getLogger(CommonConditionService.class);
    private final List<String> relationCacheNames = Arrays.asList(
        com.blog.myapp.domain.CommonConditionItem.class.getName() + ".commonCondition",
        com.blog.myapp.domain.CommonTable.class.getName() + ".commonCondition"
    );

    private final CommonConditionRepository commonConditionRepository;

    private final CacheManager cacheManager;

    private final CommonConditionMapper commonConditionMapper;

    public CommonConditionService(
        CommonConditionRepository commonConditionRepository,
        CacheManager cacheManager,
        CommonConditionMapper commonConditionMapper
    ) {
        this.commonConditionRepository = commonConditionRepository;
        this.cacheManager = cacheManager;
        this.commonConditionMapper = commonConditionMapper;
    }

    /**
     * Save a commonCondition.
     *
     * @param commonConditionDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonConditionDTO save(CommonConditionDTO commonConditionDTO) {
        log.debug("Request to save CommonCondition : {}", commonConditionDTO);
        CommonCondition commonCondition = commonConditionMapper.toEntity(commonConditionDTO);
        commonCondition = commonConditionRepository.save(commonCondition);
        return commonConditionMapper.toDto(commonCondition);
    }

    /**
     * Partially update a commonCondition.
     *
     * @param commonConditionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonConditionDTO> partialUpdate(CommonConditionDTO commonConditionDTO) {
        log.debug("Request to partially update CommonCondition : {}", commonConditionDTO);

        return commonConditionRepository
            .findById(commonConditionDTO.getId())
            .map(
                existingCommonCondition -> {
                    commonConditionMapper.partialUpdate(existingCommonCondition, commonConditionDTO);
                    return existingCommonCondition;
                }
            )
            .map(commonConditionRepository::save)
            .map(commonConditionMapper::toDto);
    }

    /**
     * Get all the commonConditions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonConditionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonConditions");
        return commonConditionRepository.findAll(pageable).map(commonConditionMapper::toDto);
    }

    /**
     * count all the commonConditions.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonConditions");
        return commonConditionRepository.count();
    }

    /**
     * Get one commonCondition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonConditionDTO> findOne(Long id) {
        log.debug("Request to get CommonCondition : {}", id);
        return commonConditionRepository.findById(id).map(commonConditionMapper::toDto);
    }

    /**
     * Get one commonCondition by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonConditionDTO> findOneByExample(Example<CommonCondition> example) {
        log.debug("Request to get CommonCondition by example");
        return commonConditionRepository.findOne(example).map(commonConditionMapper::toDto);
    }

    /**
     * Get all the commonConditions by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonConditionDTO> findAllByExample(Example<CommonCondition> example, Pageable pageable) {
        log.debug("Request to get CommonCondition by example");
        return commonConditionRepository.findAll(example, pageable).map(commonConditionMapper::toDto);
    }

    /**
     * Delete the commonCondition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonCondition : {}", id);
        commonConditionRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonCondition
     */
    public CommonConditionDTO updateByIgnoreSpecifiedFields(CommonConditionDTO changeCommonConditionDTO, Set<String> unchangedFields) {
        CommonConditionDTO commonConditionDTO = findOne(changeCommonConditionDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonConditionDTO, commonConditionDTO, unchangedFields.toArray(new String[0]));
        commonConditionDTO = save(commonConditionDTO);
        return commonConditionDTO;
    }

    /**
     * Update specified fields by commonCondition
     */
    public CommonConditionDTO updateBySpecifiedFields(CommonConditionDTO changeCommonConditionDTO, Set<String> fieldNames) {
        CommonConditionDTO commonConditionDTO = findOne(changeCommonConditionDTO.getId()).get();
        CommonConditionDTO finalCommonConditionDTO = commonConditionDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalCommonConditionDTO, fieldName, BeanUtil.getFieldValue(changeCommonConditionDTO, fieldName));
            }
        );
        commonConditionDTO = save(finalCommonConditionDTO);
        return commonConditionDTO;
    }

    /**
     * Update specified field by commonCondition
     */
    public CommonConditionDTO updateBySpecifiedField(CommonConditionDTO changeCommonConditionDTO, String fieldName) {
        CommonConditionDTO commonConditionDTO = findOne(changeCommonConditionDTO.getId()).get();
        BeanUtil.setFieldValue(commonConditionDTO, fieldName, BeanUtil.getFieldValue(changeCommonConditionDTO, fieldName));
        commonConditionDTO = save(commonConditionDTO);
        return commonConditionDTO;
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
