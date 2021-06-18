package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.blog.myapp.domain.CommonTable;
import com.blog.myapp.domain.CommonTableField;
import com.blog.myapp.domain.CommonTableRelationship;
import com.blog.myapp.domain.enumeration.RelationshipType;
import com.blog.myapp.repository.CommonTableRelationshipRepository;
import com.blog.myapp.repository.CommonTableRepository;
import com.blog.myapp.service.dto.CommonTableRelationshipDTO;
import com.blog.myapp.service.mapper.CommonTableRelationshipMapper;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Service Implementation for managing {@link CommonTableRelationship}.
 */
@Service
@Transactional
public class CommonTableRelationshipService {

    private final Logger log = LoggerFactory.getLogger(CommonTableRelationshipService.class);
    private final List<String> relationCacheNames = Arrays.asList(
        com.blog.myapp.domain.CommonTable.class.getName() + ".commonTableRelationship",
        com.blog.myapp.domain.DataDictionary.class.getName() + ".commonTableRelationship",
        com.blog.myapp.domain.CommonTable.class.getName() + ".relationships"
    );

    private final CommonTableRelationshipRepository commonTableRelationshipRepository;

    private final CacheManager cacheManager;

    private final CommonExtDataService commonExtDataService;

    private final CommonTableRepository commonTableRepository;

    private final CommonTableRelationshipMapper commonTableRelationshipMapper;

    public CommonTableRelationshipService(
        CommonTableRelationshipRepository commonTableRelationshipRepository,
        CacheManager cacheManager,
        CommonExtDataService commonExtDataService,
        CommonTableRepository commonTableRepository,
        CommonTableRelationshipMapper commonTableRelationshipMapper
    ) {
        this.commonTableRelationshipRepository = commonTableRelationshipRepository;
        this.cacheManager = cacheManager;
        this.commonExtDataService = commonExtDataService;
        this.commonTableRepository = commonTableRepository;
        this.commonTableRelationshipMapper = commonTableRelationshipMapper;
    }

    /**
     * Save a commonTableRelationship.
     *
     * @param commonTableRelationshipDTO the entity to save.
     * @return the persisted entity.
     */
    public CommonTableRelationshipDTO save(CommonTableRelationshipDTO commonTableRelationshipDTO) {
        log.debug("Request to save CommonTableRelationship : {}", commonTableRelationshipDTO);
        // 获得未修改数据
        Optional<CommonTableRelationshipDTO> old = this.findOne(commonTableRelationshipDTO.getId());
        // 提取扩展数据
        Map<String, Object> extData = commonTableRelationshipDTO.getExtData();
        CommonTableRelationship commonTableRelationship = commonTableRelationshipMapper.toEntity(commonTableRelationshipDTO);
        this.commonTableRelationshipRepository.save(commonTableRelationship);
        this.commonExtDataService.saveExtData(commonTableRelationship);
        return commonTableRelationshipMapper.toDto(commonTableRelationship);
    }

    /**
     * Partially update a commonTableRelationship.
     *
     * @param commonTableRelationshipDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CommonTableRelationshipDTO> partialUpdate(CommonTableRelationshipDTO commonTableRelationshipDTO) {
        log.debug("Request to partially update CommonTableRelationship : {}", commonTableRelationshipDTO);

        return commonTableRelationshipRepository
            .findById(commonTableRelationshipDTO.getId())
            .map(
                existingAdministrativeDivision -> {
                    commonTableRelationshipMapper.partialUpdate(existingAdministrativeDivision, commonTableRelationshipDTO);
                    return existingAdministrativeDivision;
                }
            )
            .map(commonTableRelationshipRepository::save)
            .map(commonTableRelationshipMapper::toDto);
    }

    /**
     * Get all the commonTableRelationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonTableRelationshipDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommonTableRelationships");
        return commonTableRelationshipRepository.findAll(pageable).map(commonTableRelationshipMapper::toDto);
    }

    /**
     *  Get all the commonTableRelationships where ExtData is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CommonTableRelationshipDTO> findAllWhereExtDataIsNull() {
        log.debug("Request to get all commonTableRelationships where ExtData is null");
        return StreamSupport
            .stream(commonTableRelationshipRepository.findAll().spliterator(), false)
            .filter(commonTableRelationship -> commonTableRelationship.getExtData() == null)
            .map(commonTableRelationshipMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one commonTableRelationship by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CommonTableRelationshipDTO> findOne(Long id) {
        log.debug("Request to get CommonTableRelationship : {}", id);
        return commonTableRelationshipRepository
            .findById(id)
            .map(commonTableRelationship -> (CommonTableRelationship) commonExtDataService.getExtData(commonTableRelationship))
            .map(commonTableRelationshipMapper::toDto);
    }

    /**
     * Delete the commonTableRelationship by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CommonTableRelationship : {}", id);
        commonTableRelationshipRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by commonTableRelationship
     */
    public CommonTableRelationshipDTO updateByIgnoreSpecifiedFields(
        CommonTableRelationshipDTO changeCommonTableRelationshipDTO,
        Set<String> unchangedFields
    ) {
        CommonTableRelationshipDTO commonTableRelationshipDTO = findOne(changeCommonTableRelationshipDTO.getId()).get();
        BeanUtil.copyProperties(changeCommonTableRelationshipDTO, commonTableRelationshipDTO, unchangedFields.toArray(new String[0]));
        return save(commonTableRelationshipDTO);
    }

    /**
     * Update specified fields by commonTableRelationship
     */
    public CommonTableRelationshipDTO updateBySpecifiedFields(
        CommonTableRelationshipDTO changeCommonTableRelationshipDTO,
        Set<String> fieldNames
    ) {
        CommonTableRelationshipDTO commonTableRelationshipDTO = findOne(changeCommonTableRelationshipDTO.getId()).get();
        CommonTableRelationshipDTO finalCommonTableDTO = commonTableRelationshipDTO;
        fieldNames.forEach(
            fieldName ->
                BeanUtil.setFieldValue(finalCommonTableDTO, fieldName, BeanUtil.getFieldValue(changeCommonTableRelationshipDTO, fieldName))
        );
        return save(finalCommonTableDTO);
    }

    /**
     * Update specified field by commonTableRelationship
     */
    public CommonTableRelationshipDTO updateBySpecifiedField(
        CommonTableRelationshipDTO changeCommonTableRelationshipDTO,
        String fieldName
    ) {
        CommonTableRelationshipDTO commonTableRelationshipDTO = findOne(changeCommonTableRelationshipDTO.getId()).get();
        BeanUtil.setFieldValue(commonTableRelationshipDTO, fieldName, BeanUtil.getFieldValue(changeCommonTableRelationshipDTO, fieldName));
        return save(commonTableRelationshipDTO);
    }

    private void clearRelationsCache() {
        this.relationCacheNames.forEach(
                cacheName -> {
                    if (cacheManager.getCache(cacheName) != null) {
                        cacheManager.getCache(cacheName).clear();
                    }
                }
            );
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
