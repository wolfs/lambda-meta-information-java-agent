import java.lang.instrument.UnmodifiableClassException;

public class ObtainByteCode {

    public static byte[] getByteCodeForClass(Class<?> clazz) throws UnmodifiableClassException {
        return LambdaByteCodeCapturer.getClassFile(clazz);
    }
}
