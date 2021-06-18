package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonStringDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonStringDTO.class);
        CommonStringDTO commonStringDTO1 = new CommonStringDTO();
        commonStringDTO1.setId(1L);
        CommonStringDTO commonStringDTO2 = new CommonStringDTO();
        assertThat(commonStringDTO1).isNotEqualTo(commonStringDTO2);
        commonStringDTO2.setId(commonStringDTO1.getId());
        assertThat(commonStringDTO1).isEqualTo(commonStringDTO2);
        commonStringDTO2.setId(2L);
        assertThat(commonStringDTO1).isNotEqualTo(commonStringDTO2);
        commonStringDTO1.setId(null);
        assertThat(commonStringDTO1).isNotEqualTo(commonStringDTO2);
    }
}
