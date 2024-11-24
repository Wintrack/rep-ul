package ca.ulaval.glo4003.repUL.api.config.authentication.annotation;

import ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType.ALL;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Protected {
    AuthorizationType value() default ALL;

}
