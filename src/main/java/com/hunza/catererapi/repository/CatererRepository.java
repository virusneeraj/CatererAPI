package com.hunza.catererapi.repository;

import com.hunza.catererapi.model.CatererDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface CatererRepository extends MongoRepository<CatererDocument, String> {
    Optional<CatererDocument> findFirstByNameOrId(String name, String id);
    Optional<CatererDocument> findFirstByName(String name);
    Page<CatererDocument> findByLocation_City(String city, Pageable pageable);
}
