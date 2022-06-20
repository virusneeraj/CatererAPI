package com.hunza.catererapi;

import com.hunza.catererapi.service.CatererService;
import com.hunza.catererapi.utils.APIResponseUtil;
import com.hunza.catererapi.utils.HunzaConstant;
import com.hunza.catererapi.utils.HunzaUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CatererServiceTest {
    @Mock
    CatererService catererService;

    @Autowired
    HunzaUtil hunzaUtil;

    @Autowired
    APIResponseUtil apiResponseUtil;


    @Test
    public void getAllEmployeesTest() {
        int page = 0;
        int size = 10;
        String sortWithOrder = "";
        Pageable pageable = hunzaUtil.getPageable(""+page,""+size,""+sortWithOrder);

        when(catererService.searchCaterer(pageable)).thenReturn(apiResponseUtil.successResponse(HunzaConstant.SUCCESS_MESSAGE));

       assertThat(catererService.searchCaterer(pageable)).isEqualTo(apiResponseUtil.successResponse(HunzaConstant.SUCCESS_MESSAGE));
    }
}
