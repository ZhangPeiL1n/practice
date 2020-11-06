package com.zpl.practice.annotation;

import java.lang.annotation.Annotation;

/**
 * @author 张沛霖
 * @date 2020/11/6
 */
@AnnotationTest(name = "this is a name", value = "this is a value")
public class Test {

    public static void main(String[] args) {
        Annotation[] annotations = Test.class.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof AnnotationTest) {

                AnnotationTest annotationTest = (AnnotationTest) annotation;
                System.out.println(annotationTest.name());
                System.out.println(annotationTest.value());
            }

        }
        AnnotationTest annotation = Test.class.getAnnotation(AnnotationTest.class);

    }
}
