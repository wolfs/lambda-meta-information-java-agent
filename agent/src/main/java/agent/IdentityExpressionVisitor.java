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

public class IdentityExpressionVisitor implements ExpressionVisitor<Expression> {

    @Override
    public Expression visit(MethodInvocationExpression expr) {
        return expr;
    }

    @Override
    public Expression visit(ConstExpression constExpression) {
        return constExpression;
    }

    @Override
    public Expression visit(ReturnAddressExpression returnAddressExpression) {
        return returnAddressExpression;
    }

    @Override
    public Expression visit(GetFieldExpression getFieldExpression) {
        return getFieldExpression;
    }

    @Override
    public Expression visit(NewExpression newExpression) {
        return newExpression;
    }

    @Override
    public Expression visit(ArgumentExpression argumentExpression) {
        return argumentExpression;
    }

    @Override
    public Expression visit(CapturedArgExpression capturedArgExpression) {
        return capturedArgExpression;
    }

    @Override
    public Expression visit(UnaryExpression unaryExpression) {
        return unaryExpression;
    }

    @Override
    public Expression visit(NewArrayExpression newArrayExpression) {
        return newArrayExpression;
    }

    @Override
    public Expression visit(ArrayLengthExpression arrayLengthExpression) {
        return arrayLengthExpression;
    }

    @Override
    public Expression visit(CastExpression castExpression) {
        return castExpression;
    }

    @Override
    public Expression visit(InstanceOfExpression instanceOfExpression) {
        return instanceOfExpression;
    }

    @Override
    public Expression visit(ThisExpression thisExpression) {
        return thisExpression;
    }

    @Override
    public Expression visit(ArrayLoadExpression arrayLoadExpression) {
        return arrayLoadExpression;
    }

    @Override
    public Expression visit(BinaryArithmeticExpression binaryArithmeticExpression) {
        return binaryArithmeticExpression;
    }

    @Override
    public Expression visit(UnknownExpression unknownExpression) {
        return unknownExpression;
    }

}
