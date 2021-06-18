package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.DepartmentSimpleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentSimpleDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartmentSimpleMapper extends EntityMapper<DepartmentSimpleDTO, Department> {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DepartmentSimpleDTO toDto(Department department);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Department toEntity(DepartmentSimpleDTO departmentDTO);
}
