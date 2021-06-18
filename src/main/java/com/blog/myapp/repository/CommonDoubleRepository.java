package com.blog.myapp.repository;

import com.blog.myapp.domain.CommonDouble;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Spring Data SQL repository for the CommonDouble entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommonDoubleRepository extends JpaRepository<CommonDouble, Long>, JpaSpecificationExecutor<CommonDouble> {
    // jhipster-needle-repository-add-method - JHipster will add getters and setters here, do not remove
}
