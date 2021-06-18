package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.DepartmentDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentDTO}.
 */
@Mapper(componentModel = "spring", uses = { DepartmentSimpleMapper.class, AuthorityMapper.class })
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {
    @Mapping(target = "children", source = "children", qualifiedByName = "nameSet")
    @Mapping(target = "authorities", source = "authorities", qualifiedByName = "nameSet")
    @Mapping(target = "parent", source = "parent")
    DepartmentDTO toDto(Department department);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChildren", ignore = true)
    @Mapping(target = "removeAuthorities", ignore = true)
    Department toEntity(DepartmentDTO departmentDTO);

    @Named("nameSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LinkedHashSet<DepartmentDTO> toDtoNameSet(Set<Department> department);

    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DepartmentDTO toDtoName(Department department);
}
