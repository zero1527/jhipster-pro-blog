package com.blog.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.blog.myapp.domain.*; // for static metamodels
import com.blog.myapp.domain.CommonTableRelationship;
import com.blog.myapp.domain.enumeration.RelationshipType;
import com.blog.myapp.repository.CommonTableRelationshipRepository;
import com.blog.myapp.repository.CommonTableRepository;
import com.blog.myapp.service.criteria.CommonTableRelationshipCriteria;
import com.blog.myapp.service.dto.CommonTableRelationshipDTO;
import com.blog.myapp.service.mapper.CommonTableRelationshipMapper;
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
 * 用于对数据库中的{@link CommonTableRelationship}实体执行复杂查询的Service。
 * 主要输入是一个{@link CommonTableRelationshipCriteria}，它被转换为{@link Specification}，
 * 所有字段过滤器都将应用到表达式中。
 * 它返回满足条件的{@link CommonTableRelationshipDTO}列表{@link List} 或 {@link CommonTableRelationshipDTO} 的分页列表 {@link Page}。
 */
@Service
@Transactional(readOnly = true)
public class CommonTableRelationshipQueryService extends QueryService<CommonTableRelationship> {

    private final Logger log = LoggerFactory.getLogger(CommonTableRelationshipQueryService.class);

    private final CommonTableRelationshipRepository commonTableRelationshipRepository;

    private final EntityManager em;

    private final CommonTableRepository commonTableRepository;

    private final CommonTableRelationshipMapper commonTableRelationshipMapper;

    public CommonTableRelationshipQueryService(
        CommonTableRelationshipRepository commonTableRelationshipRepository,
        EntityManager em,
        CommonTableRepository commonTableRepository,
        CommonTableRelationshipMapper commonTableRelationshipMapper
    ) {
        this.commonTableRelationshipRepository = commonTableRelationshipRepository;
        this.em = em;
        this.commonTableRepository = commonTableRepository;
        this.commonTableRelationshipMapper = commonTableRelationshipMapper;
    }

