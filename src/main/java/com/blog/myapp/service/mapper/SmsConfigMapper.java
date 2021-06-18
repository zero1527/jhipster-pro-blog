package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.SmsConfigDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SmsConfig} and its DTO {@link SmsConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SmsConfigMapper extends EntityMapper<SmsConfigDTO, SmsConfig> {}
