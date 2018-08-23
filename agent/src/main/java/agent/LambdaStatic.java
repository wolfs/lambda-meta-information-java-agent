package agent;

import expr.Expression;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class LambdaStatic {
    public static class LambdaAccessedMemberInfo {
        public Expression base;
        public Expression expr;

        public Member member;

        public Object getBase(Lambda lambda, Object... args) {
            return ExpressionEvaluator.evaluate(base, lambda, args);
        }
    }

    public Method implementationMethod;
    public Class<?>[] capturedTypes;
    public Class<?>[] argumentTypes;

    public Expression expression;
    public LambdaAccessedMemberInfo accessedMemberInfo;

}
