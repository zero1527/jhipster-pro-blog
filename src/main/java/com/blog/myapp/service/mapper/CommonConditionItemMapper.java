package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonConditionItemDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonConditionItem} and its DTO {@link CommonConditionItemDTO}.
 */
@Mapper(componentModel = "spring", uses = { CommonConditionMapper.class })
public interface CommonConditionItemMapper extends EntityMapper<CommonConditionItemDTO, CommonConditionItem> {
    @Mapping(target = "commonCondition", source = "commonCondition", qualifiedByName = "name")
    CommonConditionItemDTO toDto(CommonConditionItem commonConditionItem);

    @Named("fieldNameSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "fieldName", source = "fieldName")
    LinkedHashSet<CommonConditionItemDTO> toDtoFieldNameSet(Set<CommonConditionItem> commonConditionItem);
}
