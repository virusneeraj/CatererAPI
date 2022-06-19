package com.hunza.catererapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class HunzaUtil {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public Pageable getPageable(String page, String size, String sortWithOrder) {

        Pageable pageable = PageRequest.of(0, 10);
        try {
            int p = 0, s = 10;

            if (page != null && !page.isEmpty())
                p = Integer.parseInt(page);
            if (size != null && !size.isEmpty())
                s = Integer.parseInt(size);

            if (p > 0)
                pageable = PageRequest.of(p, 10);

            if (s > 0)
                pageable = PageRequest.of(0, s);

            if (p > 0 && s > 0)
                pageable = PageRequest.of(p, s);

            if (sortWithOrder != null && !sortWithOrder.isEmpty()) {
                if (sortWithOrder.contains(",")) {
                    if ("DESC".equalsIgnoreCase(sortWithOrder.split(",")[1].trim()))
                        pageable = PageRequest.of(p, s, Sort.Direction.DESC, sortWithOrder.split(",")[0].trim());
                    else
                        pageable = PageRequest.of(p, s, Sort.Direction.ASC, sortWithOrder.split(",")[0].trim());
                } else
                    pageable = PageRequest.of(p, s, Sort.Direction.ASC, sortWithOrder);
            }
        } catch (Exception e) {
            logger.warn("invalid page pram requested");
        }
        return pageable;
    }

}
