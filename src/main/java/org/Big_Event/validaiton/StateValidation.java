package org.Big_Event.validaiton;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.Big_Event.anno.State;

public class StateValidation implements ConstraintValidator<State, String> {


    @Override
    public void initialize(State constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     *
     * @param value 将来要校验的值
     * @param constraintValidatorContext
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if(value == null){
            return false;
        }
        if(value.equals("已发布") || value.equals("草稿"))
        {
            return true;
        }
        return false;
    }
}
