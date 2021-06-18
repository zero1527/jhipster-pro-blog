package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.DataDictionaryDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataDictionary} and its DTO {@link DataDictionaryDTO}.
 */
@Mapper(componentModel = "spring", uses = { DataDictionarySimpleMapper.class })
public interface DataDictionaryMapper extends EntityMapper<DataDictionaryDTO, DataDictionary> {
    @Mapping(target = "children", source = "children", qualifiedByName = "nameSet")
    @Mapping(target = "parent", source = "parent")
    DataDictionaryDTO toDto(DataDictionary dataDictionary);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChildren", ignore = true)
    DataDictionary toEntity(DataDictionaryDTO dataDictionaryDTO);

    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DataDictionaryDTO toDtoName(DataDictionary dataDictionary);

    @Named("nameSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LinkedHashSet<DataDictionaryDTO> toDtoNameSet(Set<DataDictionary> dataDictionary);
}
