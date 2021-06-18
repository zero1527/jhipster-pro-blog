package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonLong;
import com.blog.myapp.repository.CommonLongRepository;
import com.blog.myapp.service.dto.CommonLongDTO;
import com.blog.myapp.service.mapper.CommonLongMapper;
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
 * Service Implementation for managing {@link CommonLong}.
 */
@Service
@Transactional
public class CommonLongService {

    private final Logger log = LoggerFactory.getLogger(CommonLongService.class);

    private final CommonLongRepository commonLongRepository;

    private final CacheManager cacheManager;

    private final CommonLongMapper commonLongMapper;

    public CommonLongService(CommonLongRepository commonLongRepository, CacheManager cacheManager, CommonLongMapper commonLongMapper) {
        this.commonLongRepository = commonLongRepository;
        this.cacheManager = cacheManager;
        this.commonLongMapper = commonLongMapper;
    }

    /**
     * Save a commonLong.
     *
     * @param commonLongDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonLongDTO save(CommonLongDTO commonLongDTO) {
        log.debug("Request to save CommonLong : {}", commonLongDTO);
        CommonLong commonLong = commonLongMapper.toEntity(commonLongDTO);
        commonLong = commonLongRepository.save(commonLong);
        return commonLongMapper.toDto(commonLong);
    }

    /**
     * Partially update a commonLong.
     *
     * @param commonLongDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonLongDTO> partialUpdate(CommonLongDTO commonLongDTO) {
        log.debug("Request to partially update CommonLong : {}", commonLongDTO);

        return commonLongRepository
            .findById(commonLongDTO.getId())
            .map(
                existingCommonLong -> {
                    commonLongMapper.partialUpdate(existingCommonLong, commonLongDTO);
                    return existingCommonLong;
                }
            )
            .map(commonLongRepository::save)
            .map(commonLongMapper::toDto);
    }

    /**
     * Get all the commonLongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonLongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonLongs");
        return commonLongRepository.findAll(pageable).map(commonLongMapper::toDto);
    }

    /**
     * count all the commonLongs.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonLongs");
        return commonLongRepository.count();
    }

    /**
     * Get one commonLong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonLongDTO> findOne(Long id) {
        log.debug("Request to get CommonLong : {}", id);
        return commonLongRepository.findById(id).map(commonLongMapper::toDto);
    }

    /**
     * Get one commonLong by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonLongDTO> findOneByExample(Example<CommonLong> example) {
        log.debug("Request to get CommonLong by example");
        return commonLongRepository.findOne(example).map(commonLongMapper::toDto);
    }

    /**
     * Get all the commonLongs by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonLongDTO> findAllByExample(Example<CommonLong> example, Pageable pageable) {
        log.debug("Request to get CommonLong by example");
        return commonLongRepository.findAll(example, pageable).map(commonLongMapper::toDto);
    }

    /**
     * Delete the commonLong by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonLong : {}", id);
        commonLongRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonLong
     */
    public CommonLongDTO updateByIgnoreSpecifiedFields(CommonLongDTO changeCommonLongDTO, Set<String> unchangedFields) {
        CommonLongDTO commonLongDTO = findOne(changeCommonLongDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonLongDTO, commonLongDTO, unchangedFields.toArray(new String[0]));
        commonLongDTO = save(commonLongDTO);
        return commonLongDTO;
    }

    /**
     * Update specified fields by commonLong
     */
    public CommonLongDTO updateBySpecifiedFields(CommonLongDTO changeCommonLongDTO, Set<String> fieldNames) {
        CommonLongDTO commonLongDTO = findOne(changeCommonLongDTO.getId()).get();
        CommonLongDTO finalCommonLongDTO = commonLongDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalCommonLongDTO, fieldName, BeanUtil.getFieldValue(changeCommonLongDTO, fieldName));
            }
        );
        commonLongDTO = save(finalCommonLongDTO);
        return commonLongDTO;
    }

    /**
     * Update specified field by commonLong
     */
    public CommonLongDTO updateBySpecifiedField(CommonLongDTO changeCommonLongDTO, String fieldName) {
        CommonLongDTO commonLongDTO = findOne(changeCommonLongDTO.getId()).get();
        BeanUtil.setFieldValue(commonLongDTO, fieldName, BeanUtil.getFieldValue(changeCommonLongDTO, fieldName));
        commonLongDTO = save(commonLongDTO);
        return commonLongDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
