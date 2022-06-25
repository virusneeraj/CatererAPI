package com.hunza.catererapi.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunza.catererapi.controller.CatererController;
import com.hunza.catererapi.dto.request.CatererRequest;
import com.hunza.catererapi.dto.response.APIResponse;
import com.hunza.catererapi.model.CatererDocument;
import com.hunza.catererapi.repository.CatererRepository;
import com.hunza.catererapi.utils.APIResponseUtil;
import com.hunza.catererapi.utils.HunzaConstant;
import com.hunza.catererapi.utils.HunzaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public APIResponse createCaterer(CatererRequest catererRequest){
        APIResponse apiResponse;
        try {
            Optional<CatererDocument> document = catererRepository.findFirstByName(catererRequest.getName());
            if(document.isPresent()){
               logger.info("Already exist with same name");
                apiResponse = apiResponseUtil.alreadyExist(new ArrayList<String>().add(catererRequest.getName()+" "+HunzaConstant.ALREADY_EXIST_MESSAGE));
            } else {
                CatererDocument catererDocument = convertIntoEntity(catererRequest);
                logger.info("catererDocument: {}", catererDocument);
                CatererDocument cc = catererRepository.save(catererDocument);
                apiResponse = apiResponseUtil.createdSuccessResponse(cc);
            }

        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("create catere error", e);
        }
        return apiResponse;
    }

    public APIResponse updateCaterer(CatererRequest catererRequest){
        APIResponse apiResponse;
        try {
            if(catererRequest.getId() == null || catererRequest.getId().isEmpty()){
                logger.warn("caterer id is missing: {}", catererRequest.getId());
                return apiResponseUtil.badRequest(HunzaConstant.BAD_REQUEST_MISSING_ID);
            }
            Optional<CatererDocument> ccOps = catererRepository.findById(catererRequest.getId());
            if(ccOps.isPresent()){
                logger.info("caterer found for: {}", catererRequest.getId());
                CatererDocument catererDocument = convertIntoEntity(catererRequest);
                CatererDocument cc = catererRepository.save(catererDocument);
                apiResponse = apiResponseUtil.successResponse(cc);
            } else {
                logger.warn("caterer not found for: {}", catererRequest.getId());
                apiResponse = apiResponseUtil.notFound(HunzaConstant.DATA_DOES_NOT_EXIST);
            }

        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("save catere error", e);
        }
        return apiResponse;
    }
    @Cacheable("search")
    public APIResponse searchCaterer(Pageable pageable){
        logger.info("loading data for: {}", pageable);
        APIResponse apiResponse;
        try {
            Page<CatererDocument> page = catererRepository.findAll(pageable);
            addSelfLinks(page);
            apiResponse = apiResponseUtil.successResponse(page);
        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("search catere error", e);
        }
        return apiResponse;
    }

    @CacheEvict(value="search", allEntries=true)
    public void searchCatererEvit(){
        logger.info("evicting search cache");
    }
    @Cacheable("nameorid")
    public APIResponse getByNameOrId(String nameorid) {
        logger.info("loading data for:  {}", nameorid);
        APIResponse apiResponse;
        try {
            Optional<CatererDocument> document = catererRepository.findFirstByNameOrId(nameorid, nameorid);
            if(document.isPresent()){
                addSelfLinks(document.get());
                apiResponse = apiResponseUtil.successResponse(document);
            } else {
                logger.info("data doest not exist for name or id: {}",nameorid);
                apiResponse = apiResponseUtil.notFound(HunzaConstant.DATA_DOES_NOT_EXIST);
            }

        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("search catere error", e);
        }
        return apiResponse;
    }
    @CacheEvict(value="nameorid", allEntries=true)
    public void getByANmeOrIdEvit(){
        logger.info("evicting name or id cache");
    }
    @Cacheable("bycity")
    public APIResponse getByCity(Pageable pageable, String city) {
        logger.info("loading data for: {}, {}", pageable, city);
        APIResponse apiResponse;
        try {
            Page<CatererDocument> page = catererRepository.findByLocation_City(city, pageable);
            addSelfLinks(page);
            apiResponse = apiResponseUtil.successResponse(page);
        } catch (Exception e){
            apiResponse = apiResponseUtil.failResponse(e.getMessage());
            logger.error("search catere error", e);
        }
        return apiResponse;
    }
    @CacheEvict(value="bycity", allEntries=true)
    public void getByCityEvit(){
        logger.info("evicting by city cache");
    }

    public APIResponse deleteCaterer(String nameOrId) {
        try {
            Optional<CatererDocument> ccOps = catererRepository.findFirstByNameOrId(nameOrId, nameOrId);
            if(ccOps.isPresent()){
                logger.info("caterer found for: {}", nameOrId);
                catererRepository.delete(ccOps.get());
                return apiResponseUtil.successResponse(HunzaConstant.SUCCESS_DELETE);
            } else {
                logger.warn("caterer not found for: {}", nameOrId);
                return apiResponseUtil.notFound(HunzaConstant.DATA_DOES_NOT_EXIST);
            }

        } catch (Exception e){
            logger.error("save catere error", e);
            return apiResponseUtil.failResponse(e.getMessage());
        }
    }

    private void addSelfLinks(Page<CatererDocument> page) throws Exception{
        page.getContent()
                .forEach(doc -> {
                            doc.add(linkTo(methodOn(CatererController.class).getByNameOrIdCaterer(doc.getId())).withSelfRel());
                            if(doc.getLocation() != null)
                                doc.getLocation().add(linkTo(methodOn(CatererController.class).getByCityCaterer("0","10","name",doc.getLocation().getCity())).withSelfRel());
                        }
                );
    }

    private void addSelfLinks(CatererDocument document) throws Exception{

        document.add(linkTo(methodOn(CatererController.class).getByNameOrIdCaterer(document.getId())).withSelfRel());
        if(document.getLocation() != null)
            document.getLocation().add(linkTo(methodOn(CatererController.class).getByCityCaterer("0","10","name",document.getLocation().getCity())).withSelfRel());

    }

    private CatererDocument convertIntoEntity(CatererRequest catererRequest){
        CatererDocument catererDocument = new CatererDocument();
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        catererDocument = mapper.convertValue(catererRequest, CatererDocument.class);

        // BeanUtils.copyProperties(catererRequest, catererDocument);
        return catererDocument;
    }

}
