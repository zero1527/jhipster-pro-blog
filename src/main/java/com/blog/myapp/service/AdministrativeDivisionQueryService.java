package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.blog.myapp.domain.*; // for static metamodels
import com.blog.myapp.domain.AdministrativeDivision;
import com.blog.myapp.domain.enumeration.RelationshipType;
import com.blog.myapp.repository.AdministrativeDivisionRepository;
import com.blog.myapp.repository.CommonTableRepository;
import com.blog.myapp.service.criteria.AdministrativeDivisionCriteria;
import com.blog.myapp.service.dto.AdministrativeDivisionDTO;
import com.blog.myapp.service.mapper.AdministrativeDivisionMapper;
import com.blog.myapp.util.JpaUtil;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.SQLQuery;
import org.hibernate.query.criteria.internal.path.PluralAttributePath;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * 用于对数据库中的{@link AdministrativeDivision}实体执行复杂查询的Service。
 * 主要输入是一个{@link AdministrativeDivisionCriteria}，它被转换为{@link Specification}，
 * 所有字段过滤器都将应用到表达式中。
 * 它返回满足条件的{@link AdministrativeDivisionDTO}列表{@link List} 或 {@link AdministrativeDivisionDTO} 的分页列表 {@link Page}。
 */
@Service
@Transactional(readOnly = true)
public class AdministrativeDivisionQueryService extends QueryService<AdministrativeDivision> {

    private final Logger log = LoggerFactory.getLogger(AdministrativeDivisionQueryService.class);

    private final AdministrativeDivisionRepository administrativeDivisionRepository;

    private final EntityManager em;

    private final CommonTableRepository commonTableRepository;

    private final AdministrativeDivisionMapper administrativeDivisionMapper;

    public AdministrativeDivisionQueryService(
        AdministrativeDivisionRepository administrativeDivisionRepository,
        EntityManager em,
        CommonTableRepository commonTableRepository,
        AdministrativeDivisionMapper administrativeDivisionMapper
    ) {
        this.administrativeDivisionRepository = administrativeDivisionRepository;
        this.em = em;
        this.commonTableRepository = commonTableRepository;
        this.administrativeDivisionMapper = administrativeDivisionMapper;
    }

