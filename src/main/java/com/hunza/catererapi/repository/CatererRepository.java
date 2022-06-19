package com.hunza.catererapi.repository;

import com.hunza.catererapi.model.CatererDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CatererRepository extends MongoRepository<CatererDocument, String> {
    Page<CatererDocument> findByNameOrId(String name, String id, Pageable pageable);
    Page<CatererDocument> findByLocation_City(String city, Pageable pageable);
}
