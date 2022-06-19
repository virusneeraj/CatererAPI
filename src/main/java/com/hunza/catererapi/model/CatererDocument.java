package com.hunza.catererapi.model;

import com.hunza.catererapi.utils.validator.RangeCheck;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Document(collection = "caterer")
@Getter
@Setter
public class CatererDocument {
    @Id
    private String id;
    private String name;
    @Valid
    private Location location;
    @NotNull(message = "Capacity is required")
    @Valid
    private Capacity capacity;
    @Valid
    private Contact contact;
}


