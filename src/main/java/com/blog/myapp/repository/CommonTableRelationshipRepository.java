package com.blog.myapp.repository;

import com.blog.myapp.domain.CommonTableRelationship;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Spring Data  repository for the CommonTableRelationship entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommonTableRelationshipRepository
    extends JpaRepository<CommonTableRelationship, Long>, JpaSpecificationExecutor<CommonTableRelationship> {
    // jhipster-needle-repository-add-method - JHipster will add getters and setters here, do not remove
}
