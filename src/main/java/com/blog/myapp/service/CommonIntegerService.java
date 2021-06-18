package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonInteger;
import com.blog.myapp.repository.CommonIntegerRepository;
import com.blog.myapp.service.dto.CommonIntegerDTO;
import com.blog.myapp.service.mapper.CommonIntegerMapper;
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
 * Service Implementation for managing {@link CommonInteger}.
 */
@Service
@Transactional
public class CommonIntegerService {

    private final Logger log = LoggerFactory.getLogger(CommonIntegerService.class);

    private final CommonIntegerRepository commonIntegerRepository;

    private final CacheManager cacheManager;

    private final CommonIntegerMapper commonIntegerMapper;

    public CommonIntegerService(
        CommonIntegerRepository commonIntegerRepository,
        CacheManager cacheManager,
        CommonIntegerMapper commonIntegerMapper
    ) {
        this.commonIntegerRepository = commonIntegerRepository;
        this.cacheManager = cacheManager;
        this.commonIntegerMapper = commonIntegerMapper;
    }

    /**
     * Save a commonInteger.
     *
     * @param commonIntegerDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonIntegerDTO save(CommonIntegerDTO commonIntegerDTO) {
        log.debug("Request to save CommonInteger : {}", commonIntegerDTO);
        CommonInteger commonInteger = commonIntegerMapper.toEntity(commonIntegerDTO);
        commonInteger = commonIntegerRepository.save(commonInteger);
        return commonIntegerMapper.toDto(commonInteger);
    }

    /**
     * Partially update a commonInteger.
     *
     * @param commonIntegerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonIntegerDTO> partialUpdate(CommonIntegerDTO commonIntegerDTO) {
        log.debug("Request to partially update CommonInteger : {}", commonIntegerDTO);

        return commonIntegerRepository
            .findById(commonIntegerDTO.getId())
            .map(
                existingCommonInteger -> {
                    commonIntegerMapper.partialUpdate(existingCommonInteger, commonIntegerDTO);
                    return existingCommonInteger;
                }
            )
            .map(commonIntegerRepository::save)
            .map(commonIntegerMapper::toDto);
    }

    /**
     * Get all the commonIntegers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonIntegerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonIntegers");
        return commonIntegerRepository.findAll(pageable).map(commonIntegerMapper::toDto);
    }

    /**
     * count all the commonIntegers.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonIntegers");
        return commonIntegerRepository.count();
    }

    /**
     * Get one commonInteger by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonIntegerDTO> findOne(Long id) {
        log.debug("Request to get CommonInteger : {}", id);
        return commonIntegerRepository.findById(id).map(commonIntegerMapper::toDto);
    }

    /**
     * Get one commonInteger by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonIntegerDTO> findOneByExample(Example<CommonInteger> example) {
        log.debug("Request to get CommonInteger by example");
        return commonIntegerRepository.findOne(example).map(commonIntegerMapper::toDto);
    }

    /**
     * Get all the commonIntegers by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonIntegerDTO> findAllByExample(Example<CommonInteger> example, Pageable pageable) {
        log.debug("Request to get CommonInteger by example");
        return commonIntegerRepository.findAll(example, pageable).map(commonIntegerMapper::toDto);
    }

    /**
     * Delete the commonInteger by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonInteger : {}", id);
        commonIntegerRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonInteger
     */
    public CommonIntegerDTO updateByIgnoreSpecifiedFields(CommonIntegerDTO changeCommonIntegerDTO, Set<String> unchangedFields) {
        CommonIntegerDTO commonIntegerDTO = findOne(changeCommonIntegerDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonIntegerDTO, commonIntegerDTO, unchangedFields.toArray(new String[0]));
        commonIntegerDTO = save(commonIntegerDTO);
        return commonIntegerDTO;
    }

    /**
     * Update specified fields by commonInteger
     */
    public CommonIntegerDTO updateBySpecifiedFields(CommonIntegerDTO changeCommonIntegerDTO, Set<String> fieldNames) {
        CommonIntegerDTO commonIntegerDTO = findOne(changeCommonIntegerDTO.getId()).get();
        CommonIntegerDTO finalCommonIntegerDTO = commonIntegerDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalCommonIntegerDTO, fieldName, BeanUtil.getFieldValue(changeCommonIntegerDTO, fieldName));
            }
        );
        commonIntegerDTO = save(finalCommonIntegerDTO);
        return commonIntegerDTO;
    }

    /**
     * Update specified field by commonInteger
     */
    public CommonIntegerDTO updateBySpecifiedField(CommonIntegerDTO changeCommonIntegerDTO, String fieldName) {
        CommonIntegerDTO commonIntegerDTO = findOne(changeCommonIntegerDTO.getId()).get();
        BeanUtil.setFieldValue(commonIntegerDTO, fieldName, BeanUtil.getFieldValue(changeCommonIntegerDTO, fieldName));
        commonIntegerDTO = save(commonIntegerDTO);
        return commonIntegerDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
