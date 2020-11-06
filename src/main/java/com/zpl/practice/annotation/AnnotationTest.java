package com.zpl.practice.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 张沛霖
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {

    String name() default "name";

    String value() default "value";


}
