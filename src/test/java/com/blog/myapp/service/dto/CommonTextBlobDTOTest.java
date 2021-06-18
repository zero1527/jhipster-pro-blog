package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonTextBlobDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonTextBlobDTO.class);
        CommonTextBlobDTO commonTextBlobDTO1 = new CommonTextBlobDTO();
        commonTextBlobDTO1.setId(1L);
        CommonTextBlobDTO commonTextBlobDTO2 = new CommonTextBlobDTO();
        assertThat(commonTextBlobDTO1).isNotEqualTo(commonTextBlobDTO2);
        commonTextBlobDTO2.setId(commonTextBlobDTO1.getId());
        assertThat(commonTextBlobDTO1).isEqualTo(commonTextBlobDTO2);
        commonTextBlobDTO2.setId(2L);
        assertThat(commonTextBlobDTO1).isNotEqualTo(commonTextBlobDTO2);
        commonTextBlobDTO1.setId(null);
        assertThat(commonTextBlobDTO1).isNotEqualTo(commonTextBlobDTO2);
    }
}
