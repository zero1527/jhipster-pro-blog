package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.AdministrativeDivisionDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdministrativeDivision} and its DTO {@link AdministrativeDivisionDTO}.
 */
@Mapper(componentModel = "spring", uses = { AdministrativeDivisionSimpleMapper.class })
public interface AdministrativeDivisionMapper extends EntityMapper<AdministrativeDivisionDTO, AdministrativeDivision> {
    @Mapping(target = "children", source = "children", qualifiedByName = "nameSet")
    @Mapping(target = "parent", source = "parent")
    AdministrativeDivisionDTO toDto(AdministrativeDivision administrativeDivision);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChildren", ignore = true)
    AdministrativeDivision toEntity(AdministrativeDivisionDTO administrativeDivisionDTO);

    @Named("nameSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LinkedHashSet<AdministrativeDivisionDTO> toDtoNameSet(Set<AdministrativeDivision> administrativeDivision);

    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    AdministrativeDivisionDTO toDtoName(AdministrativeDivision administrativeDivision);
}
