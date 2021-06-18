package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonExtDataDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonExtDataDTO.class);
        CommonExtDataDTO commonExtDataDTO1 = new CommonExtDataDTO();
        commonExtDataDTO1.setId(1L);
        CommonExtDataDTO commonExtDataDTO2 = new CommonExtDataDTO();
        assertThat(commonExtDataDTO1).isNotEqualTo(commonExtDataDTO2);
        commonExtDataDTO2.setId(commonExtDataDTO1.getId());
        assertThat(commonExtDataDTO1).isEqualTo(commonExtDataDTO2);
        commonExtDataDTO2.setId(2L);
        assertThat(commonExtDataDTO1).isNotEqualTo(commonExtDataDTO2);
        commonExtDataDTO1.setId(null);
        assertThat(commonExtDataDTO1).isNotEqualTo(commonExtDataDTO2);
    }
}
