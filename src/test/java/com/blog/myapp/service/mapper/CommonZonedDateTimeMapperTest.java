package com.blog.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonZonedDateTimeMapperTest {

    private CommonZonedDateTimeMapper commonZonedDateTimeMapper;

    @BeforeEach
    public void setUp() {
        commonZonedDateTimeMapper = new CommonZonedDateTimeMapperImpl();
    }
}
