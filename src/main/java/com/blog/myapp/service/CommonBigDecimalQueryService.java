package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.blog.myapp.domain.*; // for static metamodels
import com.blog.myapp.domain.CommonBigDecimal;
import com.blog.myapp.domain.enumeration.RelationshipType;
import com.blog.myapp.repository.CommonBigDecimalRepository;
import com.blog.myapp.repository.CommonTableRepository;
import com.blog.myapp.service.criteria.CommonBigDecimalCriteria;
import com.blog.myapp.service.dto.CommonBigDecimalDTO;
import com.blog.myapp.service.mapper.CommonBigDecimalMapper;
import com.blog.myapp.util.JpaUtil;
import java.math.BigDecimal;
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
import tech.jhipster.service.filter.BigDecimalFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * 用于对数据库中的{@link CommonBigDecimal}实体执行复杂查询的Service。
 * 主要输入是一个{@link CommonBigDecimalCriteria}，它被转换为{@link Specification}，
 * 所有字段过滤器都将应用到表达式中。
 * 它返回满足条件的{@link CommonBigDecimalDTO}列表{@link List} 或 {@link CommonBigDecimalDTO} 的分页列表 {@link Page}。
 */
@Service
@Transactional(readOnly = true)
public class CommonBigDecimalQueryService extends QueryService<CommonBigDecimal> {

    private final Logger log = LoggerFactory.getLogger(CommonBigDecimalQueryService.class);

    private final CommonBigDecimalRepository commonBigDecimalRepository;

    private final EntityManager em;

    private final CommonTableRepository commonTableRepository;

    private final CommonBigDecimalMapper commonBigDecimalMapper;

    public CommonBigDecimalQueryService(
        CommonBigDecimalRepository commonBigDecimalRepository,
        EntityManager em,
        CommonTableRepository commonTableRepository,
        CommonBigDecimalMapper commonBigDecimalMapper
    ) {
        this.commonBigDecimalRepository = commonBigDecimalRepository;
        this.em = em;
        this.commonTableRepository = commonTableRepository;
        this.commonBigDecimalMapper = commonBigDecimalMapper;
    }

