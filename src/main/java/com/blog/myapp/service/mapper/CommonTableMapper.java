package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonTableDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonTable} and its DTO {@link CommonTableDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {
        CommonTableFieldMapper.class,
        CommonTableRelationshipMapper.class,
        CommonTableSimpleMapper.class,
        UserMapper.class,
        BusinessTypeMapper.class,
    }
)
public interface CommonTableMapper extends EntityMapper<CommonTableDTO, CommonTable> {
    @Mapping(target = "commonTableFields", source = "commonTableFields", qualifiedByName = "titleSet")
    @Mapping(target = "relationships", source = "relationships", qualifiedByName = "nameSet")
    @Mapping(target = "metaModel", source = "metaModel")
    @Mapping(target = "creator", source = "creator", qualifiedByName = "login")
    @Mapping(target = "businessType", source = "businessType", qualifiedByName = "name")
    CommonTableDTO toDto(CommonTable commonTable);

    @Mapping(target = "commonTableFields", ignore = true)
    @Mapping(target = "removeCommonTableFields", ignore = true)
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "removeRelationships", ignore = true)
    CommonTable toEntity(CommonTableDTO commonTableDTO);

    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CommonTableDTO toDtoName(CommonTable commonTable);
}
