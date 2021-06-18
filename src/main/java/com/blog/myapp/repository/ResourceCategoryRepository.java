package com.blog.myapp.repository;

import com.blog.myapp.domain.ResourceCategory;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Spring Data SQL repository for the ResourceCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceCategoryRepository extends JpaRepository<ResourceCategory, Long>, JpaSpecificationExecutor<ResourceCategory> {
    Page<ResourceCategory> findAllByParentIsNull(Pageable pageable);

    Page<ResourceCategory> findAllByParentId(Long parentId, Pageable pageable);
    // jhipster-needle-repository-add-method - JHipster will add getters and setters here, do not remove
}
