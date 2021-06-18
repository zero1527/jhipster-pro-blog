package com.blog.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonStringMapperTest {

    private CommonStringMapper commonStringMapper;

    @BeforeEach
    public void setUp() {
        commonStringMapper = new CommonStringMapperImpl();
    }
}
