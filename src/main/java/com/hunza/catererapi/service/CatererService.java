package com.hunza.catererapi.service;

import com.hunza.catererapi.dto.response.APIResponse;
import com.hunza.catererapi.model.CatererDocument;
import com.hunza.catererapi.repository.CatererRepository;
import com.hunza.catererapi.utils.APIResponseUtil;
import com.hunza.catererapi.utils.HunzaConstant;
import com.hunza.catererapi.utils.HunzaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatererService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CatererRepository catererRepository;

    @Autowired
    APIResponseUtil apiResponseUtil;

    @Autowired
    HunzaUtil hunzaUtil;


    public APIResponse createCaterer(CatererDocument catererDocument){
        APIResponse apiResponse;
        try {
            CatererDocument cc = catererRepository.save(catererDocument);
            apiResponse = apiResponseUtil.successResponse(cc);
        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("create catere error", e);
        }
        return apiResponse;
    }

    public APIResponse updateCaterer(CatererDocument catererDocument){
        APIResponse apiResponse;
        try {
            Optional<CatererDocument> ccOps = catererRepository.findById(catererDocument.getId());
            if(ccOps.isPresent()){
                logger.info("caterer found for: {}", catererDocument.getId());
                CatererDocument cc = catererRepository.save(catererDocument);
                apiResponse = apiResponseUtil.successResponse(cc);
            } else {
                logger.warn("caterer not found for: {}", catererDocument.getId());
                apiResponse = apiResponseUtil.notFound(HunzaConstant.DATA_DOES_NOT_EXIST);
            }

        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("save catere error", e);
        }
        return apiResponse;
    }

    public APIResponse searchCaterer(Pageable pageable){
        APIResponse apiResponse;
        try {
            Page<CatererDocument> page = catererRepository.findAll(pageable);
            apiResponse = apiResponseUtil.successResponse(page);
        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("search catere error", e);
        }
        return apiResponse;
    }

    public APIResponse getByNameOrId(Pageable pageable, String nameorid) {
        APIResponse apiResponse;
        try {
            Page<CatererDocument> page = catererRepository.findByNameOrId(nameorid, nameorid, pageable);
            apiResponse = apiResponseUtil.successResponse(page);
        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("search catere error", e);
        }
        return apiResponse;
    }

    public APIResponse getByCity(Pageable pageable, String city) {
        APIResponse apiResponse;
        try {
            Page<CatererDocument> page = catererRepository.findByLocation_City(city, pageable);
            apiResponse = apiResponseUtil.successResponse(page);
        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("search catere error", e);
        }
        return apiResponse;
    }
}
