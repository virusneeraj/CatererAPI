package com.hunza.catererapi.dto.request;

import com.hunza.catererapi.utils.validator.RangeCheck;
import lombok.Data;

@Data
@RangeCheck(message = "Minimum and Maximum are required. Minimum and Maximum should positive number. Maximum can not smaller then minimum")
public class CapacityRequest {
    private Integer minimum;
    private Integer maximum;
}
