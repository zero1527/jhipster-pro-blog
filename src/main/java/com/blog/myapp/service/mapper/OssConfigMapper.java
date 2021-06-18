package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.OssConfigDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OssConfig} and its DTO {@link OssConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OssConfigMapper extends EntityMapper<OssConfigDTO, OssConfig> {}
