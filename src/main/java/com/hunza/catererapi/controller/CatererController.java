package com.hunza.catererapi.controller;

import com.hunza.catererapi.dto.response.APIResponse;
import com.hunza.catererapi.model.CatererDocument;
import com.hunza.catererapi.service.CacheManagerService;
import com.hunza.catererapi.service.CatererService;
import com.hunza.catererapi.utils.APIResponseUtil;
import com.hunza.catererapi.utils.HunzaConstant;
import com.hunza.catererapi.utils.HunzaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("caterer")
public class CatererController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    APIResponseUtil apiResponseUtil;

    @Autowired
    CatererService catererService;

    @Autowired
    HunzaUtil hunzaUtil;

    @Autowired
    CacheManagerService cacheManagerService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addCaterer(@Valid @RequestBody CatererDocument catererDocument){
        logger.info("add new catere request");
        catererDocument.setId(null);
        APIResponse apiResponse = catererService.createCaterer(catererDocument);
        if(apiResponse.getStatus().equals(HunzaConstant.CREATED_SUCCESS_STATUS))
            cacheManagerService.evictAllCatererCache();
        return apiResponseUtil.apiResponseToEntityResponse(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse> updateCaterer(@RequestBody CatererDocument catererDocument){
        logger.info("update catere request");
        APIResponse apiResponse = catererService.updateCaterer(catererDocument);
        if(apiResponse.getStatus().equals(HunzaConstant.SUCCESS_STATUS))
            cacheManagerService.evictAllCatererCache();
        return apiResponseUtil.apiResponseToEntityResponse(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse> searchCaterer(@RequestParam( name = "page", defaultValue = "0", required = false) String page,
                                                     @RequestParam( name = "size", defaultValue = "10", required = false) String size,
                                                     @RequestParam( name = "sort", required = false) String sortWithOrder,
                                                     @RequestParam( name = "search", required = false) String searchText){
        logger.info("sea5rch catere request");
        Pageable pageable = hunzaUtil.getPageable(page, size, sortWithOrder);
        APIResponse apiResponse = catererService.searchCaterer(pageable);
        return apiResponseUtil.apiResponseToEntityResponse(apiResponse);
    }

    @GetMapping("/serach/{byCity}")
    public ResponseEntity<APIResponse> getByCityCaterer(@RequestParam( name = "page", defaultValue = "0", required = false) String page,
                                                            @RequestParam( name = "size", defaultValue = "10", required = false) String size,
                                                            @RequestParam( name = "sort", required = false) String sortWithOrder,
                                                            @RequestParam( name = "search", required = false) String searchText,
                                                            @PathVariable("byCity") String nameorid){
        logger.info("sea5rch catere request");
        Pageable pageable = hunzaUtil.getPageable(page, size, sortWithOrder);
        APIResponse apiResponse = catererService.getByCity(pageable, nameorid);
        return apiResponseUtil.apiResponseToEntityResponse(apiResponse);
    }

    @GetMapping("/get/{byNameOrId}")
    public ResponseEntity<APIResponse> getByNameOrIdCaterer(@RequestParam( name = "page", defaultValue = "0", required = false) String page,
                                                     @RequestParam( name = "size", defaultValue = "10", required = false) String size,
                                                     @RequestParam( name = "sort", required = false) String sortWithOrder,
                                                     @RequestParam( name = "search", required = false) String searchText,
                                                    @PathVariable("byNameOrId") String nameorid){
        logger.info("sea5rch catere request");
        Pageable pageable = hunzaUtil.getPageable(page, size, sortWithOrder);
        APIResponse apiResponse = catererService.getByNameOrId(pageable, nameorid);
        return apiResponseUtil.apiResponseToEntityResponse(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteCaterer(@PathVariable("id") String id){
        logger.info("add new catere request");
        APIResponse apiResponse = catererService.deleteCaterer(id);
        if(apiResponse.getStatus().equals(HunzaConstant.CREATED_SUCCESS_STATUS))
            cacheManagerService.evictAllCatererCache();
        return apiResponseUtil.apiResponseToEntityResponse(apiResponse);
    }

}
