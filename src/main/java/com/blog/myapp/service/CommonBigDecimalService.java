package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonBigDecimal;
import com.blog.myapp.repository.CommonBigDecimalRepository;
import com.blog.myapp.service.dto.CommonBigDecimalDTO;
import com.blog.myapp.service.mapper.CommonBigDecimalMapper;
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
 * Service Implementation for managing {@link CommonBigDecimal}.
 */
@Service
@Transactional
public class CommonBigDecimalService {

    private final Logger log = LoggerFactory.getLogger(CommonBigDecimalService.class);

    private final CommonBigDecimalRepository commonBigDecimalRepository;

    private final CacheManager cacheManager;

    private final CommonBigDecimalMapper commonBigDecimalMapper;

    public CommonBigDecimalService(
        CommonBigDecimalRepository commonBigDecimalRepository,
        CacheManager cacheManager,
        CommonBigDecimalMapper commonBigDecimalMapper
    ) {
        this.commonBigDecimalRepository = commonBigDecimalRepository;
        this.cacheManager = cacheManager;
        this.commonBigDecimalMapper = commonBigDecimalMapper;
    }

    /**
     * Save a commonBigDecimal.
     *
     * @param commonBigDecimalDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonBigDecimalDTO save(CommonBigDecimalDTO commonBigDecimalDTO) {
        log.debug("Request to save CommonBigDecimal : {}", commonBigDecimalDTO);
        CommonBigDecimal commonBigDecimal = commonBigDecimalMapper.toEntity(commonBigDecimalDTO);
        commonBigDecimal = commonBigDecimalRepository.save(commonBigDecimal);
        return commonBigDecimalMapper.toDto(commonBigDecimal);
    }

    /**
     * Partially update a commonBigDecimal.
     *
     * @param commonBigDecimalDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonBigDecimalDTO> partialUpdate(CommonBigDecimalDTO commonBigDecimalDTO) {
        log.debug("Request to partially update CommonBigDecimal : {}", commonBigDecimalDTO);

        return commonBigDecimalRepository
            .findById(commonBigDecimalDTO.getId())
            .map(
                existingCommonBigDecimal -> {
                    commonBigDecimalMapper.partialUpdate(existingCommonBigDecimal, commonBigDecimalDTO);
                    return existingCommonBigDecimal;
                }
            )
            .map(commonBigDecimalRepository::save)
            .map(commonBigDecimalMapper::toDto);
    }

    /**
     * Get all the commonBigDecimals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonBigDecimalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonBigDecimals");
        return commonBigDecimalRepository.findAll(pageable).map(commonBigDecimalMapper::toDto);
    }

    /**
     * count all the commonBigDecimals.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonBigDecimals");
        return commonBigDecimalRepository.count();
    }

    /**
     * Get one commonBigDecimal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonBigDecimalDTO> findOne(Long id) {
        log.debug("Request to get CommonBigDecimal : {}", id);
        return commonBigDecimalRepository.findById(id).map(commonBigDecimalMapper::toDto);
    }

    /**
     * Get one commonBigDecimal by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonBigDecimalDTO> findOneByExample(Example<CommonBigDecimal> example) {
        log.debug("Request to get CommonBigDecimal by example");
        return commonBigDecimalRepository.findOne(example).map(commonBigDecimalMapper::toDto);
    }

    /**
     * Get all the commonBigDecimals by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonBigDecimalDTO> findAllByExample(Example<CommonBigDecimal> example, Pageable pageable) {
        log.debug("Request to get CommonBigDecimal by example");
        return commonBigDecimalRepository.findAll(example, pageable).map(commonBigDecimalMapper::toDto);
    }

    /**
     * Delete the commonBigDecimal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonBigDecimal : {}", id);
        commonBigDecimalRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonBigDecimal
     */
    public CommonBigDecimalDTO updateByIgnoreSpecifiedFields(CommonBigDecimalDTO changeCommonBigDecimalDTO, Set<String> unchangedFields) {
        CommonBigDecimalDTO commonBigDecimalDTO = findOne(changeCommonBigDecimalDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonBigDecimalDTO, commonBigDecimalDTO, unchangedFields.toArray(new String[0]));
        commonBigDecimalDTO = save(commonBigDecimalDTO);
        return commonBigDecimalDTO;
    }

    /**
     * Update specified fields by commonBigDecimal
     */
    public CommonBigDecimalDTO updateBySpecifiedFields(CommonBigDecimalDTO changeCommonBigDecimalDTO, Set<String> fieldNames) {
        CommonBigDecimalDTO commonBigDecimalDTO = findOne(changeCommonBigDecimalDTO.getId()).get();
        CommonBigDecimalDTO finalCommonBigDecimalDTO = commonBigDecimalDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalCommonBigDecimalDTO, fieldName, BeanUtil.getFieldValue(changeCommonBigDecimalDTO, fieldName));
            }
        );
        commonBigDecimalDTO = save(finalCommonBigDecimalDTO);
        return commonBigDecimalDTO;
    }

    /**
     * Update specified field by commonBigDecimal
     */
    public CommonBigDecimalDTO updateBySpecifiedField(CommonBigDecimalDTO changeCommonBigDecimalDTO, String fieldName) {
        CommonBigDecimalDTO commonBigDecimalDTO = findOne(changeCommonBigDecimalDTO.getId()).get();
        BeanUtil.setFieldValue(commonBigDecimalDTO, fieldName, BeanUtil.getFieldValue(changeCommonBigDecimalDTO, fieldName));
        commonBigDecimalDTO = save(commonBigDecimalDTO);
        return commonBigDecimalDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
