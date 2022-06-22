package com.hunza.catererapi.model;

import com.hunza.catererapi.utils.validator.RangeCheck;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Capacity {
    private Integer minimum;
    private Integer maximum;
}
