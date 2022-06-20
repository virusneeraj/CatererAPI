package com.hunza.catererapi;

import com.hunza.catererapi.utils.HunzaUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UtilTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HunzaUtil hunzaUtil;

    @Test
    void getPageableDefault() throws Exception {
        int page = 0;
        int size = 10;
        String sortWithOrder = "";
        Pageable pageable = hunzaUtil.getPageable(""+page,""+size,""+sortWithOrder);
        logger.info(pageable.toString());
         assertThat(pageable.getPageNumber()).isEqualTo(page);
        assertThat(pageable.getPageSize()).isEqualTo(size);
    }

    @Test
    void getPageableSort() throws Exception {
        int page = 0;
        int size = 10;
        String sortWithOrder = "name:desc";
        Pageable pageable = hunzaUtil.getPageable(""+page,""+size,""+sortWithOrder);
        logger.info(pageable.toString());
        assertThat(pageable.getPageNumber()).isEqualTo(page);
        assertThat(pageable.getPageSize()).isEqualTo(size);
        assertThat(pageable.getSort().isSorted()).isEqualTo(true);
    }
}
