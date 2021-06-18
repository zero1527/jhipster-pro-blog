package com.blog.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonBigDecimalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonBigDecimal.class);
        CommonBigDecimal commonBigDecimal1 = new CommonBigDecimal();
        commonBigDecimal1.setId(1L);
        CommonBigDecimal commonBigDecimal2 = new CommonBigDecimal();
        commonBigDecimal2.setId(commonBigDecimal1.getId());
        assertThat(commonBigDecimal1).isEqualTo(commonBigDecimal2);
        commonBigDecimal2.setId(2L);
        assertThat(commonBigDecimal1).isNotEqualTo(commonBigDecimal2);
        commonBigDecimal1.setId(null);
        assertThat(commonBigDecimal1).isNotEqualTo(commonBigDecimal2);
    }
}
