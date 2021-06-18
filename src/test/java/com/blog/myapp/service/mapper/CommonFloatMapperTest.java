package com.blog.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonFloatMapperTest {

    private CommonFloatMapper commonFloatMapper;

    @BeforeEach
    public void setUp() {
        commonFloatMapper = new CommonFloatMapperImpl();
    }
}
