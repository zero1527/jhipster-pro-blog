package com.blog.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonTextBlobTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonTextBlob.class);
        CommonTextBlob commonTextBlob1 = new CommonTextBlob();
        commonTextBlob1.setId(1L);
        CommonTextBlob commonTextBlob2 = new CommonTextBlob();
        commonTextBlob2.setId(commonTextBlob1.getId());
        assertThat(commonTextBlob1).isEqualTo(commonTextBlob2);
        commonTextBlob2.setId(2L);
        assertThat(commonTextBlob1).isNotEqualTo(commonTextBlob2);
        commonTextBlob1.setId(null);
        assertThat(commonTextBlob1).isNotEqualTo(commonTextBlob2);
    }
}
