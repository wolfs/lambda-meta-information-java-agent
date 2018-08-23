package expr;

public class ThisExpression extends Expression {

    public ThisExpression(Class<?> thisType) {
        super(thisType);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "this";
    }
}
