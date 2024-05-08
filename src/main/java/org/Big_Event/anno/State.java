package org.Big_Event.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.Big_Event.validaiton.StateValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author GranTHdxY
 **/
@Documented // 这是一个元注解
@Target({FIELD})//这是元注解
@Retention(RUNTIME)
@Constraint(validatedBy = { StateValidation.class})//指定提供校验规则的类
public @interface State {
    //提供校验码失败后的信息
    String message() default "state参数的值只能是已发布或者草稿";
    //指定分组
    Class<?>[] groups() default{ };
    //负载  获取到State注解的附加信息
    Class<? extends Payload>[] payload() default { };
}
