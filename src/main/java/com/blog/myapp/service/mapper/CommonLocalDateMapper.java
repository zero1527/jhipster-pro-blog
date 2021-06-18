package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonLocalDateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonLocalDate} and its DTO {@link CommonLocalDateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonLocalDateMapper extends EntityMapper<CommonLocalDateDTO, CommonLocalDate> {}
