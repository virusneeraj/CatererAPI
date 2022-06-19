package com.hunza.catererapi.utils.validator;

import com.hunza.catererapi.model.Capacity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapacityValidator implements ConstraintValidator<RangeCheck, Capacity> {

    @Override
    public void initialize(RangeCheck constraint) {
    }

    @Override
    public boolean isValid(Capacity form, ConstraintValidatorContext context) {
        if(form.getMinimum() == null || form.getMaximum() == null || form.getMinimum() < 1)
            return false;
        return form.getMaximum() >= form.getMinimum();
    }

}
