package com.blog.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.blog.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonZonedDateTimeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommonZonedDateTimeDTO.class);
        CommonZonedDateTimeDTO commonZonedDateTimeDTO1 = new CommonZonedDateTimeDTO();
        commonZonedDateTimeDTO1.setId(1L);
        CommonZonedDateTimeDTO commonZonedDateTimeDTO2 = new CommonZonedDateTimeDTO();
        assertThat(commonZonedDateTimeDTO1).isNotEqualTo(commonZonedDateTimeDTO2);
        commonZonedDateTimeDTO2.setId(commonZonedDateTimeDTO1.getId());
        assertThat(commonZonedDateTimeDTO1).isEqualTo(commonZonedDateTimeDTO2);
        commonZonedDateTimeDTO2.setId(2L);
        assertThat(commonZonedDateTimeDTO1).isNotEqualTo(commonZonedDateTimeDTO2);
        commonZonedDateTimeDTO1.setId(null);
        assertThat(commonZonedDateTimeDTO1).isNotEqualTo(commonZonedDateTimeDTO2);
    }
}
