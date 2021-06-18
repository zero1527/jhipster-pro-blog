package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonLocalDate;
import com.blog.myapp.repository.CommonLocalDateRepository;
import com.blog.myapp.service.dto.CommonLocalDateDTO;
import com.blog.myapp.service.mapper.CommonLocalDateMapper;
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
 * Service Implementation for managing {@link CommonLocalDate}.
 */
@Service
@Transactional
public class CommonLocalDateService {

    private final Logger log = LoggerFactory.getLogger(CommonLocalDateService.class);

    private final CommonLocalDateRepository commonLocalDateRepository;

    private final CacheManager cacheManager;

    private final CommonLocalDateMapper commonLocalDateMapper;

    public CommonLocalDateService(
        CommonLocalDateRepository commonLocalDateRepository,
        CacheManager cacheManager,
        CommonLocalDateMapper commonLocalDateMapper
    ) {
        this.commonLocalDateRepository = commonLocalDateRepository;
        this.cacheManager = cacheManager;
        this.commonLocalDateMapper = commonLocalDateMapper;
    }

    /**
     * Save a commonLocalDate.
     *
     * @param commonLocalDateDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonLocalDateDTO save(CommonLocalDateDTO commonLocalDateDTO) {
        log.debug("Request to save CommonLocalDate : {}", commonLocalDateDTO);
        CommonLocalDate commonLocalDate = commonLocalDateMapper.toEntity(commonLocalDateDTO);
        commonLocalDate = commonLocalDateRepository.save(commonLocalDate);
        return commonLocalDateMapper.toDto(commonLocalDate);
    }

    /**
     * Partially update a commonLocalDate.
     *
     * @param commonLocalDateDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonLocalDateDTO> partialUpdate(CommonLocalDateDTO commonLocalDateDTO) {
        log.debug("Request to partially update CommonLocalDate : {}", commonLocalDateDTO);

        return commonLocalDateRepository
            .findById(commonLocalDateDTO.getId())
            .map(
                existingCommonLocalDate -> {
                    commonLocalDateMapper.partialUpdate(existingCommonLocalDate, commonLocalDateDTO);
                    return existingCommonLocalDate;
                }
            )
            .map(commonLocalDateRepository::save)
            .map(commonLocalDateMapper::toDto);
    }

    /**
     * Get all the commonLocalDates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonLocalDateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonLocalDates");
        return commonLocalDateRepository.findAll(pageable).map(commonLocalDateMapper::toDto);
    }

    /**
     * count all the commonLocalDates.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonLocalDates");
        return commonLocalDateRepository.count();
    }

    /**
     * Get one commonLocalDate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonLocalDateDTO> findOne(Long id) {
        log.debug("Request to get CommonLocalDate : {}", id);
        return commonLocalDateRepository.findById(id).map(commonLocalDateMapper::toDto);
    }

    /**
     * Get one commonLocalDate by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonLocalDateDTO> findOneByExample(Example<CommonLocalDate> example) {
        log.debug("Request to get CommonLocalDate by example");
        return commonLocalDateRepository.findOne(example).map(commonLocalDateMapper::toDto);
    }

    /**
     * Get all the commonLocalDates by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonLocalDateDTO> findAllByExample(Example<CommonLocalDate> example, Pageable pageable) {
        log.debug("Request to get CommonLocalDate by example");
        return commonLocalDateRepository.findAll(example, pageable).map(commonLocalDateMapper::toDto);
    }

    /**
     * Delete the commonLocalDate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonLocalDate : {}", id);
        commonLocalDateRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonLocalDate
     */
    public CommonLocalDateDTO updateByIgnoreSpecifiedFields(CommonLocalDateDTO changeCommonLocalDateDTO, Set<String> unchangedFields) {
        CommonLocalDateDTO commonLocalDateDTO = findOne(changeCommonLocalDateDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonLocalDateDTO, commonLocalDateDTO, unchangedFields.toArray(new String[0]));
        commonLocalDateDTO = save(commonLocalDateDTO);
        return commonLocalDateDTO;
    }

    /**
     * Update specified fields by commonLocalDate
     */
    public CommonLocalDateDTO updateBySpecifiedFields(CommonLocalDateDTO changeCommonLocalDateDTO, Set<String> fieldNames) {
        CommonLocalDateDTO commonLocalDateDTO = findOne(changeCommonLocalDateDTO.getId()).get();
        CommonLocalDateDTO finalCommonLocalDateDTO = commonLocalDateDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalCommonLocalDateDTO, fieldName, BeanUtil.getFieldValue(changeCommonLocalDateDTO, fieldName));
            }
        );
        commonLocalDateDTO = save(finalCommonLocalDateDTO);
        return commonLocalDateDTO;
    }

    /**
     * Update specified field by commonLocalDate
     */
    public CommonLocalDateDTO updateBySpecifiedField(CommonLocalDateDTO changeCommonLocalDateDTO, String fieldName) {
        CommonLocalDateDTO commonLocalDateDTO = findOne(changeCommonLocalDateDTO.getId()).get();
        BeanUtil.setFieldValue(commonLocalDateDTO, fieldName, BeanUtil.getFieldValue(changeCommonLocalDateDTO, fieldName));
        commonLocalDateDTO = save(commonLocalDateDTO);
        return commonLocalDateDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
