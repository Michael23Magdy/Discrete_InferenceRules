package Inference.Rules;

import Expression.ExpressionNode;
import Expression.ExpressionTree;

public class HypotheticalSyllogism implements InferenceRule{

    @Override
    public boolean matches(ExpressionTree exp1, ExpressionTree exp2) {
        return  exp1.getRoot().getOperator() == '>' && exp2.getRoot().getOperator() == '>' &&
                (exp1.getRoot().getRightExpression().isEqual(exp2.getRoot().getLeftExpression()) ||
                        exp2.getRoot().getRightExpression().isEqual(exp1.getRoot().getLeftExpression()));
    }

    @Override
    public ExpressionTree apply(ExpressionTree exp1, ExpressionTree exp2) throws Exception {
        if (exp1.getRoot().getOperator() == '>' && exp2.getRoot().getOperator() == '>' &&
                exp1.getRoot().getRightExpression().isEqual(exp2.getRoot().getLeftExpression())){
            ExpressionNode inferred = new ExpressionNode(exp1.getRoot().getLeftExpression(), exp2.getRoot().getRightExpression(), '>');
            return new ExpressionTree(inferred.getExpressionRepresentation());

        }
        ExpressionNode inferred = new ExpressionNode(exp2.getRoot().getLeftExpression(), exp1.getRoot().getRightExpression(), '>');
        return new ExpressionTree(inferred.getExpressionRepresentation());

    }
}