    /**
     * Return a {@link List} of {@link CommonTableRelationshipDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CommonTableRelationshipDTO> findByCriteria(CommonTableRelationshipCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CommonTableRelationship> specification = createSpecification(criteria);
        return commonTableRelationshipMapper.toDto(commonTableRelationshipRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CommonTableRelationshipDTO} which matches the criteria from the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonTableRelationshipDTO> findBySpecification(Specification<CommonTableRelationship> specification, Pageable page) {
        log.debug("find by criteria : {}, page: {}", specification, page);
        return commonTableRelationshipRepository.findAll(specification, page).map(commonTableRelationshipMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link CommonTableRelationshipDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonTableRelationshipDTO> findByCriteria(CommonTableRelationshipCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CommonTableRelationship> specification = createSpecification(criteria);
        return commonTableRelationshipRepository.findAll(specification, page).map(commonTableRelationshipMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CommonTableRelationshipCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CommonTableRelationship> specification = createSpecification(criteria);
        return commonTableRelationshipRepository.count(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countBySpecification(Specification specification) {
        log.debug("count by specification : {}", specification);
        return commonTableRelationshipRepository.count(specification);
    }

    /**
     * Function to convert {@link CommonTableRelationshipCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CommonTableRelationship> createSpecification(CommonTableRelationshipCriteria criteria) {
        Specification<CommonTableRelationship> specification = Specification.where(null);
        if (criteria != null) {
            if (StringUtils.isNotEmpty(criteria.getJhiCommonSearchKeywords())) {
                if (StringUtils.isNumeric(criteria.getJhiCommonSearchKeywords())) {
                    specification =
                        specification.or(
                            buildSpecification(
                                new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTableRelationship_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (LongFilter) new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTableRelationship_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTableRelationship_.columnWidth
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTableRelationship_.order
                            )
                        );
                } else {
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.name
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.otherEntityField
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.otherEntityName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.relationshipName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.otherEntityRelationshipName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.fontColor
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.backgroundColor
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.help
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.dataName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.webComponentType
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.dataDictionaryCode
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTableRelationship_.options
                            )
                        );
                }
            } else {
                if (criteria.getId() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getId(), CommonTableRelationship_.id));
                }
                if (criteria.getName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getName(), CommonTableRelationship_.name));
                }
                if (criteria.getRelationshipType() != null) {
                    specification =
                        specification.and(buildSpecification(criteria.getRelationshipType(), CommonTableRelationship_.relationshipType));
                }
                if (criteria.getSourceType() != null) {
                    specification = specification.and(buildSpecification(criteria.getSourceType(), CommonTableRelationship_.sourceType));
                }
                if (criteria.getOtherEntityField() != null) {
                    specification =
                        specification.and(
                            buildStringSpecification(criteria.getOtherEntityField(), CommonTableRelationship_.otherEntityField)
                        );
                }
                if (criteria.getOtherEntityName() != null) {
                    specification =
                        specification.and(
                            buildStringSpecification(criteria.getOtherEntityName(), CommonTableRelationship_.otherEntityName)
                        );
                }
                if (criteria.getRelationshipName() != null) {
                    specification =
                        specification.and(
                            buildStringSpecification(criteria.getRelationshipName(), CommonTableRelationship_.relationshipName)
                        );
                }
                if (criteria.getOtherEntityRelationshipName() != null) {
                    specification =
                        specification.and(
                            buildStringSpecification(
                                criteria.getOtherEntityRelationshipName(),
                                CommonTableRelationship_.otherEntityRelationshipName
                            )
                        );
                }
                if (criteria.getColumnWidth() != null) {
                    specification =
                        specification.and(buildRangeSpecification(criteria.getColumnWidth(), CommonTableRelationship_.columnWidth));
                }
                if (criteria.getOrder() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getOrder(), CommonTableRelationship_.order));
                }
                if (criteria.getFixed() != null) {
                    specification = specification.and(buildSpecification(criteria.getFixed(), CommonTableRelationship_.fixed));
                }
                if (criteria.getEditInList() != null) {
                    specification = specification.and(buildSpecification(criteria.getEditInList(), CommonTableRelationship_.editInList));
                }
                if (criteria.getEnableFilter() != null) {
                    specification =
                        specification.and(buildSpecification(criteria.getEnableFilter(), CommonTableRelationship_.enableFilter));
                }
                if (criteria.getHideInList() != null) {
                    specification = specification.and(buildSpecification(criteria.getHideInList(), CommonTableRelationship_.hideInList));
                }
                if (criteria.getHideInForm() != null) {
                    specification = specification.and(buildSpecification(criteria.getHideInForm(), CommonTableRelationship_.hideInForm));
                }
                if (criteria.getSystem() != null) {
                    specification = specification.and(buildSpecification(criteria.getSystem(), CommonTableRelationship_.system));
                }
                if (criteria.getFontColor() != null) {
                    specification =
                        specification.and(buildStringSpecification(criteria.getFontColor(), CommonTableRelationship_.fontColor));
                }
                if (criteria.getBackgroundColor() != null) {
                    specification =
                        specification.and(
                            buildStringSpecification(criteria.getBackgroundColor(), CommonTableRelationship_.backgroundColor)
                        );
                }
                if (criteria.getHelp() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getHelp(), CommonTableRelationship_.help));
                }
                if (criteria.getOwnerSide() != null) {
                    specification = specification.and(buildSpecification(criteria.getOwnerSide(), CommonTableRelationship_.ownerSide));
                }
                if (criteria.getDataName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getDataName(), CommonTableRelationship_.dataName));
                }
                if (criteria.getWebComponentType() != null) {
                    specification =
                        specification.and(
                            buildStringSpecification(criteria.getWebComponentType(), CommonTableRelationship_.webComponentType)
                        );
                }
                if (criteria.getOtherEntityIsTree() != null) {
                    specification =
                        specification.and(buildSpecification(criteria.getOtherEntityIsTree(), CommonTableRelationship_.otherEntityIsTree));
                }
                if (criteria.getShowInFilterTree() != null) {
                    specification =
                        specification.and(buildSpecification(criteria.getShowInFilterTree(), CommonTableRelationship_.showInFilterTree));
                }
                if (criteria.getDataDictionaryCode() != null) {
                    specification =
                        specification.and(
                            buildStringSpecification(criteria.getDataDictionaryCode(), CommonTableRelationship_.dataDictionaryCode)
                        );
                }
                if (criteria.getClientReadOnly() != null) {
                    specification =
                        specification.and(buildSpecification(criteria.getClientReadOnly(), CommonTableRelationship_.clientReadOnly));
                }
                if (criteria.getEndUsed() != null) {
                    specification = specification.and(buildSpecification(criteria.getEndUsed(), CommonTableRelationship_.endUsed));
                }
                if (criteria.getOptions() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getOptions(), CommonTableRelationship_.options));
                }
                if (criteria.getRelationEntityId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getRelationEntityId(),
                                root -> root.join(CommonTableRelationship_.relationEntity, JoinType.LEFT).get(CommonTable_.id)
                            )
                        );
                }
                if (criteria.getRelationEntityName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getRelationEntityName(),
                                root -> root.join(CommonTableRelationship_.relationEntity, JoinType.LEFT).get(CommonTable_.name)
                            )
                        );
                }
                if (criteria.getDataDictionaryNodeId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getDataDictionaryNodeId(),
                                root -> root.join(CommonTableRelationship_.dataDictionaryNode, JoinType.LEFT).get(DataDictionary_.id)
                            )
                        );
                }
                if (criteria.getDataDictionaryNodeName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getDataDictionaryNodeName(),
                                root -> root.join(CommonTableRelationship_.dataDictionaryNode, JoinType.LEFT).get(DataDictionary_.name)
                            )
                        );
                }
                if (criteria.getMetaModelId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getMetaModelId(),
                                root -> root.join(CommonTableRelationship_.metaModel, JoinType.LEFT).get(CommonTable_.id)
                            )
                        );
                }
                if (criteria.getMetaModelName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getMetaModelName(),
                                root -> root.join(CommonTableRelationship_.metaModel, JoinType.LEFT).get(CommonTable_.name)
                            )
                        );
                }
                if (criteria.getCommonTableId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getCommonTableId(),
                                root -> root.join(CommonTableRelationship_.commonTable, JoinType.LEFT).get(CommonTable_.id)
                            )
                        );
                }
                if (criteria.getCommonTableName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getCommonTableName(),
                                root -> root.join(CommonTableRelationship_.commonTable, JoinType.LEFT).get(CommonTable_.name)
                            )
                        );
                }
            }
        }
        return specification;
    }

    @Transactional
    public boolean updateBySpecifield(String fieldName, Object value, CommonTableRelationshipCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<CommonTableRelationship> q = cb.createCriteriaUpdate(CommonTableRelationship.class);
        CriteriaQuery<CommonTableRelationship> sq = cb.createQuery(CommonTableRelationship.class);
        Root<CommonTableRelationship> root = q.from(CommonTableRelationship.class);
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
     * @return Page<CommonTableRelationshipDTO>
     */
    @Transactional(readOnly = true)
    public Page<CommonTableRelationshipDTO> selectByCustomEntity(
        String entityName,
        CommonTableRelationshipCriteria criteria,
        Predicate predicate,
        Specification specification,
        Pageable pageable
    ) {
        List<CommonTableRelationship> dataList = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<CommonTableRelationship> root = q.from(CommonTableRelationship.class);
        if (StringUtils.isEmpty(entityName)) {
            entityName = "CommonTableRelationship";
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
                                CommonTableRelationship.class.getDeclaredField(commonTableRelationship.getRelationshipName())
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
                CommonTableRelationship commonTableRelationship = BeanUtil.mapToBean(itemmap, CommonTableRelationship.class, true);
                // 组装manytoone的内容
                manyToOneRelationships.forEach(
                    relationship -> {
                        try {
                            String clazzName =
                                CommonTableRelationship.class.getDeclaredField(relationship.getRelationshipName()).getType().getName();
                            String idFieldName = JpaUtil.getPkColumn(clazzName);
                            String alaisId = relationship.getRelationshipName() + "." + idFieldName;
                            String alaisField = relationship.getRelationshipName() + "." + relationship.getOtherEntityField();
                            Object relationObject = ReflectUtil.newInstance(clazzName);
                            if (itemmap.get(alaisId) != null) {
                                BeanUtil.setFieldValue(relationObject, idFieldName, itemmap.get(alaisId));
                                if (!alaisField.equals(alaisId)) {
                                    BeanUtil.setFieldValue(relationObject, relationship.getOtherEntityField(), itemmap.get(alaisField));
                                }
                                BeanUtil.setFieldValue(commonTableRelationship, relationship.getRelationshipName(), relationObject);
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
                            ObjectUtils.isNotEmpty(FieldUtils.getField(CommonTableRelationship.class, relationship.getRelationshipName()))
                    )
                    .forEach(
                        relationshipTemp -> {
                            CriteriaQuery<Tuple> subQuery = cb.createTupleQuery();
                            Class<?> clazz =
                                ((PluralAttributePath<?>) root.get(relationshipTemp.getRelationshipName())).getAttribute()
                                    .getElementType()
                                    .getJavaType();
                            Root<?> manyRoot = subQuery.from(clazz);
                            if (clazz.getSimpleName().equals("CommonTableRelationship")) {
                                BeanUtil.setFieldValue(
                                    commonTableRelationship,
                                    relationshipTemp.getRelationshipName(),
                                    this.selectByCustomEntity(
                                            "CommonTableRelationship",
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
                                    BeanUtil.setFieldValue(commonTableRelationship, relationshipTemp.getRelationshipName(), subdataList);
                                } else {
                                    // todo 暂时不予处理getOtherEntityField为null的情况。
                                    log.error(
                                        "CommonTableRelationship." + relationshipTemp.getRelationshipName() + "otherEntityField为空。"
                                    );
                                }
                            }
                        }
                    );
                dataList.add(commonTableRelationship);
            }
        }
        return new PageImpl<>(commonTableRelationshipMapper.toDto(dataList), pageable == null ? Pageable.unpaged() : pageable, totalItems);
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
