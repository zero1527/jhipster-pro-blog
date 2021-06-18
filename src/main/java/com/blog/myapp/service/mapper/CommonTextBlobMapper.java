package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.CommonTextBlobDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonTextBlob} and its DTO {@link CommonTextBlobDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonTextBlobMapper extends EntityMapper<CommonTextBlobDTO, CommonTextBlob> {}
