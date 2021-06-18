package com.blog.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonBooleanMapperTest {

    private CommonBooleanMapper commonBooleanMapper;

    @BeforeEach
    public void setUp() {
        commonBooleanMapper = new CommonBooleanMapperImpl();
    }
}
