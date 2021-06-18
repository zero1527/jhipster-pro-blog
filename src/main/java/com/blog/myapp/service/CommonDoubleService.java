package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonDouble;
import com.blog.myapp.repository.CommonDoubleRepository;
import com.blog.myapp.service.dto.CommonDoubleDTO;
import com.blog.myapp.service.mapper.CommonDoubleMapper;
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
 * Service Implementation for managing {@link CommonDouble}.
 */
@Service
@Transactional
public class CommonDoubleService {

    private final Logger log = LoggerFactory.getLogger(CommonDoubleService.class);

    private final CommonDoubleRepository commonDoubleRepository;

    private final CacheManager cacheManager;

    private final CommonDoubleMapper commonDoubleMapper;

    public CommonDoubleService(
        CommonDoubleRepository commonDoubleRepository,
        CacheManager cacheManager,
        CommonDoubleMapper commonDoubleMapper
    ) {
        this.commonDoubleRepository = commonDoubleRepository;
        this.cacheManager = cacheManager;
        this.commonDoubleMapper = commonDoubleMapper;
    }

    /**
     * Save a commonDouble.
     *
     * @param commonDoubleDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonDoubleDTO save(CommonDoubleDTO commonDoubleDTO) {
        log.debug("Request to save CommonDouble : {}", commonDoubleDTO);
        CommonDouble commonDouble = commonDoubleMapper.toEntity(commonDoubleDTO);
        commonDouble = commonDoubleRepository.save(commonDouble);
        return commonDoubleMapper.toDto(commonDouble);
    }

    /**
     * Partially update a commonDouble.
     *
     * @param commonDoubleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonDoubleDTO> partialUpdate(CommonDoubleDTO commonDoubleDTO) {
        log.debug("Request to partially update CommonDouble : {}", commonDoubleDTO);

        return commonDoubleRepository
            .findById(commonDoubleDTO.getId())
            .map(
                existingCommonDouble -> {
                    commonDoubleMapper.partialUpdate(existingCommonDouble, commonDoubleDTO);
                    return existingCommonDouble;
                }
            )
            .map(commonDoubleRepository::save)
            .map(commonDoubleMapper::toDto);
    }

    /**
     * Get all the commonDoubles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonDoubleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonDoubles");
        return commonDoubleRepository.findAll(pageable).map(commonDoubleMapper::toDto);
    }

    /**
     * count all the commonDoubles.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonDoubles");
        return commonDoubleRepository.count();
    }

    /**
     * Get one commonDouble by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonDoubleDTO> findOne(Long id) {
        log.debug("Request to get CommonDouble : {}", id);
        return commonDoubleRepository.findById(id).map(commonDoubleMapper::toDto);
    }

    /**
     * Get one commonDouble by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonDoubleDTO> findOneByExample(Example<CommonDouble> example) {
        log.debug("Request to get CommonDouble by example");
        return commonDoubleRepository.findOne(example).map(commonDoubleMapper::toDto);
    }

    /**
     * Get all the commonDoubles by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonDoubleDTO> findAllByExample(Example<CommonDouble> example, Pageable pageable) {
        log.debug("Request to get CommonDouble by example");
        return commonDoubleRepository.findAll(example, pageable).map(commonDoubleMapper::toDto);
    }

    /**
     * Delete the commonDouble by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonDouble : {}", id);
        commonDoubleRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonDouble
     */
    public CommonDoubleDTO updateByIgnoreSpecifiedFields(CommonDoubleDTO changeCommonDoubleDTO, Set<String> unchangedFields) {
        CommonDoubleDTO commonDoubleDTO = findOne(changeCommonDoubleDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonDoubleDTO, commonDoubleDTO, unchangedFields.toArray(new String[0]));
        commonDoubleDTO = save(commonDoubleDTO);
        return commonDoubleDTO;
    }

    /**
     * Update specified fields by commonDouble
     */
    public CommonDoubleDTO updateBySpecifiedFields(CommonDoubleDTO changeCommonDoubleDTO, Set<String> fieldNames) {
        CommonDoubleDTO commonDoubleDTO = findOne(changeCommonDoubleDTO.getId()).get();
        CommonDoubleDTO finalCommonDoubleDTO = commonDoubleDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalCommonDoubleDTO, fieldName, BeanUtil.getFieldValue(changeCommonDoubleDTO, fieldName));
            }
        );
        commonDoubleDTO = save(finalCommonDoubleDTO);
        return commonDoubleDTO;
    }

    /**
     * Update specified field by commonDouble
     */
    public CommonDoubleDTO updateBySpecifiedField(CommonDoubleDTO changeCommonDoubleDTO, String fieldName) {
        CommonDoubleDTO commonDoubleDTO = findOne(changeCommonDoubleDTO.getId()).get();
        BeanUtil.setFieldValue(commonDoubleDTO, fieldName, BeanUtil.getFieldValue(changeCommonDoubleDTO, fieldName));
        commonDoubleDTO = save(commonDoubleDTO);
        return commonDoubleDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