    /**
     * Return a {@link List} of {@link AdministrativeDivisionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AdministrativeDivisionDTO> findByCriteria(AdministrativeDivisionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AdministrativeDivision> specification = createSpecification(criteria);
        return administrativeDivisionMapper.toDto(administrativeDivisionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AdministrativeDivisionDTO} which matches the criteria from the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdministrativeDivisionDTO> findBySpecification(Specification<AdministrativeDivision> specification, Pageable page) {
        log.debug("find by criteria : {}, page: {}", specification, page);
        return administrativeDivisionRepository.findAll(specification, page).map(administrativeDivisionMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link AdministrativeDivisionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdministrativeDivisionDTO> findByCriteria(AdministrativeDivisionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AdministrativeDivision> specification = createSpecification(criteria);
        return administrativeDivisionRepository.findAll(specification, page).map(administrativeDivisionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AdministrativeDivisionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AdministrativeDivision> specification = createSpecification(criteria);
        return administrativeDivisionRepository.count(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countBySpecification(Specification specification) {
        log.debug("count by specification : {}", specification);
        return administrativeDivisionRepository.count(specification);
    }

    /**
     * Function to convert {@link AdministrativeDivisionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AdministrativeDivision> createSpecification(AdministrativeDivisionCriteria criteria) {
        Specification<AdministrativeDivision> specification = Specification.where(null);
        if (criteria != null) {
            if (StringUtils.isNotEmpty(criteria.getJhiCommonSearchKeywords())) {
                if (StringUtils.isNumeric(criteria.getJhiCommonSearchKeywords())) {
                    specification =
                        specification.or(
                            buildSpecification(
                                new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                AdministrativeDivision_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (LongFilter) new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                AdministrativeDivision_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(criteria.getJhiCommonSearchKeywords())),
                                AdministrativeDivision_.level
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (DoubleFilter) new DoubleFilter().setEquals(Double.valueOf(criteria.getJhiCommonSearchKeywords())),
                                AdministrativeDivision_.lng
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (DoubleFilter) new DoubleFilter().setEquals(Double.valueOf(criteria.getJhiCommonSearchKeywords())),
                                AdministrativeDivision_.lat
                            )
                        );
                } else {
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                AdministrativeDivision_.name
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                AdministrativeDivision_.areaCode
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                AdministrativeDivision_.cityCode
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                AdministrativeDivision_.mergerName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                AdministrativeDivision_.shortName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                AdministrativeDivision_.zipCode
                            )
                        );
                }
            } else {
                if (criteria.getId() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getId(), AdministrativeDivision_.id));
                }
                if (criteria.getName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getName(), AdministrativeDivision_.name));
                }
                if (criteria.getAreaCode() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getAreaCode(), AdministrativeDivision_.areaCode));
                }
                if (criteria.getCityCode() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getCityCode(), AdministrativeDivision_.cityCode));
                }
                if (criteria.getMergerName() != null) {
                    specification =
                        specification.and(buildStringSpecification(criteria.getMergerName(), AdministrativeDivision_.mergerName));
                }
                if (criteria.getShortName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getShortName(), AdministrativeDivision_.shortName));
                }
                if (criteria.getZipCode() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getZipCode(), AdministrativeDivision_.zipCode));
                }
                if (criteria.getLevel() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getLevel(), AdministrativeDivision_.level));
                }
                if (criteria.getLng() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getLng(), AdministrativeDivision_.lng));
                }
                if (criteria.getLat() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getLat(), AdministrativeDivision_.lat));
                }
                if (criteria.getChildrenId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getChildrenId(),
                                root -> root.join(AdministrativeDivision_.children, JoinType.LEFT).get(AdministrativeDivision_.id)
                            )
                        );
                }
                if (criteria.getChildrenName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getChildrenName(),
                                root -> root.join(AdministrativeDivision_.children, JoinType.LEFT).get(AdministrativeDivision_.name)
                            )
                        );
                }
                if (criteria.getParentId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getParentId(),
                                root -> root.join(AdministrativeDivision_.parent, JoinType.LEFT).get(AdministrativeDivision_.id)
                            )
                        );
                }
                if (criteria.getParentName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getParentName(),
                                root -> root.join(AdministrativeDivision_.parent, JoinType.LEFT).get(AdministrativeDivision_.name)
                            )
                        );
                }
            }
        }
        return specification;
    }

    @Transactional
    public boolean updateBySpecifield(String fieldName, Object value, AdministrativeDivisionCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<AdministrativeDivision> q = cb.createCriteriaUpdate(AdministrativeDivision.class);
        CriteriaQuery<AdministrativeDivision> sq = cb.createQuery(AdministrativeDivision.class);
        Root<AdministrativeDivision> root = q.from(AdministrativeDivision.class);
        q.set(root.get(fieldName), value).where(createSpecification(criteria).toPredicate(root, sq, cb));
        int result = em.createQuery(q).executeUpdate();
        em.flush();
        return result > 0;
    }

    /**
     * 直接转换为dto。maytoone的，直接查询结果。one-to-many和many-to-many后续加载
     * @param entityName 模型名称
     * @param criteria 条件表达式
     * @param predicate 条件
     * @param pageable 分页
     * @return Page<AdministrativeDivisionDTO>
     */
    @Transactional(readOnly = true)
    public Page<AdministrativeDivisionDTO> selectByCustomEntity(
        String entityName,
        AdministrativeDivisionCriteria criteria,
        Predicate predicate,
        Specification specification,
        Pageable pageable
    ) {
        List<AdministrativeDivision> dataList = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<AdministrativeDivision> root = q.from(AdministrativeDivision.class);
        if (StringUtils.isEmpty(entityName)) {
            entityName = "AdministrativeDivision";
        }
        Optional<CommonTable> oneByEntityName = commonTableRepository.findOneByEntityName(entityName);
        List<String> fields = new ArrayList<>();
        List<CommonTableRelationship> toManyRelationships = new ArrayList<>();
        List<CommonTableRelationship> manyToOneRelationships = new ArrayList<>();
        List<Selection<?>> s = new ArrayList<>();
        if (oneByEntityName.isPresent()) {
            List<CommonTableField> tableFields = oneByEntityName
                .get()
                .getCommonTableFields()
                .stream()
                .filter(commonTableField -> !commonTableField.getHideInList())
                .collect(Collectors.toList());
            tableFields.forEach(commonTableField -> fields.add(commonTableField.getEntityFieldName()));
            List<CommonTableRelationship> tableRelationships = oneByEntityName
                .get()
                .getRelationships()
                .stream()
                .filter(commonTableRelationship -> !commonTableRelationship.getHideInList())
                .collect(Collectors.toList());
            tableRelationships.forEach(
                commonTableRelationship -> {
                    if (
                        commonTableRelationship.getRelationshipType().equals(RelationshipType.ONE_TO_MANY) ||
                        commonTableRelationship.getRelationshipType().equals(RelationshipType.MANY_TO_MANY)
                    ) {
                        toManyRelationships.add(commonTableRelationship);
                    } else {
                        try {
                            String clazzName =
                                AdministrativeDivision.class.getDeclaredField(commonTableRelationship.getRelationshipName())
                                    .getType()
                                    .getName();
                            manyToOneRelationships.add(commonTableRelationship);
                            root.join(commonTableRelationship.getRelationshipName(), JoinType.LEFT);
                            String idFieldName = JpaUtil.getPkColumn(clazzName);
                            String alaisId = commonTableRelationship.getRelationshipName() + "." + idFieldName;
                            s.add(root.get(commonTableRelationship.getRelationshipName()).get(idFieldName).alias(alaisId));
                            String relationPredicateAlias =
                                commonTableRelationship.getRelationshipName() + "." + commonTableRelationship.getOtherEntityField();
                            if (s.stream().noneMatch(predicateRelation -> predicateRelation.getAlias().equals(relationPredicateAlias))) {
                                s.add(
                                    root
                                        .get(commonTableRelationship.getRelationshipName())
                                        .get(commonTableRelationship.getOtherEntityField())
                                        .alias(relationPredicateAlias)
                                );
                            }
                        } catch (NoSuchFieldException e) {
                            log.error(e.getMessage());
                        }
                    }
                }
            );
        }
        s.addAll(fields.stream().map(fieldName -> root.get(fieldName).alias(fieldName)).collect(Collectors.toList()));
        q.multiselect(s);
        Predicate criteriaPredicate = createSpecification(criteria).toPredicate(root, q, cb);
        Predicate specificationPredicate = specification == null ? null : specification.toPredicate(root, q, cb);
        if (criteriaPredicate != null) {
            q.where(criteriaPredicate);
        } else if (predicate != null) {
            q.where(predicate);
        } else if (specificationPredicate != null) {
            q.where(specificationPredicate);
        }
        q.distinct(true);
        long totalItems;
        if (specificationPredicate != null) {
            totalItems = countBySpecification(specification);
        } else {
            totalItems = countByCriteria(criteria);
        }
        if (totalItems > 0) {
            if (pageable != null) {
                List<Order> orders = new ArrayList<>();
                pageable
                    .getSort()
                    .forEach(
                        order ->
                            orders.add(order.isAscending() ? cb.asc(root.get(order.getProperty())) : cb.desc(root.get(order.getProperty())))
                    );
                q.orderBy(orders);
            }
            TypedQuery<Tuple> query = em.createQuery(q);
            if (pageable != null) {
                pageable.getSort().toList();
                query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
                query.setMaxResults(pageable.getPageSize());
            }
            List<Tuple> list = query.getResultList();
            for (Tuple tu : list) {
                Map<String, Object> itemmap = new HashMap<>();
                for (TupleElement<?> element : tu.getElements()) {
                    itemmap.put(element.getAlias(), tu.get(element.getAlias()));
                }
                AdministrativeDivision administrativeDivision = BeanUtil.mapToBean(itemmap, AdministrativeDivision.class, true);
                // 组装manytoone的内容
                manyToOneRelationships.forEach(
                    relationship -> {
                        try {
                            String clazzName =
                                AdministrativeDivision.class.getDeclaredField(relationship.getRelationshipName()).getType().getName();
                            String idFieldName = JpaUtil.getPkColumn(clazzName);
                            String alaisId = relationship.getRelationshipName() + "." + idFieldName;
                            String alaisField = relationship.getRelationshipName() + "." + relationship.getOtherEntityField();
                            Object relationObject = ReflectUtil.newInstance(clazzName);
                            if (itemmap.get(alaisId) != null) {
                                BeanUtil.setFieldValue(relationObject, idFieldName, itemmap.get(alaisId));
                                if (!alaisField.equals(alaisId)) {
                                    BeanUtil.setFieldValue(relationObject, relationship.getOtherEntityField(), itemmap.get(alaisField));
                                }
                                BeanUtil.setFieldValue(administrativeDivision, relationship.getRelationshipName(), relationObject);
                            }
                        } catch (NoSuchFieldException e) {
                            log.error(e.getMessage());
                        }
                    }
                );
                // 组装manytomany等的内容
                toManyRelationships
                    .stream()
                    .filter(
                        relationship ->
                            ObjectUtils.isNotEmpty(FieldUtils.getField(AdministrativeDivision.class, relationship.getRelationshipName()))
                    )
                    .forEach(
                        relationshipTemp -> {
                            CriteriaQuery<Tuple> subQuery = cb.createTupleQuery();
                            Class<?> clazz =
                                ((PluralAttributePath<?>) root.get(relationshipTemp.getRelationshipName())).getAttribute()
                                    .getElementType()
                                    .getJavaType();
                            Root<?> manyRoot = subQuery.from(clazz);
                            if (clazz.getSimpleName().equals("AdministrativeDivision")) {
                                BeanUtil.setFieldValue(
                                    administrativeDivision,
                                    relationshipTemp.getRelationshipName(),
                                    this.selectByCustomEntity(
                                            "AdministrativeDivision",
                                            null,
                                            cb.equal(
                                                manyRoot.get(relationshipTemp.getOtherEntityRelationshipName()).get("id"),
                                                itemmap.get("id")
                                            ),
                                            null,
                                            null
                                        )
                                );
                            } else {
                                Selection<Object> idAlias = manyRoot.get("id").alias("id");
                                if (relationshipTemp.getOtherEntityField() != null) {
                                    subQuery.multiselect(
                                        idAlias,
                                        manyRoot.get(relationshipTemp.getOtherEntityField()).alias(relationshipTemp.getOtherEntityField())
                                    );
                                    subQuery.where(
                                        cb.equal(
                                            manyRoot.get(relationshipTemp.getOtherEntityRelationshipName()).get("id"),
                                            itemmap.get("id")
                                        )
                                    );
                                    Set<Object> subdataList = new LinkedHashSet<>();
                                    List<Tuple> sublist = em.createQuery(subQuery).getResultList();
                                    for (Tuple stu : sublist) {
                                        Map<String, Object> subitemmap = new HashMap<>();
                                        for (TupleElement<?> element : stu.getElements()) {
                                            subitemmap.put(element.getAlias(), stu.get(element.getAlias()));
                                        }
                                        subdataList.add(BeanUtil.mapToBean(subitemmap, clazz, true));
                                    }
                                    BeanUtil.setFieldValue(administrativeDivision, relationshipTemp.getRelationshipName(), subdataList);
                                } else {
                                    // todo 暂时不予处理getOtherEntityField为null的情况。
                                    log.error(
                                        "AdministrativeDivision." + relationshipTemp.getRelationshipName() + "otherEntityField为空。"
                                    );
                                }
                            }
                        }
                    );
                dataList.add(administrativeDivision);
            }
        }
        return new PageImpl<>(administrativeDivisionMapper.toDto(dataList), pageable == null ? Pageable.unpaged() : pageable, totalItems);
    }

    public List<Map<String, Object>> findAllByJpql(String jqpl) {
        Query query = em.createQuery(jqpl);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public Map<String, Object> getByJpql(String jqpl) {
        Query query = em.createQuery(jqpl);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map<String, Object>) query.getSingleResult();
    }

    public String toUpperFirstChar2(String string) {
        char[] chars = string.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= 32;
            return String.valueOf(chars);
        }
        return string;
    }
}
