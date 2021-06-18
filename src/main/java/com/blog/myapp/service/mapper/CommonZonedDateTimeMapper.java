package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonZonedDateTimeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonZonedDateTime} and its DTO {@link CommonZonedDateTimeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonZonedDateTimeMapper extends EntityMapper<CommonZonedDateTimeDTO, CommonZonedDateTime> {}
