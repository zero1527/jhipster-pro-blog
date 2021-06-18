package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonIntegerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonIntegerDTO.class);
        CommonIntegerDTO commonIntegerDTO1 = new CommonIntegerDTO();
        commonIntegerDTO1.setId(1L);
        CommonIntegerDTO commonIntegerDTO2 = new CommonIntegerDTO();
        assertThat(commonIntegerDTO1).isNotEqualTo(commonIntegerDTO2);
        commonIntegerDTO2.setId(commonIntegerDTO1.getId());
        assertThat(commonIntegerDTO1).isEqualTo(commonIntegerDTO2);
        commonIntegerDTO2.setId(2L);
        assertThat(commonIntegerDTO1).isNotEqualTo(commonIntegerDTO2);
        commonIntegerDTO1.setId(null);
        assertThat(commonIntegerDTO1).isNotEqualTo(commonIntegerDTO2);
    }
}
