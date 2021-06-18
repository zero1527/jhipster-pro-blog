package com.blog.myapp.repository;

import com.blog.myapp.domain.Department;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Spring Data SQL repository for the Department entity.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
    Page<Department> findAllByParentIsNull(Pageable pageable);

    Page<Department> findAllByParentId(Long parentId, Pageable pageable);

    @Query(
        value = "select distinct department from Department department left join fetch department.authorities",
        countQuery = "select count(distinct department) from Department department"
    )
    Page<Department> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct department from Department department left join fetch department.authorities")
    List<Department> findAllWithEagerRelationships();

    @Query("select department from Department department left join fetch department.authorities where department.id =:id")
    Optional<Department> findOneWithEagerRelationships(@Param("id") Long id);
    // jhipster-needle-repository-add-method - JHipster will add getters and setters here, do not remove
}
