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

import java.util.ArrayList;
import java.util.List;

public class DeepIdentityExpressionVisitor implements ExpressionVisitor<Expression> {

    @Override
    public Expression visit(MethodInvocationExpression expr) {
        Expression target = null;
        if (expr.target != null)
            target = expr.target.accept(this);

        List<Expression> args = new ArrayList<>();
        boolean argsDiffer = false;
        for (Expression arg : expr.args) {
            Expression tmp = arg.accept(this);
            argsDiffer |= tmp != arg;
            args.add(tmp);
        }

        if (target == expr.target && !argsDiffer)
            return expr;
        else
            return new MethodInvocationExpression(expr.method, target, args);
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
    public Expression visit(GetFieldExpression expr) {
        Expression target = null;
        if (expr.target != null)
            target = expr.target.accept(this);
        if (target == expr.target)
            return expr;
        else
            return new GetFieldExpression(target, expr.field);
    }

    @Override
    public Expression visit(NewExpression expr) {
        Expression target = null;
        if (expr.target != null)
            target = expr.target.accept(this);

        List<Expression> args = new ArrayList<>();
        boolean argsDiffer = false;
        for (Expression arg : expr.args) {
            Expression tmp = arg.accept(this);
            argsDiffer |= tmp != arg;
            args.add(tmp);
        }

        if (target == expr.target && !argsDiffer)
            return expr;
        else
            return new NewExpression(expr.constructor, target, args);
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
    public Expression visit(UnaryExpression expr) {
        Expression arg = expr.argument.accept(this);
        if (arg != expr.argument)
            return new UnaryExpression(expr.type, arg);
        else
            return expr;
    }

    @Override
    public Expression visit(NewArrayExpression expr) {
        Expression length = expr.length.accept(this);
        if (length != expr.length)
            return new NewArrayExpression(expr.type, length, expr.isMultiDimensional);
        else
            return expr;
    }

    @Override
    public Expression visit(ArrayLengthExpression expr) {
        Expression array = expr.array.accept(this);
        if (expr.array == array)
            return expr;
        else
            return new ArrayLengthExpression(array);
    }

    @Override
    public Expression visit(CastExpression expr) {
        Expression arg = expr.expr.accept(this);
        if (arg != expr.expr)
            return new CastExpression(expr.type, arg);
        else
            return expr;
    }

    @Override
    public Expression visit(InstanceOfExpression expr) {
        Expression arg = expr.expr.accept(this);
        if (arg != expr.expr)
            return new InstanceOfExpression(arg, expr.queryType);
        else
            return expr;
    }

    @Override
    public Expression visit(ThisExpression thisExpression) {
        return thisExpression;
    }

    @Override
    public Expression visit(ArrayLoadExpression expr) {
        Expression array = expr.array.accept(this);
        Expression index = expr.index.accept(this);
        if (array != expr.array || index != expr.index)
            return new ArrayLoadExpression(array, index);
        else
            return expr;
    }

    @Override
    public Expression visit(BinaryArithmeticExpression expr) {
        Expression exp1 = expr.exp1.accept(this);
        Expression exp2 = expr.exp2.accept(this);
        if (exp1 != expr.exp1 || exp2 != expr.exp2)
            return new BinaryArithmeticExpression(expr.op, exp1, exp2);
        else
            return expr;
    }

    @Override
    public Expression visit(UnknownExpression unknownExpression) {
        return unknownExpression;
    }

}
