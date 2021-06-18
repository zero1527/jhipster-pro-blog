package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonBigDecimalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonBigDecimal} and its DTO {@link CommonBigDecimalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonBigDecimalMapper extends EntityMapper<CommonBigDecimalDTO, CommonBigDecimal> {}