    /**
     * Return a {@link List} of {@link CommonBigDecimalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CommonBigDecimalDTO> findByCriteria(CommonBigDecimalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CommonBigDecimal> specification = createSpecification(criteria);
        return commonBigDecimalMapper.toDto(commonBigDecimalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CommonBigDecimalDTO} which matches the criteria from the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonBigDecimalDTO> findBySpecification(Specification<CommonBigDecimal> specification, Pageable page) {
        log.debug("find by criteria : {}, page: {}", specification, page);
        return commonBigDecimalRepository.findAll(specification, page).map(commonBigDecimalMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link CommonBigDecimalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonBigDecimalDTO> findByCriteria(CommonBigDecimalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CommonBigDecimal> specification = createSpecification(criteria);
        return commonBigDecimalRepository.findAll(specification, page).map(commonBigDecimalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CommonBigDecimalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CommonBigDecimal> specification = createSpecification(criteria);
        return commonBigDecimalRepository.count(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countBySpecification(Specification specification) {
        log.debug("count by specification : {}", specification);
        return commonBigDecimalRepository.count(specification);
    }

    /**
     * Function to convert {@link CommonBigDecimalCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CommonBigDecimal> createSpecification(CommonBigDecimalCriteria criteria) {
        Specification<CommonBigDecimal> specification = Specification.where(null);
        if (criteria != null) {
            if (StringUtils.isNotEmpty(criteria.getJhiCommonSearchKeywords())) {
                if (StringUtils.isNumeric(criteria.getJhiCommonSearchKeywords())) {
                    specification =
                        specification.or(
                            buildSpecification(
                                new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonBigDecimal_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (LongFilter) new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonBigDecimal_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (LongFilter) new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonBigDecimal_.ownerId
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (BigDecimalFilter) new BigDecimalFilter()
                                    .setEquals(BigDecimal.valueOf(Double.parseDouble(criteria.getJhiCommonSearchKeywords()))),
                                CommonBigDecimal_.value
                            )
                        );
                } else {
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonBigDecimal_.ownerType
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonBigDecimal_.fieldName
                            )
                        );
                }
            } else {
                if (criteria.getId() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getId(), CommonBigDecimal_.id));
                }
                if (criteria.getOwnerType() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getOwnerType(), CommonBigDecimal_.ownerType));
                }
                if (criteria.getOwnerId() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getOwnerId(), CommonBigDecimal_.ownerId));
                }
                if (criteria.getFieldName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getFieldName(), CommonBigDecimal_.fieldName));
                }
                if (criteria.getValue() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getValue(), CommonBigDecimal_.value));
                }
            }
        }
        return specification;
    }

    @Transactional
    public boolean updateBySpecifield(String fieldName, Object value, CommonBigDecimalCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<CommonBigDecimal> q = cb.createCriteriaUpdate(CommonBigDecimal.class);
        CriteriaQuery<CommonBigDecimal> sq = cb.createQuery(CommonBigDecimal.class);
        Root<CommonBigDecimal> root = q.from(CommonBigDecimal.class);
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
     * @return Page<CommonBigDecimalDTO>
     */
    @Transactional(readOnly = true)
    public Page<CommonBigDecimalDTO> selectByCustomEntity(
        String entityName,
        CommonBigDecimalCriteria criteria,
        Predicate predicate,
        Specification specification,
        Pageable pageable
    ) {
        List<CommonBigDecimal> dataList = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<CommonBigDecimal> root = q.from(CommonBigDecimal.class);
        if (StringUtils.isEmpty(entityName)) {
            entityName = "CommonBigDecimal";
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
                                CommonBigDecimal.class.getDeclaredField(commonTableRelationship.getRelationshipName()).getType().getName();
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
                CommonBigDecimal commonBigDecimal = BeanUtil.mapToBean(itemmap, CommonBigDecimal.class, true);
                // 组装manytoone的内容
                manyToOneRelationships.forEach(
                    relationship -> {
                        try {
                            String clazzName =
                                CommonBigDecimal.class.getDeclaredField(relationship.getRelationshipName()).getType().getName();
                            String idFieldName = JpaUtil.getPkColumn(clazzName);
                            String alaisId = relationship.getRelationshipName() + "." + idFieldName;
                            String alaisField = relationship.getRelationshipName() + "." + relationship.getOtherEntityField();
                            Object relationObject = ReflectUtil.newInstance(clazzName);
                            if (itemmap.get(alaisId) != null) {
                                BeanUtil.setFieldValue(relationObject, idFieldName, itemmap.get(alaisId));
                                if (!alaisField.equals(alaisId)) {
                                    BeanUtil.setFieldValue(relationObject, relationship.getOtherEntityField(), itemmap.get(alaisField));
                                }
                                BeanUtil.setFieldValue(commonBigDecimal, relationship.getRelationshipName(), relationObject);
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
                            ObjectUtils.isNotEmpty(FieldUtils.getField(CommonBigDecimal.class, relationship.getRelationshipName()))
                    )
                    .forEach(
                        relationshipTemp -> {
                            CriteriaQuery<Tuple> subQuery = cb.createTupleQuery();
                            Class<?> clazz =
                                ((PluralAttributePath<?>) root.get(relationshipTemp.getRelationshipName())).getAttribute()
                                    .getElementType()
                                    .getJavaType();
                            Root<?> manyRoot = subQuery.from(clazz);
                            if (clazz.getSimpleName().equals("CommonBigDecimal")) {
                                BeanUtil.setFieldValue(
                                    commonBigDecimal,
                                    relationshipTemp.getRelationshipName(),
                                    this.selectByCustomEntity(
                                            "CommonBigDecimal",
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
                                    BeanUtil.setFieldValue(commonBigDecimal, relationshipTemp.getRelationshipName(), subdataList);
                                } else {
                                    // todo 暂时不予处理getOtherEntityField为null的情况。
                                    log.error("CommonBigDecimal." + relationshipTemp.getRelationshipName() + "otherEntityField为空。");
                                }
                            }
                        }
                    );
                dataList.add(commonBigDecimal);
            }
        }
        return new PageImpl<>(commonBigDecimalMapper.toDto(dataList), pageable == null ? Pageable.unpaged() : pageable, totalItems);
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