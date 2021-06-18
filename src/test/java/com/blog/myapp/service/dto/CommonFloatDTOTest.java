package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonFloatDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonFloatDTO.class);
        CommonFloatDTO commonFloatDTO1 = new CommonFloatDTO();
        commonFloatDTO1.setId(1L);
        CommonFloatDTO commonFloatDTO2 = new CommonFloatDTO();
        assertThat(commonFloatDTO1).isNotEqualTo(commonFloatDTO2);
        commonFloatDTO2.setId(commonFloatDTO1.getId());
        assertThat(commonFloatDTO1).isEqualTo(commonFloatDTO2);
        commonFloatDTO2.setId(2L);
        assertThat(commonFloatDTO1).isNotEqualTo(commonFloatDTO2);
        commonFloatDTO1.setId(null);
        assertThat(commonFloatDTO1).isNotEqualTo(commonFloatDTO2);
    }
}
