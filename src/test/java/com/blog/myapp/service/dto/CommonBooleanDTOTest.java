package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonBooleanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonBooleanDTO.class);
        CommonBooleanDTO commonBooleanDTO1 = new CommonBooleanDTO();
        commonBooleanDTO1.setId(1L);
        CommonBooleanDTO commonBooleanDTO2 = new CommonBooleanDTO();
        assertThat(commonBooleanDTO1).isNotEqualTo(commonBooleanDTO2);
        commonBooleanDTO2.setId(commonBooleanDTO1.getId());
        assertThat(commonBooleanDTO1).isEqualTo(commonBooleanDTO2);
        commonBooleanDTO2.setId(2L);
        assertThat(commonBooleanDTO1).isNotEqualTo(commonBooleanDTO2);
        commonBooleanDTO1.setId(null);
        assertThat(commonBooleanDTO1).isNotEqualTo(commonBooleanDTO2);
    }
}
