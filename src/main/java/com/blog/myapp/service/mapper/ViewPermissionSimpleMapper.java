package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.ViewPermission;
import com.blog.myapp.service.dto.ViewPermissionSimpleDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ViewPermissionSimpleMapper extends EntityMapper<ViewPermissionSimpleDTO, ViewPermission> {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "text", source = "text")
    ViewPermissionSimpleDTO toDto(ViewPermission viewPermission);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "text", source = "text")
    ViewPermission toEntity(ViewPermissionSimpleDTO viewPermissionSimpleDTO);
}
