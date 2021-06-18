package com.blog.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonLocalDateMapperTest {

    private CommonLocalDateMapper commonLocalDateMapper;

    @BeforeEach
    public void setUp() {
        commonLocalDateMapper = new CommonLocalDateMapperImpl();
    }
}
