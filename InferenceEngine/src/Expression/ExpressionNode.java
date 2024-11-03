package Expression;

public class ExpressionNode {
    private ExpressionNode leftExpression;
    private ExpressionNode rightExpression;
    private Character operator;

    public ExpressionNode(Character operator) {
        this.operator = operator;
    }
    public ExpressionNode(ExpressionNode leftExpression, Character operator) {
        this.leftExpression = leftExpression;
        this.operator = operator;
    }
    public ExpressionNode(ExpressionNode leftExpression, ExpressionNode rightExpression, Character operator) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    public ExpressionNode getLeftExpression() {
        return leftExpression;
    }
    public ExpressionNode getRightExpression() {
        return rightExpression;
    }
    public Character getOperator() {
        return operator;
    }

    public boolean isEqual(ExpressionNode expressionNode){
        if(this.operator != expressionNode.getOperator())
            return false;
        return  this.leftExpression.isEqual(expressionNode.getLeftExpression()) &
                this.rightExpression.isEqual(expressionNode.getRightExpression());

    }


}
