package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.ApiPermissionDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApiPermission} and its DTO {@link ApiPermissionDTO}.
 */
@Mapper(componentModel = "spring", uses = { ApiPermissionSimpleMapper.class })
public interface ApiPermissionMapper extends EntityMapper<ApiPermissionDTO, ApiPermission> {
    @Mapping(target = "children", source = "children", qualifiedByName = "nameSet")
    @Mapping(target = "parent", source = "parent")
    ApiPermissionDTO toDto(ApiPermission apiPermission);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChildren", ignore = true)
    ApiPermission toEntity(ApiPermissionDTO apiPermissionDTO);

    @Named("nameSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LinkedHashSet<ApiPermissionDTO> toDtoNameSet(Set<ApiPermission> apiPermission);

    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ApiPermissionDTO toDtoName(ApiPermission apiPermission);
}
