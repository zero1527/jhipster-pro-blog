package com.blog.myapp.repository;

import com.blog.myapp.domain.UploadImage;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Spring Data SQL repository for the UploadImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UploadImageRepository extends JpaRepository<UploadImage, Long>, JpaSpecificationExecutor<UploadImage> {
    @Query("select uploadImage from UploadImage uploadImage where uploadImage.user.login = ?#{principal.username}")
    List<UploadImage> findByUserIsCurrentUser();
    // jhipster-needle-repository-add-method - JHipster will add getters and setters here, do not remove
}
