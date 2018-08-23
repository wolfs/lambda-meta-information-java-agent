import java.util.function.Consumer;

public class ClassWithProperty {
    private final Consumer<String> consumer;

    public ClassWithProperty(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    public Consumer<String> getConsumer() {
        return consumer;
    }
}
