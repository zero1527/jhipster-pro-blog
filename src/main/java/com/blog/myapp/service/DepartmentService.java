package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.blog.myapp.domain.Department;
import com.blog.myapp.repository.DepartmentRepository;
import com.blog.myapp.service.dto.DepartmentDTO;
import com.blog.myapp.service.mapper.DepartmentMapper;
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
 * Service Implementation for managing {@link Department}.
 */
@Service
@Transactional
public class DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentService.class);
    private final List<String> relationCacheNames = Arrays.asList(
        com.blog.myapp.domain.Department.class.getName() + ".parent",
        com.blog.myapp.domain.Authority.class.getName() + ".departments",
        com.blog.myapp.domain.Department.class.getName() + ".children",
        com.blog.myapp.domain.User.class.getName() + ".department"
    );

    private final DepartmentRepository departmentRepository;

    private final CacheManager cacheManager;

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, CacheManager cacheManager, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.cacheManager = cacheManager;
        this.departmentMapper = departmentMapper;
    }

    /**
     * Save a department.
     *
     * @param departmentDTO the entity to save.
     * @return the persisted entity.
     */
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        log.debug("Request to save Department : {}", departmentDTO);
        Department department = departmentMapper.toEntity(departmentDTO);
        clearChildrenCache();
        department = departmentRepository.save(department);
        // 更新缓存
        if (department.getParent() != null) {
            department.getParent().addChildren(department);
        }
        return departmentMapper.toDto(department);
    }

    /**
     * Partially update a department.
     *
     * @param departmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DepartmentDTO> partialUpdate(DepartmentDTO departmentDTO) {
        log.debug("Request to partially update Department : {}", departmentDTO);

        return departmentRepository
            .findById(departmentDTO.getId())
            .map(
                existingDepartment -> {
                    departmentMapper.partialUpdate(existingDepartment, departmentDTO);
                    return existingDepartment;
                }
            )
            .map(departmentRepository::save)
            .map(departmentMapper::toDto);
    }

    /**
     * Get all the departments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DepartmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Departments");
        return departmentRepository.findAll(pageable).map(departmentMapper::toDto);
    }

    /**
     * Get all the departments for parent is null.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DepartmentDTO> findAllTop(Pageable pageable) {
        log.debug("Request to get all Departments for parent is null");
        return departmentRepository.findAllByParentIsNull(pageable).map(departmentMapper::toDto);
    }

    /**
     * Get all the departments for parent is parentId.
     *
     * @param parentId the Id of parent
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<DepartmentDTO> findChildrenByParentId(Long parentId) {
        log.debug("Request to get all Departments for parent is parentId");
        return departmentRepository.findAllByParentId(parentId, Pageable.unpaged()).map(departmentMapper::toDto).getContent();
    }

    /**
     * count all the departments.
     * @return the count of entities
     */
    @Transactional(readOnly = true)
    public long count() {
        log.debug("Request to count all Departments");
        return departmentRepository.count();
    }

    /**
     * Get all the departments with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DepartmentDTO> findAllWithEagerRelationships(Pageable pageable) {
        return departmentRepository.findAllWithEagerRelationships(pageable).map(departmentMapper::toDto);
    }

    /**
     * Get one department by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DepartmentDTO> findOne(Long id) {
        log.debug("Request to get Department : {}", id);
        return departmentRepository.findOneWithEagerRelationships(id).map(departmentMapper::toDto);
    }

    /**
     * Get one department by example.
     *
     * @param example the example of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DepartmentDTO> findOneByExample(Example<Department> example) {
        log.debug("Request to get Department by example");
        return departmentRepository.findOne(example).map(departmentMapper::toDto);
    }

    /**
     * Get all the departments by example.
     *
     * @param example the example of the entity.
     * @return the entities.
     */

    @Transactional(readOnly = true)
    public Page<DepartmentDTO> findAllByExample(Example<Department> example, Pageable pageable) {
        log.debug("Request to get Department by example");
        return departmentRepository.findAll(example, pageable).map(departmentMapper::toDto);
    }

    /**
     * Delete the department by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Department : {}", id);
        Department department = departmentRepository.getOne(id);
        if (department.getParent() != null) {
            department.getParent().removeChildren(department);
        }
        if (department.getChildren() != null) {
            department
                .getChildren()
                .forEach(
                    subDepartment -> {
                        subDepartment.setParent(null);
                    }
                );
        }
        departmentRepository.deleteById(id);
    }

    /**
     * Update ignore specified fields by department
     */
    public DepartmentDTO updateByIgnoreSpecifiedFields(DepartmentDTO changeDepartmentDTO, Set<String> unchangedFields) {
        DepartmentDTO departmentDTO = findOne(changeDepartmentDTO.getId()).get();
        BeanUtil.copyProperties(changeDepartmentDTO, departmentDTO, unchangedFields.toArray(new String[0]));
        departmentDTO = save(departmentDTO);
        return departmentDTO;
    }

    /**
     * Update specified fields by department
     */
    public DepartmentDTO updateBySpecifiedFields(DepartmentDTO changeDepartmentDTO, Set<String> fieldNames) {
        DepartmentDTO departmentDTO = findOne(changeDepartmentDTO.getId()).get();
        DepartmentDTO finalDepartmentDTO = departmentDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalDepartmentDTO, fieldName, BeanUtil.getFieldValue(changeDepartmentDTO, fieldName));
            }
        );
        departmentDTO = save(finalDepartmentDTO);
        return departmentDTO;
    }

    /**
     * Update specified field by department
     */
    public DepartmentDTO updateBySpecifiedField(DepartmentDTO changeDepartmentDTO, String fieldName) {
        DepartmentDTO departmentDTO = findOne(changeDepartmentDTO.getId()).get();
        BeanUtil.setFieldValue(departmentDTO, fieldName, BeanUtil.getFieldValue(changeDepartmentDTO, fieldName));
        departmentDTO = save(departmentDTO);
        return departmentDTO;
    }

    // 清除children缓存
    private void clearChildrenCache() {
        Objects.requireNonNull(cacheManager.getCache(com.blog.myapp.domain.Department.class.getName() + ".children")).clear();
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
