package com.blog.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonIntegerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonInteger.class);
        CommonInteger commonInteger1 = new CommonInteger();
        commonInteger1.setId(1L);
        CommonInteger commonInteger2 = new CommonInteger();
        commonInteger2.setId(commonInteger1.getId());
        assertThat(commonInteger1).isEqualTo(commonInteger2);
        commonInteger2.setId(2L);
        assertThat(commonInteger1).isNotEqualTo(commonInteger2);
        commonInteger1.setId(null);
        assertThat(commonInteger1).isNotEqualTo(commonInteger2);
    }
}
