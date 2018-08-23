import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class RegisterAgentTest {

    @Test
    void can_register_agent() throws Exception {
//        String agentJar = System.getProperty("agent.jar.path");
//        String name = ManagementFactory.getRuntimeMXBean().getName();
//        String pid = name.substring(0, name.indexOf('@'));
//
//        VirtualMachine vm = VirtualMachine.attach(pid);
//        vm.loadAgent(agentJar);
        byte[] byteCodeOfCurrentClass = ObtainByteCode.getByteCodeForClass(SomeBasicClass.class);
        assertThat(byteCodeOfCurrentClass).isNotEmpty();
    }

    @Test
    void can_obtain_bytecode_of_lambda() throws Exception {
        ClassWithProperty classWithProperty = new ClassWithProperty(System.out::println);

        Class<? extends Consumer> lambdaClass = classWithProperty.getConsumer().getClass();
        System.out.println(lambdaClass.getName());

        assertThat(lambdaClass).satisfies(Class::isSynthetic);
        assertThat(lambdaClass.getName()).contains("$$Lambda$");

        byte[] byteCodeForLambdaClass = ObtainByteCode.getByteCodeForClass(lambdaClass);
        assertThat(byteCodeForLambdaClass).isNotEmpty();
    }
}
