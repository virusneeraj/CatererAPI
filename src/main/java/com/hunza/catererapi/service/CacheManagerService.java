package com.hunza.catererapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheManagerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CatererService catererService;

    public void evictAllCatererCache(){
        logger.info("evicting all caterer cache");
        catererService.searchCatererEvit();
        catererService.getByCityEvit();
        catererService.getByANmeOrIdEvit();
    }
}
