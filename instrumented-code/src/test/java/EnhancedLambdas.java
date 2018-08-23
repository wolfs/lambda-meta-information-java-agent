import static org.assertj.core.api.Assertions.assertThat;

import agent.LambdaInformation;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class EnhancedLambdas {

    @Test
    void lambdaIsEnhancedWithAnnotation() {
        ClassWithProperty classWithProperty = new ClassWithProperty((it) -> System.out.println(it));

        Class<? extends Consumer> lambdaClass = classWithProperty.getConsumer().getClass();
        System.out.println(lambdaClass.getName());

        assertThat(lambdaClass).satisfies(Class::isSynthetic);
        assertThat(lambdaClass.getName()).contains("$$Lambda$");
        assertThat(lambdaClass).hasAnnotation(LambdaInformation.class);
        LambdaInformation annotation = lambdaClass.getAnnotation(LambdaInformation.class);
        assertThat(annotation.implMethodClass()).isEqualTo(getClass());
        assertThat(annotation.implMethodName()).isEqualTo("lambda$lambdaIsEnhancedWithAnnotation$0");
    }
}
