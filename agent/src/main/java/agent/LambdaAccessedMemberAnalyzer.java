package agent;

import expr.ArgumentExpression;
import expr.ArrayLengthExpression;
import expr.ArrayLoadExpression;
import expr.BinaryArithmeticExpression;
import expr.CapturedArgExpression;
import expr.CastExpression;
import expr.ConstExpression;
import expr.Expression;
import expr.ExpressionVisitor;
import expr.GetFieldExpression;
import expr.InstanceOfExpression;
import expr.MethodInvocationExpression;
import expr.NewArrayExpression;
import expr.NewExpression;
import expr.ReturnAddressExpression;
import expr.ThisExpression;
import expr.UnaryExpression;
import expr.UnknownExpression;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class LambdaAccessedMemberAnalyzer {

    public static LambdaStatic.LambdaAccessedMemberInfo analyze(LambdaStatic lambda) {
        return analyze(lambda.expression);
    }

    public static LambdaStatic.LambdaAccessedMemberInfo analyze(Expression expression) {
        if (expression == null)
            return null;

        return expression.accept(new CastRemover()).accept(new AccessedMemberExtractor());
    }

    private static class CastRemover extends IdentityExpressionVisitor {
        static private Set<Method> boxingMethods = new HashSet<>();
        static private Set<Method> unboxingMethods = new HashSet<>();
        static {
            try {
                boxingMethods.add(Long.class.getMethod("valueOf", Long.TYPE));
                boxingMethods.add(Integer.class.getMethod("valueOf", Integer.TYPE));
                boxingMethods.add(Short.class.getMethod("valueOf", Short.TYPE));
                boxingMethods.add(Byte.class.getMethod("valueOf", Byte.TYPE));
                boxingMethods.add(Character.class.getMethod("valueOf", Character.TYPE));
                boxingMethods.add(Double.class.getMethod("valueOf", Double.TYPE));
                boxingMethods.add(Float.class.getMethod("valueOf", Float.TYPE));

                unboxingMethods.add(Long.class.getMethod("longValue"));
                unboxingMethods.add(Integer.class.getMethod("intValue"));
                unboxingMethods.add(Short.class.getMethod("shortValue"));
                unboxingMethods.add(Byte.class.getMethod("byteValue"));
                unboxingMethods.add(Character.class.getMethod("charValue"));
                unboxingMethods.add(Double.class.getMethod("doubleValue"));
                unboxingMethods.add(Float.class.getMethod("floatValue"));
            } catch (NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Expression visit(MethodInvocationExpression expr) {
            if (boxingMethods.contains(expr.method))
                return expr.args.get(0);
            else if (unboxingMethods.contains(expr.method))
                return expr.target;
            else
                return expr;
        }

        @Override
        public Expression visit(CastExpression expr) {
            return expr.expr;
        }
    }

    private static class AccessedMemberExtractor implements ExpressionVisitor<LambdaStatic.LambdaAccessedMemberInfo> {

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(MethodInvocationExpression expr) {
            LambdaStatic.LambdaAccessedMemberInfo info = new LambdaStatic.LambdaAccessedMemberInfo();
            info.member = expr.method;
            info.expr = expr;
            info.base = expr.target;
            return info;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(GetFieldExpression expr) {
            LambdaStatic.LambdaAccessedMemberInfo info = new LambdaStatic.LambdaAccessedMemberInfo();
            info.member = expr.field;
            info.base = expr.target;
            info.expr = expr;
            return info;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(ConstExpression constExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(ReturnAddressExpression returnAddressExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(NewExpression newExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(ArgumentExpression argumentExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(CapturedArgExpression capturedArgExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(UnaryExpression unaryExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(NewArrayExpression newArrayExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(ArrayLengthExpression arrayLengthExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(CastExpression castExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(InstanceOfExpression instanceOfExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(ThisExpression thisExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(ArrayLoadExpression arrayLoadExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(BinaryArithmeticExpression binaryArithmeticExpression) {
            return null;
        }

        @Override
        public LambdaStatic.LambdaAccessedMemberInfo visit(UnknownExpression unknownExpression) {
            return null;
        }
    }

}
