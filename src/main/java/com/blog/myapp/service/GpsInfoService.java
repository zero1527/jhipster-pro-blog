package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.GpsInfo;
import com.blog.myapp.repository.GpsInfoRepository;
import com.blog.myapp.service.dto.GpsInfoDTO;
import com.blog.myapp.service.mapper.GpsInfoMapper;
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
 * Service Implementation for managing {@link GpsInfo}.
 */
@Service
@Transactional
public class GpsInfoService {

    private final Logger log = LoggerFactory.getLogger(GpsInfoService.class);

    private final GpsInfoRepository gpsInfoRepository;

    private final CacheManager cacheManager;

    private final GpsInfoMapper gpsInfoMapper;

    public GpsInfoService(GpsInfoRepository gpsInfoRepository, CacheManager cacheManager, GpsInfoMapper gpsInfoMapper) {
        this.gpsInfoRepository = gpsInfoRepository;
        this.cacheManager = cacheManager;
        this.gpsInfoMapper = gpsInfoMapper;
    }

    /**
     * Save a gpsInfo.
     *
     * @param gpsInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public GpsInfoDTO save(GpsInfoDTO gpsInfoDTO) {
        log.debug("Request to save GpsInfo : {}", gpsInfoDTO);
        GpsInfo gpsInfo = gpsInfoMapper.toEntity(gpsInfoDTO);
        gpsInfo = gpsInfoRepository.save(gpsInfo);
        return gpsInfoMapper.toDto(gpsInfo);
    }

    /**
     * Partially update a gpsInfo.
     *
     * @param gpsInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GpsInfoDTO> partialUpdate(GpsInfoDTO gpsInfoDTO) {
        log.debug("Request to partially update GpsInfo : {}", gpsInfoDTO);

        return gpsInfoRepository
            .findById(gpsInfoDTO.getId())
            .map(
                existingGpsInfo -> {
                    gpsInfoMapper.partialUpdate(existingGpsInfo, gpsInfoDTO);
                    return existingGpsInfo;
                }
            )
            .map(gpsInfoRepository::save)
            .map(gpsInfoMapper::toDto);
    }

    /**
     * Get all the gpsInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GpsInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GpsInfos");
        return gpsInfoRepository.findAll(pageable).map(gpsInfoMapper::toDto);
    }

    /**
     * count all the gpsInfos.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all GpsInfos");
        return gpsInfoRepository.count();
    }

    /**
     * Get one gpsInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GpsInfoDTO> findOne(Long id) {
        log.debug("Request to get GpsInfo : {}", id);
        return gpsInfoRepository.findById(id).map(gpsInfoMapper::toDto);
    }

    /**
     * Get one gpsInfo by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GpsInfoDTO> findOneByExample(Example<GpsInfo> example) {
        log.debug("Request to get GpsInfo by example");
        return gpsInfoRepository.findOne(example).map(gpsInfoMapper::toDto);
    }

    /**
     * Get all the gpsInfos by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<GpsInfoDTO> findAllByExample(Example<GpsInfo> example, Pageable pageable) {
        log.debug("Request to get GpsInfo by example");
        return gpsInfoRepository.findAll(example, pageable).map(gpsInfoMapper::toDto);
    }

    /**
     * Delete the gpsInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GpsInfo : {}", id);
        gpsInfoRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by gpsInfo
     */
    public GpsInfoDTO updateByIgnoreSpecifiedFields(GpsInfoDTO changeGpsInfoDTO, Set<String> unchangedFields) {
        GpsInfoDTO gpsInfoDTO = findOne(changeGpsInfoDTO.getId()).get();
        BeanUtil.copyProperties(changeGpsInfoDTO, gpsInfoDTO, unchangedFields.toArray(new String[0]));
        gpsInfoDTO = save(gpsInfoDTO);
        return gpsInfoDTO;
    }

    /**
     * Update specified fields by gpsInfo
     */
    public GpsInfoDTO updateBySpecifiedFields(GpsInfoDTO changeGpsInfoDTO, Set<String> fieldNames) {
        GpsInfoDTO gpsInfoDTO = findOne(changeGpsInfoDTO.getId()).get();
        GpsInfoDTO finalGpsInfoDTO = gpsInfoDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalGpsInfoDTO, fieldName, BeanUtil.getFieldValue(changeGpsInfoDTO, fieldName));
            }
        );
        gpsInfoDTO = save(finalGpsInfoDTO);
        return gpsInfoDTO;
    }

    /**
     * Update specified field by gpsInfo
     */
    public GpsInfoDTO updateBySpecifiedField(GpsInfoDTO changeGpsInfoDTO, String fieldName) {
        GpsInfoDTO gpsInfoDTO = findOne(changeGpsInfoDTO.getId()).get();
        BeanUtil.setFieldValue(gpsInfoDTO, fieldName, BeanUtil.getFieldValue(changeGpsInfoDTO, fieldName));
        gpsInfoDTO = save(gpsInfoDTO);
        return gpsInfoDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
