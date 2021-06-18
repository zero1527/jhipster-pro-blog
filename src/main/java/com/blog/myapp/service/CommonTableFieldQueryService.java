package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.blog.myapp.domain.*; // for static metamodels
import com.blog.myapp.domain.CommonTableField;
import com.blog.myapp.domain.enumeration.RelationshipType;
import com.blog.myapp.repository.CommonTableFieldRepository;
import com.blog.myapp.repository.CommonTableRepository;
import com.blog.myapp.service.criteria.CommonTableFieldCriteria;
import com.blog.myapp.service.dto.CommonTableFieldDTO;
import com.blog.myapp.service.mapper.CommonTableFieldMapper;
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
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * 用于对数据库中的{@link CommonTableField}实体执行复杂查询的Service。
 * 主要输入是一个{@link CommonTableFieldCriteria}，它被转换为{@link Specification}，
 * 所有字段过滤器都将应用到表达式中。
 * 它返回满足条件的{@link CommonTableFieldDTO}列表{@link List} 或 {@link CommonTableFieldDTO} 的分页列表 {@link Page}。
 */
@Service
@Transactional(readOnly = true)
public class CommonTableFieldQueryService extends QueryService<CommonTableField> {

    private final Logger log = LoggerFactory.getLogger(CommonTableFieldQueryService.class);

    private final CommonTableFieldRepository commonTableFieldRepository;

    private final EntityManager em;

    private final CommonTableRepository commonTableRepository;

    private final CommonTableFieldMapper commonTableFieldMapper;

    public CommonTableFieldQueryService(
        CommonTableFieldRepository commonTableFieldRepository,
        EntityManager em,
        CommonTableRepository commonTableRepository,
        CommonTableFieldMapper commonTableFieldMapper
    ) {
        this.commonTableFieldRepository = commonTableFieldRepository;
        this.em = em;
        this.commonTableRepository = commonTableRepository;
        this.commonTableFieldMapper = commonTableFieldMapper;
    }

