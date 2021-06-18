package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.CommonTextBlob;
import com.blog.myapp.repository.CommonTextBlobRepository;
import com.blog.myapp.service.dto.CommonTextBlobDTO;
import com.blog.myapp.service.mapper.CommonTextBlobMapper;
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
 * Service Implementation for managing {@link CommonTextBlob}.
 */
@Service
@Transactional
public class CommonTextBlobService {

    private final Logger log = LoggerFactory.getLogger(CommonTextBlobService.class);

    private final CommonTextBlobRepository commonTextBlobRepository;

    private final CacheManager cacheManager;

    private final CommonTextBlobMapper commonTextBlobMapper;

    public CommonTextBlobService(
        CommonTextBlobRepository commonTextBlobRepository,
        CacheManager cacheManager,
        CommonTextBlobMapper commonTextBlobMapper
    ) {
        this.commonTextBlobRepository = commonTextBlobRepository;
        this.cacheManager = cacheManager;
        this.commonTextBlobMapper = commonTextBlobMapper;
    }

    /**
     * Save a commonTextBlob.
     *
     * @param commonTextBlobDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonTextBlobDTO save(CommonTextBlobDTO commonTextBlobDTO) {
        log.debug("Request to save CommonTextBlob : {}", commonTextBlobDTO);
        CommonTextBlob commonTextBlob = commonTextBlobMapper.toEntity(commonTextBlobDTO);
        commonTextBlob = commonTextBlobRepository.save(commonTextBlob);
        return commonTextBlobMapper.toDto(commonTextBlob);
    }

    /**
     * Partially update a commonTextBlob.
     *
     * @param commonTextBlobDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonTextBlobDTO> partialUpdate(CommonTextBlobDTO commonTextBlobDTO) {
        log.debug("Request to partially update CommonTextBlob : {}", commonTextBlobDTO);

        return commonTextBlobRepository
            .findById(commonTextBlobDTO.getId())
            .map(
                existingCommonTextBlob -> {
                    commonTextBlobMapper.partialUpdate(existingCommonTextBlob, commonTextBlobDTO);
                    return existingCommonTextBlob;
                }
            )
            .map(commonTextBlobRepository::save)
            .map(commonTextBlobMapper::toDto);
    }

    /**
     * Get all the commonTextBlobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonTextBlobDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonTextBlobs");
        return commonTextBlobRepository.findAll(pageable).map(commonTextBlobMapper::toDto);
    }

    /**
     * count all the commonTextBlobs.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all CommonTextBlobs");
        return commonTextBlobRepository.count();
    }

    /**
     * Get one commonTextBlob by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonTextBlobDTO> findOne(Long id) {
        log.debug("Request to get CommonTextBlob : {}", id);
        return commonTextBlobRepository.findById(id).map(commonTextBlobMapper::toDto);
    }

    /**
     * Get one commonTextBlob by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonTextBlobDTO> findOneByExample(Example<CommonTextBlob> example) {
        log.debug("Request to get CommonTextBlob by example");
        return commonTextBlobRepository.findOne(example).map(commonTextBlobMapper::toDto);
    }

    /**
     * Get all the commonTextBlobs by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<CommonTextBlobDTO> findAllByExample(Example<CommonTextBlob> example, Pageable pageable) {
        log.debug("Request to get CommonTextBlob by example");
        return commonTextBlobRepository.findAll(example, pageable).map(commonTextBlobMapper::toDto);
    }

    /**
     * Delete the commonTextBlob by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonTextBlob : {}", id);
        commonTextBlobRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonTextBlob
     */
    public CommonTextBlobDTO updateByIgnoreSpecifiedFields(CommonTextBlobDTO changeCommonTextBlobDTO, Set<String> unchangedFields) {
        CommonTextBlobDTO commonTextBlobDTO = findOne(changeCommonTextBlobDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonTextBlobDTO, commonTextBlobDTO, unchangedFields.toArray(new String[0]));
        commonTextBlobDTO = save(commonTextBlobDTO);
        return commonTextBlobDTO;
    }

    /**
     * Update specified fields by commonTextBlob
     */
    public CommonTextBlobDTO updateBySpecifiedFields(CommonTextBlobDTO changeCommonTextBlobDTO, Set<String> fieldNames) {
        CommonTextBlobDTO commonTextBlobDTO = findOne(changeCommonTextBlobDTO.getId()).get();
        CommonTextBlobDTO finalCommonTextBlobDTO = commonTextBlobDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalCommonTextBlobDTO, fieldName, BeanUtil.getFieldValue(changeCommonTextBlobDTO, fieldName));
            }
        );
        commonTextBlobDTO = save(finalCommonTextBlobDTO);
        return commonTextBlobDTO;
    }

    /**
     * Update specified field by commonTextBlob
     */
    public CommonTextBlobDTO updateBySpecifiedField(CommonTextBlobDTO changeCommonTextBlobDTO, String fieldName) {
        CommonTextBlobDTO commonTextBlobDTO = findOne(changeCommonTextBlobDTO.getId()).get();
        BeanUtil.setFieldValue(commonTextBlobDTO, fieldName, BeanUtil.getFieldValue(changeCommonTextBlobDTO, fieldName));
        commonTextBlobDTO = save(commonTextBlobDTO);
        return commonTextBlobDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
