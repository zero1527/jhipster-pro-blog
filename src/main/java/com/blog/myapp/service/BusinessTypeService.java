package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.BusinessType;
import com.blog.myapp.repository.BusinessTypeRepository;
import com.blog.myapp.service.dto.BusinessTypeDTO;
import com.blog.myapp.service.mapper.BusinessTypeMapper;
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
 * Service Implementation for managing {@link BusinessType}.
 */
@Service
@Transactional
public class BusinessTypeService {

    private final Logger log = LoggerFactory.getLogger(BusinessTypeService.class);

    private final BusinessTypeRepository businessTypeRepository;

    private final CacheManager cacheManager;

    private final BusinessTypeMapper businessTypeMapper;

    public BusinessTypeService(
        BusinessTypeRepository businessTypeRepository,
        CacheManager cacheManager,
        BusinessTypeMapper businessTypeMapper
    ) {
        this.businessTypeRepository = businessTypeRepository;
        this.cacheManager = cacheManager;
        this.businessTypeMapper = businessTypeMapper;
    }

    /**
     * Save a businessType.
     *
     * @param businessTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public BusinessTypeDTO save(BusinessTypeDTO businessTypeDTO) {
        log.debug("Request to save BusinessType : {}", businessTypeDTO);
        BusinessType businessType = businessTypeMapper.toEntity(businessTypeDTO);
        businessType = businessTypeRepository.save(businessType);
        return businessTypeMapper.toDto(businessType);
    }

    /**
     * Partially update a businessType.
     *
     * @param businessTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BusinessTypeDTO> partialUpdate(BusinessTypeDTO businessTypeDTO) {
        log.debug("Request to partially update BusinessType : {}", businessTypeDTO);

        return businessTypeRepository
            .findById(businessTypeDTO.getId())
            .map(
                existingBusinessType -> {
                    businessTypeMapper.partialUpdate(existingBusinessType, businessTypeDTO);
                    return existingBusinessType;
                }
            )
            .map(businessTypeRepository::save)
            .map(businessTypeMapper::toDto);
    }

    /**
     * Get all the businessTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BusinessTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BusinessTypes");
        return businessTypeRepository.findAll(pageable).map(businessTypeMapper::toDto);
    }

    /**
     * count all the businessTypes.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all BusinessTypes");
        return businessTypeRepository.count();
    }

    /**
     * Get one businessType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BusinessTypeDTO> findOne(Long id) {
        log.debug("Request to get BusinessType : {}", id);
        return businessTypeRepository.findById(id).map(businessTypeMapper::toDto);
    }

    /**
     * Get one businessType by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BusinessTypeDTO> findOneByExample(Example<BusinessType> example) {
        log.debug("Request to get BusinessType by example");
        return businessTypeRepository.findOne(example).map(businessTypeMapper::toDto);
    }

    /**
     * Get all the businessTypes by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<BusinessTypeDTO> findAllByExample(Example<BusinessType> example, Pageable pageable) {
        log.debug("Request to get BusinessType by example");
        return businessTypeRepository.findAll(example, pageable).map(businessTypeMapper::toDto);
    }

    /**
     * Delete the businessType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BusinessType : {}", id);
        businessTypeRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by businessType
     */
    public BusinessTypeDTO updateByIgnoreSpecifiedFields(BusinessTypeDTO changeBusinessTypeDTO, Set<String> unchangedFields) {
        BusinessTypeDTO businessTypeDTO = findOne(changeBusinessTypeDTO.getId()).get();
        BeanUtil.copyProperties(changeBusinessTypeDTO, businessTypeDTO, unchangedFields.toArray(new String[0]));
        businessTypeDTO = save(businessTypeDTO);
        return businessTypeDTO;
    }

    /**
     * Update specified fields by businessType
     */
    public BusinessTypeDTO updateBySpecifiedFields(BusinessTypeDTO changeBusinessTypeDTO, Set<String> fieldNames) {
        BusinessTypeDTO businessTypeDTO = findOne(changeBusinessTypeDTO.getId()).get();
        BusinessTypeDTO finalBusinessTypeDTO = businessTypeDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalBusinessTypeDTO, fieldName, BeanUtil.getFieldValue(changeBusinessTypeDTO, fieldName));
            }
        );
        businessTypeDTO = save(finalBusinessTypeDTO);
        return businessTypeDTO;
    }

    /**
     * Update specified field by businessType
     */
    public BusinessTypeDTO updateBySpecifiedField(BusinessTypeDTO changeBusinessTypeDTO, String fieldName) {
        BusinessTypeDTO businessTypeDTO = findOne(changeBusinessTypeDTO.getId()).get();
        BeanUtil.setFieldValue(businessTypeDTO, fieldName, BeanUtil.getFieldValue(changeBusinessTypeDTO, fieldName));
        businessTypeDTO = save(businessTypeDTO);
        return businessTypeDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