    /**
     * Return a {@link List} of {@link CommonTableFieldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CommonTableFieldDTO> findByCriteria(CommonTableFieldCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CommonTableField> specification = createSpecification(criteria);
        return commonTableFieldMapper.toDto(commonTableFieldRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CommonTableFieldDTO} which matches the criteria from the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonTableFieldDTO> findBySpecification(Specification<CommonTableField> specification, Pageable page) {
        log.debug("find by criteria : {}, page: {}", specification, page);
        return commonTableFieldRepository.findAll(specification, page).map(commonTableFieldMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link CommonTableFieldDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonTableFieldDTO> findByCriteria(CommonTableFieldCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CommonTableField> specification = createSpecification(criteria);
        return commonTableFieldRepository.findAll(specification, page).map(commonTableFieldMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CommonTableFieldCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CommonTableField> specification = createSpecification(criteria);
        return commonTableFieldRepository.count(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countBySpecification(Specification specification) {
        log.debug("count by specification : {}", specification);
        return commonTableFieldRepository.count(specification);
    }

    /**
     * Function to convert {@link CommonTableFieldCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CommonTableField> createSpecification(CommonTableFieldCriteria criteria) {
        Specification<CommonTableField> specification = Specification.where(null);
        if (criteria != null) {
            if (StringUtils.isNotEmpty(criteria.getJhiCommonSearchKeywords())) {
                if (StringUtils.isNumeric(criteria.getJhiCommonSearchKeywords())) {
                    specification =
                        specification.or(
                            buildSpecification(
                                new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTableField_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (LongFilter) new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTableField_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTableField_.columnWidth
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTableField_.order
                            )
                        );
                } else {
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.title
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.entityFieldName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.tableColumnName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.validateRules
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.fieldValues
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.help
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.fontColor
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.backgroundColor
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableField_.options
                            )
                        );
                }
            } else {
                if (criteria.getId() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getId(), CommonTableField_.id));
                }
                if (criteria.getTitle() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getTitle(), CommonTableField_.title));
                }
                if (criteria.getEntityFieldName() != null) {
                    specification =
                        specification.and(buildStringSpecification(criteria.getEntityFieldName(), CommonTableField_.entityFieldName));
                }
                if (criteria.getType() != null) {
                    specification = specification.and(buildSpecification(criteria.getType(), CommonTableField_.type));
                }
                if (criteria.getTableColumnName() != null) {
                    specification =
                        specification.and(buildStringSpecification(criteria.getTableColumnName(), CommonTableField_.tableColumnName));
                }
                if (criteria.getColumnWidth() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getColumnWidth(), CommonTableField_.columnWidth));
                }
                if (criteria.getOrder() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getOrder(), CommonTableField_.order));
                }
                if (criteria.getEditInList() != null) {
                    specification = specification.and(buildSpecification(criteria.getEditInList(), CommonTableField_.editInList));
                }
                if (criteria.getHideInList() != null) {
                    specification = specification.and(buildSpecification(criteria.getHideInList(), CommonTableField_.hideInList));
                }
                if (criteria.getHideInForm() != null) {
                    specification = specification.and(buildSpecification(criteria.getHideInForm(), CommonTableField_.hideInForm));
                }
                if (criteria.getEnableFilter() != null) {
                    specification = specification.and(buildSpecification(criteria.getEnableFilter(), CommonTableField_.enableFilter));
                }
                if (criteria.getValidateRules() != null) {
                    specification =
                        specification.and(buildStringSpecification(criteria.getValidateRules(), CommonTableField_.validateRules));
                }
                if (criteria.getShowInFilterTree() != null) {
                    specification =
                        specification.and(buildSpecification(criteria.getShowInFilterTree(), CommonTableField_.showInFilterTree));
                }
                if (criteria.getFixed() != null) {
                    specification = specification.and(buildSpecification(criteria.getFixed(), CommonTableField_.fixed));
                }
                if (criteria.getSortable() != null) {
                    specification = specification.and(buildSpecification(criteria.getSortable(), CommonTableField_.sortable));
                }
                if (criteria.getTreeIndicator() != null) {
                    specification = specification.and(buildSpecification(criteria.getTreeIndicator(), CommonTableField_.treeIndicator));
                }
                if (criteria.getClientReadOnly() != null) {
                    specification = specification.and(buildSpecification(criteria.getClientReadOnly(), CommonTableField_.clientReadOnly));
                }
                if (criteria.getFieldValues() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getFieldValues(), CommonTableField_.fieldValues));
                }
                if (criteria.getNotNull() != null) {
                    specification = specification.and(buildSpecification(criteria.getNotNull(), CommonTableField_.notNull));
                }
                if (criteria.getSystem() != null) {
                    specification = specification.and(buildSpecification(criteria.getSystem(), CommonTableField_.system));
                }
                if (criteria.getHelp() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getHelp(), CommonTableField_.help));
                }
                if (criteria.getFontColor() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getFontColor(), CommonTableField_.fontColor));
                }
                if (criteria.getBackgroundColor() != null) {
                    specification =
                        specification.and(buildStringSpecification(criteria.getBackgroundColor(), CommonTableField_.backgroundColor));
                }
                if (criteria.getNullHideInForm() != null) {
                    specification = specification.and(buildSpecification(criteria.getNullHideInForm(), CommonTableField_.nullHideInForm));
                }
                if (criteria.getEndUsed() != null) {
                    specification = specification.and(buildSpecification(criteria.getEndUsed(), CommonTableField_.endUsed));
                }
                if (criteria.getOptions() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getOptions(), CommonTableField_.options));
                }
                if (criteria.getMetaModelId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getMetaModelId(),
                                root -> root.join(CommonTableField_.metaModel, JoinType.LEFT).get(CommonTable_.id)
                            )
                        );
                }
                if (criteria.getMetaModelName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getMetaModelName(),
                                root -> root.join(CommonTableField_.metaModel, JoinType.LEFT).get(CommonTable_.name)
                            )
                        );
                }
                if (criteria.getCommonTableId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getCommonTableId(),
                                root -> root.join(CommonTableField_.commonTable, JoinType.LEFT).get(CommonTable_.id)
                            )
                        );
                }
                if (criteria.getCommonTableName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getCommonTableName(),
                                root -> root.join(CommonTableField_.commonTable, JoinType.LEFT).get(CommonTable_.name)
                            )
                        );
                }
            }
        }
        return specification;
    }

    @Transactional
    public boolean updateBySpecifield(String fieldName, Object value, CommonTableFieldCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<CommonTableField> q = cb.createCriteriaUpdate(CommonTableField.class);
        CriteriaQuery<CommonTableField> sq = cb.createQuery(CommonTableField.class);
        Root<CommonTableField> root = q.from(CommonTableField.class);
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
     * @return Page<CommonTableFieldDTO>
     */
    @Transactional(readOnly = true)
    public Page<CommonTableFieldDTO> selectByCustomEntity(
        String entityName,
        CommonTableFieldCriteria criteria,
        Predicate predicate,
        Specification specification,
        Pageable pageable
    ) {
        List<CommonTableField> dataList = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<CommonTableField> root = q.from(CommonTableField.class);
        if (StringUtils.isEmpty(entityName)) {
            entityName = "CommonTableField";
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
                                CommonTableField.class.getDeclaredField(commonTableRelationship.getRelationshipName()).getType().getName();
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
                CommonTableField commonTableField = BeanUtil.mapToBean(itemmap, CommonTableField.class, true);
                // 组装manytoone的内容
                manyToOneRelationships.forEach(
                    relationship -> {
                        try {
                            String clazzName =
                                CommonTableField.class.getDeclaredField(relationship.getRelationshipName()).getType().getName();
                            String idFieldName = JpaUtil.getPkColumn(clazzName);
                            String alaisId = relationship.getRelationshipName() + "." + idFieldName;
                            String alaisField = relationship.getRelationshipName() + "." + relationship.getOtherEntityField();
                            Object relationObject = ReflectUtil.newInstance(clazzName);
                            if (itemmap.get(alaisId) != null) {
                                BeanUtil.setFieldValue(relationObject, idFieldName, itemmap.get(alaisId));
                                if (!alaisField.equals(alaisId)) {
                                    BeanUtil.setFieldValue(relationObject, relationship.getOtherEntityField(), itemmap.get(alaisField));
                                }
                                BeanUtil.setFieldValue(commonTableField, relationship.getRelationshipName(), relationObject);
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
                            ObjectUtils.isNotEmpty(FieldUtils.getField(CommonTableField.class, relationship.getRelationshipName()))
                    )
                    .forEach(
                        relationshipTemp -> {
                            CriteriaQuery<Tuple> subQuery = cb.createTupleQuery();
                            Class<?> clazz =
                                ((PluralAttributePath<?>) root.get(relationshipTemp.getRelationshipName())).getAttribute()
                                    .getElementType()
                                    .getJavaType();
                            Root<?> manyRoot = subQuery.from(clazz);
                            if (clazz.getSimpleName().equals("CommonTableField")) {
                                BeanUtil.setFieldValue(
                                    commonTableField,
                                    relationshipTemp.getRelationshipName(),
                                    this.selectByCustomEntity(
                                            "CommonTableField",
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
                                    BeanUtil.setFieldValue(commonTableField, relationshipTemp.getRelationshipName(), subdataList);
                                } else {
                                    // todo 暂时不予处理getOtherEntityField为null的情况。
                                    log.error("CommonTableField." + relationshipTemp.getRelationshipName() + "otherEntityField为空。");
                                }
                            }
                        }
                    );
                dataList.add(commonTableField);
            }
        }
        return new PageImpl<>(commonTableFieldMapper.toDto(dataList), pageable == null ? Pageable.unpaged() : pageable, totalItems);
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
