package com.hunza.catererapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class Location extends RepresentationModel<Location> {
    @Pattern(regexp="^[A-Za-z]*$",message = "City must not contain digits or special characters")
    private String city;
    @NotNull
    private String street;
    private String postalCode;

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
