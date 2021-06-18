package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonIntegerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonInteger} and its DTO {@link CommonIntegerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonIntegerMapper extends EntityMapper<CommonIntegerDTO, CommonInteger> {}
