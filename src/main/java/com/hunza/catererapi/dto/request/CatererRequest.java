package com.hunza.catererapi.dto.request;



import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class CatererRequest {
    private String id;
    private String name;
    @Valid
    private LocationRequest location;
    @NotNull(message = "Capacity is required")
    @Valid
    private CapacityRequest capacity;
    @Valid
    private ContactRequest contact;
}
