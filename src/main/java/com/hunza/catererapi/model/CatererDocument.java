package com.hunza.catererapi.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Document(collection = "caterer")
@Data
public class CatererDocument extends RepresentationModel<CatererDocument> {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Location location;
    @NotNull(message = "Capacity is required")
    @Valid
    private Capacity capacity;
    @Valid
    private Contact contact;
    @Transient
    @Override
    public Links getLinks() {
        return super.getLinks();
    }
    @AccessType(AccessType.Type.PROPERTY)
    public void setLinks(List<Link> links) {
        super.removeLinks();
        super.add(links);
    }
}


