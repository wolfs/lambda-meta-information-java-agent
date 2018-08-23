import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

public class LambdaByteCodeCapturer implements ClassFileTransformer {
    private static Instrumentation inst;

    private byte[] classFile;

    public static synchronized void agentmain(String args, Instrumentation inst) {
        LambdaByteCodeCapturer.inst = inst;
    }

    public static synchronized void premain(String args, Instrumentation inst) {
        LambdaByteCodeCapturer.inst = inst;
    }

    public static synchronized byte[] getClassFile(Class cls) throws UnmodifiableClassException {
        Instrumentation inst = LambdaByteCodeCapturer.inst;
        if (inst == null) {
            throw new IllegalStateException("Agent has not been loaded!");
        }

        LambdaByteCodeCapturer codeCapturer = new LambdaByteCodeCapturer();
        inst.addTransformer(codeCapturer, true);
        try {
            inst.retransformClasses(cls);
        } catch (UnmodifiableClassException e) {
            // ignore
        } finally {
            inst.removeTransformer(codeCapturer);
        }
        return codeCapturer.classFile;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] bytecode) throws IllegalClassFormatException {
        if (classBeingRedefined != null) {
            this.classFile = bytecode;
        }
        return null;
    }
}
