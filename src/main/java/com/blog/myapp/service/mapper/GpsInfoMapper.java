package com.blog.myapp.service.mapper;

import com.blog.myapp.domain.*;
import com.blog.myapp.service.dto.GpsInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GpsInfo} and its DTO {@link GpsInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GpsInfoMapper extends EntityMapper<GpsInfoDTO, GpsInfo> {}
