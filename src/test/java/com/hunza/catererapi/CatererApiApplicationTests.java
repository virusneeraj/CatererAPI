package com.hunza.catererapi;

import com.hunza.catererapi.controller.CatererController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CatererApiApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CatererController catererController;

    @Test
    void contextLoads() throws Exception {
        logger.info("Testing context load");
        assertThat(catererController).isNotNull();
    }

}
