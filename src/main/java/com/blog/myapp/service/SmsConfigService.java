package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.SmsConfig;
import com.blog.myapp.repository.SmsConfigRepository;
import com.blog.myapp.service.dto.SmsConfigDTO;
import com.blog.myapp.service.mapper.SmsConfigMapper;
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
 * Service Implementation for managing {@link SmsConfig}.
 */
@Service
@Transactional
public class SmsConfigService {

    private final Logger log = LoggerFactory.getLogger(SmsConfigService.class);

    private final SmsConfigRepository smsConfigRepository;

    private final CacheManager cacheManager;

    private final SmsConfigMapper smsConfigMapper;

    public SmsConfigService(SmsConfigRepository smsConfigRepository, CacheManager cacheManager, SmsConfigMapper smsConfigMapper) {
        this.smsConfigRepository = smsConfigRepository;
        this.cacheManager = cacheManager;
        this.smsConfigMapper = smsConfigMapper;
    }

    /**
     * Save a smsConfig.
     *
     * @param smsConfigDTO the entity to save.
     * @return the persisted entity.
     */
    public SmsConfigDTO save(SmsConfigDTO smsConfigDTO) {
        log.debug("Request to save SmsConfig : {}", smsConfigDTO);
        SmsConfig smsConfig = smsConfigMapper.toEntity(smsConfigDTO);
        smsConfig = smsConfigRepository.save(smsConfig);
        return smsConfigMapper.toDto(smsConfig);
    }

    /**
     * Partially update a smsConfig.
     *
     * @param smsConfigDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SmsConfigDTO> partialUpdate(SmsConfigDTO smsConfigDTO) {
        log.debug("Request to partially update SmsConfig : {}", smsConfigDTO);

        return smsConfigRepository
            .findById(smsConfigDTO.getId())
            .map(
                existingSmsConfig -> {
                    smsConfigMapper.partialUpdate(existingSmsConfig, smsConfigDTO);
                    return existingSmsConfig;
                }
            )
            .map(smsConfigRepository::save)
            .map(smsConfigMapper::toDto);
    }

    /**
     * Get all the smsConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SmsConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SmsConfigs");
        return smsConfigRepository.findAll(pageable).map(smsConfigMapper::toDto);
    }

    /**
     * count all the smsConfigs.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all SmsConfigs");
        return smsConfigRepository.count();
    }

    /**
     * Get one smsConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SmsConfigDTO> findOne(Long id) {
        log.debug("Request to get SmsConfig : {}", id);
        return smsConfigRepository.findById(id).map(smsConfigMapper::toDto);
    }

    /**
     * Get one smsConfig by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SmsConfigDTO> findOneByExample(Example<SmsConfig> example) {
        log.debug("Request to get SmsConfig by example");
        return smsConfigRepository.findOne(example).map(smsConfigMapper::toDto);
    }

    /**
     * Get all the smsConfigs by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<SmsConfigDTO> findAllByExample(Example<SmsConfig> example, Pageable pageable) {
        log.debug("Request to get SmsConfig by example");
        return smsConfigRepository.findAll(example, pageable).map(smsConfigMapper::toDto);
    }

    /**
     * Delete the smsConfig by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SmsConfig : {}", id);
        smsConfigRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by smsConfig
     */
    public SmsConfigDTO updateByIgnoreSpecifiedFields(SmsConfigDTO changeSmsConfigDTO, Set<String> unchangedFields) {
        SmsConfigDTO smsConfigDTO = findOne(changeSmsConfigDTO.getId()).get();
        BeanUtil.copyProperties(changeSmsConfigDTO, smsConfigDTO, unchangedFields.toArray(new String[0]));
        smsConfigDTO = save(smsConfigDTO);
        return smsConfigDTO;
    }

    /**
     * Update specified fields by smsConfig
     */
    public SmsConfigDTO updateBySpecifiedFields(SmsConfigDTO changeSmsConfigDTO, Set<String> fieldNames) {
        SmsConfigDTO smsConfigDTO = findOne(changeSmsConfigDTO.getId()).get();
        SmsConfigDTO finalSmsConfigDTO = smsConfigDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalSmsConfigDTO, fieldName, BeanUtil.getFieldValue(changeSmsConfigDTO, fieldName));
            }
        );
        smsConfigDTO = save(finalSmsConfigDTO);
        return smsConfigDTO;
    }

    /**
     * Update specified field by smsConfig
     */
    public SmsConfigDTO updateBySpecifiedField(SmsConfigDTO changeSmsConfigDTO, String fieldName) {
        SmsConfigDTO smsConfigDTO = findOne(changeSmsConfigDTO.getId()).get();
        BeanUtil.setFieldValue(smsConfigDTO, fieldName, BeanUtil.getFieldValue(changeSmsConfigDTO, fieldName));
        smsConfigDTO = save(smsConfigDTO);
        return smsConfigDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
