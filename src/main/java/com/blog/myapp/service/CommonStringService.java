package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonString;
import com.blog.myapp.repository.CommonStringRepository;
import com.blog.myapp.service.dto.CommonStringDTO;
import com.blog.myapp.service.mapper.CommonStringMapper;
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
 * Service Implementation for managing {@link CommonString}.
 */
@Service
@Transactional
public class CommonStringService {

    private final Logger log = LoggerFactory.getLogger(CommonStringService.class);

    private final CommonStringRepository commonStringRepository;

    private final CacheManager cacheManager;

    private final CommonStringMapper commonStringMapper;

    public CommonStringService(
        CommonStringRepository commonStringRepository,
        CacheManager cacheManager,
        CommonStringMapper commonStringMapper
    ) {
        this.commonStringRepository = commonStringRepository;
        this.cacheManager = cacheManager;
        this.commonStringMapper = commonStringMapper;
    }

    /**
     * Save a commonString.
     *
     * @param commonStringDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonStringDTO save(CommonStringDTO commonStringDTO) {
        log.debug("Request to save CommonString : {}", commonStringDTO);
        CommonString commonString = commonStringMapper.toEntity(commonStringDTO);
        commonString = commonStringRepository.save(commonString);
        return commonStringMapper.toDto(commonString);
    }

    /**
     * Partially update a commonString.
     *
     * @param commonStringDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonStringDTO> partialUpdate(CommonStringDTO commonStringDTO) {
        log.debug("Request to partially update CommonString : {}", commonStringDTO);

        return commonStringRepository
            .findById(commonStringDTO.getId())
            .map(
                existingCommonString -> {
                    commonStringMapper.partialUpdate(existingCommonString, commonStringDTO);
                    return existingCommonString;
                }
            )
            .map(commonStringRepository::save)
            .map(commonStringMapper::toDto);
    }

    /**
     * Get all the commonStrings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonStringDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonStrings");
        return commonStringRepository.findAll(pageable).map(commonStringMapper::toDto);
    }

    /**
     * count all the commonStrings.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonStrings");
        return commonStringRepository.count();
    }

    /**
     * Get one commonString by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonStringDTO> findOne(Long id) {
        log.debug("Request to get CommonString : {}", id);
        return commonStringRepository.findById(id).map(commonStringMapper::toDto);
    }

    /**
     * Get one commonString by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonStringDTO> findOneByExample(Example<CommonString> example) {
        log.debug("Request to get CommonString by example");
        return commonStringRepository.findOne(example).map(commonStringMapper::toDto);
    }

    /**
     * Get all the commonStrings by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonStringDTO> findAllByExample(Example<CommonString> example, Pageable pageable) {
        log.debug("Request to get CommonString by example");
        return commonStringRepository.findAll(example, pageable).map(commonStringMapper::toDto);
    }

    /**
     * Delete the commonString by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonString : {}", id);
        commonStringRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonString
     */
    public CommonStringDTO updateByIgnoreSpecifiedFields(CommonStringDTO changeCommonStringDTO, Set<String> unchangedFields) {
        CommonStringDTO commonStringDTO = findOne(changeCommonStringDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonStringDTO, commonStringDTO, unchangedFields.toArray(new String[0]));
        commonStringDTO = save(commonStringDTO);
        return commonStringDTO;
    }

    /**
     * Update specified fields by commonString
     */
    public CommonStringDTO updateBySpecifiedFields(CommonStringDTO changeCommonStringDTO, Set<String> fieldNames) {
        CommonStringDTO commonStringDTO = findOne(changeCommonStringDTO.getId()).get();
        CommonStringDTO finalCommonStringDTO = commonStringDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalCommonStringDTO, fieldName, BeanUtil.getFieldValue(changeCommonStringDTO, fieldName));
            }
        );
        commonStringDTO = save(finalCommonStringDTO);
        return commonStringDTO;
    }

    /**
     * Update specified field by commonString
     */
    public CommonStringDTO updateBySpecifiedField(CommonStringDTO changeCommonStringDTO, String fieldName) {
        CommonStringDTO commonStringDTO = findOne(changeCommonStringDTO.getId()).get();
        BeanUtil.setFieldValue(commonStringDTO, fieldName, BeanUtil.getFieldValue(changeCommonStringDTO, fieldName));
        commonStringDTO = save(commonStringDTO);
        return commonStringDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
