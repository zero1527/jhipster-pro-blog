package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.ResourceCategoryDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceCategory} and its DTO {@link ResourceCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = { ResourceCategorySimpleMapper.class })
public interface ResourceCategoryMapper extends EntityMapper<ResourceCategoryDTO, ResourceCategory> {
    @Mapping(target = "children", source = "children", qualifiedByName = "titleSet")
    @Mapping(target = "parent", source = "parent")
    ResourceCategoryDTO toDto(ResourceCategory resourceCategory);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChildren", ignore = true)
    ResourceCategory toEntity(ResourceCategoryDTO resourceCategoryDTO);

    @Named("title")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    ResourceCategoryDTO toDtoTitle(ResourceCategory resourceCategory);

    @Named("titleSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    LinkedHashSet<ResourceCategoryDTO> toDtoTitleSet(Set<ResourceCategory> resourceCategory);
}
