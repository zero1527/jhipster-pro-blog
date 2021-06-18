package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.Position;
import com.blog.myapp.repository.PositionRepository;
import com.blog.myapp.service.dto.PositionDTO;
import com.blog.myapp.service.mapper.PositionMapper;
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
 * Service Implementation for managing {@link Position}.
 */
@Service
@Transactional
public class PositionService {

    private final Logger log = LoggerFactory.getLogger(PositionService.class);

    private final PositionRepository positionRepository;

    private final CacheManager cacheManager;

    private final PositionMapper positionMapper;

    public PositionService(PositionRepository positionRepository, CacheManager cacheManager, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.cacheManager = cacheManager;
        this.positionMapper = positionMapper;
    }

    /**
     * Save a position.
     *
     * @param positionDTO the entity to save.
     * @return the persisted entity.
     */
    public PositionDTO save(PositionDTO positionDTO) {
        log.debug("Request to save Position : {}", positionDTO);
        Position position = positionMapper.toEntity(positionDTO);
        position = positionRepository.save(position);
        return positionMapper.toDto(position);
    }

    /**
     * Partially update a position.
     *
     * @param positionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PositionDTO> partialUpdate(PositionDTO positionDTO) {
        log.debug("Request to partially update Position : {}", positionDTO);

        return positionRepository
            .findById(positionDTO.getId())
            .map(
                existingPosition -> {
                    positionMapper.partialUpdate(existingPosition, positionDTO);
                    return existingPosition;
                }
            )
            .map(positionRepository::save)
            .map(positionMapper::toDto);
    }

    /**
     * Get all the positions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PositionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Positions");
        return positionRepository.findAll(pageable).map(positionMapper::toDto);
    }

    /**
     * count all the positions.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all Positions");
        return positionRepository.count();
    }

    /**
     * Get one position by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PositionDTO> findOne(Long id) {
        log.debug("Request to get Position : {}", id);
        return positionRepository.findById(id).map(positionMapper::toDto);
    }

    /**
     * Get one position by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PositionDTO> findOneByExample(Example<Position> example) {
        log.debug("Request to get Position by example");
        return positionRepository.findOne(example).map(positionMapper::toDto);
    }

    /**
     * Get all the positions by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<PositionDTO> findAllByExample(Example<Position> example, Pageable pageable) {
        log.debug("Request to get Position by example");
        return positionRepository.findAll(example, pageable).map(positionMapper::toDto);
    }

    /**
     * Delete the position by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Position : {}", id);
        positionRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by position
     */
    public PositionDTO updateByIgnoreSpecifiedFields(PositionDTO changePositionDTO, Set<String> unchangedFields) {
        PositionDTO positionDTO = findOne(changePositionDTO.getId()).get();
        BeanUtil.copyProperties(changePositionDTO, positionDTO, unchangedFields.toArray(new String[0]));
        positionDTO = save(positionDTO);
        return positionDTO;
    }

    /**
     * Update specified fields by position
     */
    public PositionDTO updateBySpecifiedFields(PositionDTO changePositionDTO, Set<String> fieldNames) {
        PositionDTO positionDTO = findOne(changePositionDTO.getId()).get();
        PositionDTO finalPositionDTO = positionDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalPositionDTO, fieldName, BeanUtil.getFieldValue(changePositionDTO, fieldName));
            }
        );
        positionDTO = save(finalPositionDTO);
        return positionDTO;
    }

    /**
     * Update specified field by position
     */
    public PositionDTO updateBySpecifiedField(PositionDTO changePositionDTO, String fieldName) {
        PositionDTO positionDTO = findOne(changePositionDTO.getId()).get();
        BeanUtil.setFieldValue(positionDTO, fieldName, BeanUtil.getFieldValue(changePositionDTO, fieldName));
        positionDTO = save(positionDTO);
        return positionDTO;
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
