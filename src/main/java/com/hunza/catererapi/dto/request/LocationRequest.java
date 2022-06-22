package com.hunza.catererapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class LocationRequest {
    @Pattern(regexp="^[A-Za-z ]*$",message = "City must not contain digits or special characters")
    private String city;
    @NotNull
    private String street;
    private String postalCode;
}
