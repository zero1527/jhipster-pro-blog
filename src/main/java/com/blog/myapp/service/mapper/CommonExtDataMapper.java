package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonExtDataDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonExtData} and its DTO {@link CommonExtDataDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonExtDataMapper extends EntityMapper<CommonExtDataDTO, CommonExtData> {}
