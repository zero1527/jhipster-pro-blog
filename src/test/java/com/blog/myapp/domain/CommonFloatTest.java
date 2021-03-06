package com.blog.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonFloatTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonFloat.class);
        CommonFloat commonFloat1 = new CommonFloat();
        commonFloat1.setId(1L);
        CommonFloat commonFloat2 = new CommonFloat();
        commonFloat2.setId(commonFloat1.getId());
        assertThat(commonFloat1).isEqualTo(commonFloat2);
        commonFloat2.setId(2L);
        assertThat(commonFloat1).isNotEqualTo(commonFloat2);
        commonFloat1.setId(null);
        assertThat(commonFloat1).isNotEqualTo(commonFloat2);
    }
}
