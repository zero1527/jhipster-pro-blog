package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonDoubleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonDouble} and its DTO {@link CommonDoubleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonDoubleMapper extends EntityMapper<CommonDoubleDTO, CommonDouble> {}
