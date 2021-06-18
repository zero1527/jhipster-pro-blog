package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonStringDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonString} and its DTO {@link CommonStringDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonStringMapper extends EntityMapper<CommonStringDTO, CommonString> {}
