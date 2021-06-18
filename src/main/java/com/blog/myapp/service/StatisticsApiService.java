package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.StatisticsApi;
import com.blog.myapp.repository.StatisticsApiRepository;
import com.blog.myapp.service.dto.StatisticsApiDTO;
import com.blog.myapp.service.mapper.StatisticsApiMapper;
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
 * Service Implementation for managing {@link StatisticsApi}.
 */
@Service
@Transactional
public class StatisticsApiService {

    private final Logger log = LoggerFactory.getLogger(StatisticsApiService.class);
    private final List<String> relationCacheNames = Arrays.asList(
        com.blog.myapp.domain.CommonTable.class.getName() + ".statisticsApi",
        com.blog.myapp.domain.User.class.getName() + ".statisticsApi",
        com.blog.myapp.domain.User.class.getName() + ".statisticsApi"
    );

    private final StatisticsApiRepository statisticsApiRepository;

    private final CacheManager cacheManager;

    private final StatisticsApiMapper statisticsApiMapper;

    public StatisticsApiService(
        StatisticsApiRepository statisticsApiRepository,
        CacheManager cacheManager,
        StatisticsApiMapper statisticsApiMapper
    ) {
        this.statisticsApiRepository = statisticsApiRepository;
        this.cacheManager = cacheManager;
        this.statisticsApiMapper = statisticsApiMapper;
    }

    /**
     * Save a statisticsApi.
     *
     * @param statisticsApiDTO the entity to save.
     * @return the persisted entity.
     */
    public StatisticsApiDTO save(StatisticsApiDTO statisticsApiDTO) {
        log.debug("Request to save StatisticsApi : {}", statisticsApiDTO);
        StatisticsApi statisticsApi = statisticsApiMapper.toEntity(statisticsApiDTO);
        statisticsApi = statisticsApiRepository.save(statisticsApi);
        return statisticsApiMapper.toDto(statisticsApi);
    }

    /**
     * Partially update a statisticsApi.
     *
     * @param statisticsApiDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StatisticsApiDTO> partialUpdate(StatisticsApiDTO statisticsApiDTO) {
        log.debug("Request to partially update StatisticsApi : {}", statisticsApiDTO);

        return statisticsApiRepository
            .findById(statisticsApiDTO.getId())
            .map(
                existingStatisticsApi -> {
                    statisticsApiMapper.partialUpdate(existingStatisticsApi, statisticsApiDTO);
                    return existingStatisticsApi;
                }
            )
            .map(statisticsApiRepository::save)
            .map(statisticsApiMapper::toDto);
    }

    /**
     * Get all the statisticsApis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StatisticsApiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StatisticsApis");
        return statisticsApiRepository.findAll(pageable).map(statisticsApiMapper::toDto);
    }

    /**
     * count all the statisticsApis.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all StatisticsApis");
        return statisticsApiRepository.count();
    }

    /**
     * Get one statisticsApi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StatisticsApiDTO> findOne(Long id) {
        log.debug("Request to get StatisticsApi : {}", id);
        return statisticsApiRepository.findById(id).map(statisticsApiMapper::toDto);
    }

    /**
     * Get one statisticsApi by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StatisticsApiDTO> findOneByExample(Example<StatisticsApi> example) {
        log.debug("Request to get StatisticsApi by example");
        return statisticsApiRepository.findOne(example).map(statisticsApiMapper::toDto);
    }

    /**
     * Get all the statisticsApis by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<StatisticsApiDTO> findAllByExample(Example<StatisticsApi> example, Pageable pageable) {
        log.debug("Request to get StatisticsApi by example");
        return statisticsApiRepository.findAll(example, pageable).map(statisticsApiMapper::toDto);
    }

    /**
     * Delete the statisticsApi by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StatisticsApi : {}", id);
        statisticsApiRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<StatisticsApiDTO> findByCreatorIsCurrentUser() {
        return statisticsApiMapper.toDto(statisticsApiRepository.findByCreatorIsCurrentUser());
    }

    @Transactional(readOnly = true)
    public List<StatisticsApiDTO> findByModifierIsCurrentUser() {
        return statisticsApiMapper.toDto(statisticsApiRepository.findByModifierIsCurrentUser());
    }

    /**
     * Update ignore specified fields by statisticsApi
     */
    public StatisticsApiDTO updateByIgnoreSpecifiedFields(StatisticsApiDTO changeStatisticsApiDTO, Set<String> unchangedFields) {
        StatisticsApiDTO statisticsApiDTO = findOne(changeStatisticsApiDTO.getId()).get();
        BeanUtil.copyProperties(changeStatisticsApiDTO, statisticsApiDTO, unchangedFields.toArray(new String[0]));
        statisticsApiDTO = save(statisticsApiDTO);
        return statisticsApiDTO;
    }

    /**
     * Update specified fields by statisticsApi
     */
    public StatisticsApiDTO updateBySpecifiedFields(StatisticsApiDTO changeStatisticsApiDTO, Set<String> fieldNames) {
        StatisticsApiDTO statisticsApiDTO = findOne(changeStatisticsApiDTO.getId()).get();
        StatisticsApiDTO finalStatisticsApiDTO = statisticsApiDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalStatisticsApiDTO, fieldName, BeanUtil.getFieldValue(changeStatisticsApiDTO, fieldName));
            }
        );
        statisticsApiDTO = save(finalStatisticsApiDTO);
        return statisticsApiDTO;
    }

    /**
     * Update specified field by statisticsApi
     */
    public StatisticsApiDTO updateBySpecifiedField(StatisticsApiDTO changeStatisticsApiDTO, String fieldName) {
        StatisticsApiDTO statisticsApiDTO = findOne(changeStatisticsApiDTO.getId()).get();
        BeanUtil.setFieldValue(statisticsApiDTO, fieldName, BeanUtil.getFieldValue(changeStatisticsApiDTO, fieldName));
        statisticsApiDTO = save(statisticsApiDTO);
        return statisticsApiDTO;
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
