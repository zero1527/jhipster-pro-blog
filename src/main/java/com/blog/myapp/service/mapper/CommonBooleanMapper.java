package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonBooleanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonBoolean} and its DTO {@link CommonBooleanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonBooleanMapper extends EntityMapper<CommonBooleanDTO, CommonBoolean> {}
