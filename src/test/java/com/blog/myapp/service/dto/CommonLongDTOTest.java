package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonLongDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonLongDTO.class);
        CommonLongDTO commonLongDTO1 = new CommonLongDTO();
        commonLongDTO1.setId(1L);
        CommonLongDTO commonLongDTO2 = new CommonLongDTO();
        assertThat(commonLongDTO1).isNotEqualTo(commonLongDTO2);
        commonLongDTO2.setId(commonLongDTO1.getId());
        assertThat(commonLongDTO1).isEqualTo(commonLongDTO2);
        commonLongDTO2.setId(2L);
        assertThat(commonLongDTO1).isNotEqualTo(commonLongDTO2);
        commonLongDTO1.setId(null);
        assertThat(commonLongDTO1).isNotEqualTo(commonLongDTO2);
    }
}
