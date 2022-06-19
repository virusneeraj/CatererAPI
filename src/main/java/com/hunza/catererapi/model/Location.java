package com.hunza.catererapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class Location {
    @Pattern(regexp="^[A-Za-z]*$",message = "City must not contain digits or special characters")
    private String city;
    @NotNull
    private String street;
    private String postalCode;
}
