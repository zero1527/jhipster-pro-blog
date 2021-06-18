package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonFloatDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonFloat} and its DTO {@link CommonFloatDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonFloatMapper extends EntityMapper<CommonFloatDTO, CommonFloat> {}
