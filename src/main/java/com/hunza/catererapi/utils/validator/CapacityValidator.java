package com.hunza.catererapi.utils.validator;

import com.hunza.catererapi.dto.request.CapacityRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapacityValidator implements ConstraintValidator<RangeCheck, CapacityRequest> {

    @Override
    public void initialize(RangeCheck constraint) {
    }

    @Override
    public boolean isValid(CapacityRequest form, ConstraintValidatorContext context) {
        if(form.getMinimum() == null || form.getMaximum() == null || form.getMinimum() < 1)
            return false;
        return form.getMaximum() >= form.getMinimum();
    }

}
