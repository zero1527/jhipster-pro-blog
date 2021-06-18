package com.blog.myapp.repository;

import com.blog.myapp.domain.StatisticsApi;
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
 * Spring Data SQL repository for the StatisticsApi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatisticsApiRepository extends JpaRepository<StatisticsApi, Long>, JpaSpecificationExecutor<StatisticsApi> {
    @Query("select statisticsApi from StatisticsApi statisticsApi where statisticsApi.creator.login = ?#{principal.username}")
    List<StatisticsApi> findByCreatorIsCurrentUser();

    @Query("select statisticsApi from StatisticsApi statisticsApi where statisticsApi.modifier.login = ?#{principal.username}")
    List<StatisticsApi> findByModifierIsCurrentUser();
    // jhipster-needle-repository-add-method - JHipster will add getters and setters here, do not remove
}
