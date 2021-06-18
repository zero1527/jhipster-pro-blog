package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.OssConfig;
import com.blog.myapp.repository.OssConfigRepository;
import com.blog.myapp.service.dto.OssConfigDTO;
import com.blog.myapp.service.mapper.OssConfigMapper;
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
 * Service Implementation for managing {@link OssConfig}.
 */
@Service
@Transactional
public class OssConfigService {

    private final Logger log = LoggerFactory.getLogger(OssConfigService.class);

    private final OssConfigRepository ossConfigRepository;

    private final CacheManager cacheManager;

    private final OssConfigMapper ossConfigMapper;

    public OssConfigService(OssConfigRepository ossConfigRepository, CacheManager cacheManager, OssConfigMapper ossConfigMapper) {
        this.ossConfigRepository = ossConfigRepository;
        this.cacheManager = cacheManager;
        this.ossConfigMapper = ossConfigMapper;
    }

    /**
     * Save a ossConfig.
     *
     * @param ossConfigDTO the entity to save.
     * @return the persisted entity.
     */
    public OssConfigDTO save(OssConfigDTO ossConfigDTO) {
        log.debug("Request to save OssConfig : {}", ossConfigDTO);
        OssConfig ossConfig = ossConfigMapper.toEntity(ossConfigDTO);
        ossConfig = ossConfigRepository.save(ossConfig);
        return ossConfigMapper.toDto(ossConfig);
    }

    /**
     * Partially update a ossConfig.
     *
     * @param ossConfigDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OssConfigDTO> partialUpdate(OssConfigDTO ossConfigDTO) {
        log.debug("Request to partially update OssConfig : {}", ossConfigDTO);

        return ossConfigRepository
            .findById(ossConfigDTO.getId())
            .map(
                existingOssConfig -> {
                    ossConfigMapper.partialUpdate(existingOssConfig, ossConfigDTO);
                    return existingOssConfig;
                }
            )
            .map(ossConfigRepository::save)
            .map(ossConfigMapper::toDto);
    }

    /**
     * Get all the ossConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OssConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OssConfigs");
        return ossConfigRepository.findAll(pageable).map(ossConfigMapper::toDto);
    }

    /**
     * count all the ossConfigs.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all OssConfigs");
        return ossConfigRepository.count();
    }

    /**
     * Get one ossConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OssConfigDTO> findOne(Long id) {
        log.debug("Request to get OssConfig : {}", id);
        return ossConfigRepository.findById(id).map(ossConfigMapper::toDto);
    }

    /**
     * Get one ossConfig by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OssConfigDTO> findOneByExample(Example<OssConfig> example) {
        log.debug("Request to get OssConfig by example");
        return ossConfigRepository.findOne(example).map(ossConfigMapper::toDto);
    }

    /**
     * Get all the ossConfigs by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<OssConfigDTO> findAllByExample(Example<OssConfig> example, Pageable pageable) {
        log.debug("Request to get OssConfig by example");
        return ossConfigRepository.findAll(example, pageable).map(ossConfigMapper::toDto);
    }

    /**
     * Delete the ossConfig by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OssConfig : {}", id);
        ossConfigRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by ossConfig
     */
    public OssConfigDTO updateByIgnoreSpecifiedFields(OssConfigDTO changeOssConfigDTO, Set<String> unchangedFields) {
        OssConfigDTO ossConfigDTO = findOne(changeOssConfigDTO.getId()).get();
        BeanUtil.copyProperties(changeOssConfigDTO, ossConfigDTO, unchangedFields.toArray(new String[0]));
        ossConfigDTO = save(ossConfigDTO);
        return ossConfigDTO;
    }

    /**
     * Update specified fields by ossConfig
     */
    public OssConfigDTO updateBySpecifiedFields(OssConfigDTO changeOssConfigDTO, Set<String> fieldNames) {
        OssConfigDTO ossConfigDTO = findOne(changeOssConfigDTO.getId()).get();
        OssConfigDTO finalOssConfigDTO = ossConfigDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalOssConfigDTO, fieldName, BeanUtil.getFieldValue(changeOssConfigDTO, fieldName));
            }
        );
        ossConfigDTO = save(finalOssConfigDTO);
        return ossConfigDTO;
    }

    /**
     * Update specified field by ossConfig
     */
    public OssConfigDTO updateBySpecifiedField(OssConfigDTO changeOssConfigDTO, String fieldName) {
        OssConfigDTO ossConfigDTO = findOne(changeOssConfigDTO.getId()).get();
        BeanUtil.setFieldValue(ossConfigDTO, fieldName, BeanUtil.getFieldValue(changeOssConfigDTO, fieldName));
        ossConfigDTO = save(ossConfigDTO);
        return ossConfigDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
