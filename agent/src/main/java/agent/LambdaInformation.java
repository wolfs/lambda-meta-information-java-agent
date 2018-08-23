package agent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation added to lambda classes to provide information about the
 * implementation method of the lambda.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LambdaInformation {

    /**
     * Class defining the implementation method
     */
    Class<?> implMethodClass();

    /**
     * Name of the implementation method
     */
    String implMethodName();
}
