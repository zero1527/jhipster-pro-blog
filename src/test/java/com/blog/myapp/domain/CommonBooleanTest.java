package com.blog.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonBooleanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonBoolean.class);
        CommonBoolean commonBoolean1 = new CommonBoolean();
        commonBoolean1.setId(1L);
        CommonBoolean commonBoolean2 = new CommonBoolean();
        commonBoolean2.setId(commonBoolean1.getId());
        assertThat(commonBoolean1).isEqualTo(commonBoolean2);
        commonBoolean2.setId(2L);
        assertThat(commonBoolean1).isNotEqualTo(commonBoolean2);
        commonBoolean1.setId(null);
        assertThat(commonBoolean1).isNotEqualTo(commonBoolean2);
    }
}
