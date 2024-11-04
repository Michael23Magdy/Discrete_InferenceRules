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

    public boolean isNegativeOf(ExpressionNode expressionNode){
        return this.isEqual(new ExpressionNode(expressionNode, '~')) ||
                expressionNode.isEqual(new ExpressionNode(this,'~'));
    }

    public boolean isEqual(ExpressionNode expressionNode){
        if (this.operator != expressionNode.getOperator()) {
            return false;
        }

        // Check if both left expressions are null or both are non-null and equal
        boolean leftEqual = (this.leftExpression == null && expressionNode.getLeftExpression() == null) ||
                (this.leftExpression != null && expressionNode.getLeftExpression() != null &&
                        this.leftExpression.isEqual(expressionNode.getLeftExpression()));

        // Check if both right expressions are null or both are non-null and equal
        boolean rightEqual = (this.rightExpression == null && expressionNode.getRightExpression() == null) ||
                (this.rightExpression != null && expressionNode.getRightExpression() != null &&
                        this.rightExpression.isEqual(expressionNode.getRightExpression()));

        return leftEqual && rightEqual;
    }

    private static String getExpressionRepresentation(ExpressionNode exp){
        Character operation = exp.getOperator();
        if(Validation.isVariable(operation)){
            return ""+ operation;
        } else if(operation == '~') {
            String right = getExpressionRepresentation(exp.getLeftExpression());
            if(!(Validation.isVariable(exp.getLeftExpression().getOperator()) &&
                    Precedence.get(exp.getOperator())>Precedence.get(exp.getLeftExpression().getOperator())))
                right = '('+right+')';
            return '~'+right;
        } else {
            String left = getExpressionRepresentation(exp.getLeftExpression());
            if(!(Validation.isVariable(exp.getLeftExpression().getOperator()) &&
                    Precedence.get(exp.getOperator())>Precedence.get(exp.getLeftExpression().getOperator())))
                left = '('+left+')';

            String right =  getExpressionRepresentation(exp.getRightExpression());
            if(!(Validation.isVariable(exp.getRightExpression().getOperator()) &&
                    Precedence.get(exp.getOperator())>Precedence.get(exp.getRightExpression().getOperator())))
                right = '('+right+')';
            return left + operation + right;
        }
    }

    public String getExpressionRepresentation(){
        return getExpressionRepresentation(this);
    }
}
