package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonLocalDateDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonLocalDateDTO.class);
        CommonLocalDateDTO commonLocalDateDTO1 = new CommonLocalDateDTO();
        commonLocalDateDTO1.setId(1L);
        CommonLocalDateDTO commonLocalDateDTO2 = new CommonLocalDateDTO();
        assertThat(commonLocalDateDTO1).isNotEqualTo(commonLocalDateDTO2);
        commonLocalDateDTO2.setId(commonLocalDateDTO1.getId());
        assertThat(commonLocalDateDTO1).isEqualTo(commonLocalDateDTO2);
        commonLocalDateDTO2.setId(2L);
        assertThat(commonLocalDateDTO1).isNotEqualTo(commonLocalDateDTO2);
        commonLocalDateDTO1.setId(null);
        assertThat(commonLocalDateDTO1).isNotEqualTo(commonLocalDateDTO2);
    }
}
