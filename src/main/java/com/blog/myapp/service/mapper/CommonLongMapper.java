package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonLongDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonLong} and its DTO {@link CommonLongDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonLongMapper extends EntityMapper<CommonLongDTO, CommonLong> {}
