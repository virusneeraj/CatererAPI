package com.hunza.catererapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Location {
    @Pattern(regexp="^[A-Za-z]*$",message = "City must not contain digits or special characters")
    private String city;
    @NotNull
    private String street;
    private String postalCode;
}
