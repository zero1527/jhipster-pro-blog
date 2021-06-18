package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.AdministrativeDivisionSimpleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdministrativeDivision} and its DTO {@link AdministrativeDivisionSimpleDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdministrativeDivisionSimpleMapper extends EntityMapper<AdministrativeDivisionSimpleDTO, AdministrativeDivision> {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    AdministrativeDivisionSimpleDTO toDto(AdministrativeDivision administrativeDivision);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    AdministrativeDivision toEntity(AdministrativeDivisionSimpleDTO administrativeDivisionDTO);
}
