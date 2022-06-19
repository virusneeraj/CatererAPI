package com.hunza.catererapi.model;

import com.hunza.catererapi.utils.validator.RangeCheck;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@RangeCheck(message = "Minimum and Maximum are required. Minimum and Maximum should positive number. Maximum can not smaller then minimum")
public class Capacity {
    private Integer minimum;
    private Integer maximum;
}
