package Inference.Rules;

import Expression.ExpressionNode;
import Expression.ExpressionTree;

public class DisjunctiveSyllogism implements InferenceRule{

    @Override
    public boolean matches(ExpressionTree exp1, ExpressionTree exp2) {
        return  (exp1.getRoot().getOperator() == 'v' &&
                (exp1.getRoot().getRightExpression().isEqual(new ExpressionNode(exp2.getRoot(), '~')) ||
                        exp2.getRoot().isEqual(new ExpressionNode(exp1.getRoot().getRightExpression(), '~')) ||
                        exp1.getRoot().getLeftExpression().isEqual(new ExpressionNode(exp2.getRoot(), '~')) ||
                        exp2.getRoot().isEqual(new ExpressionNode(exp1.getRoot().getLeftExpression(), '~'))
                        ))
                ||
                (exp2.getRoot().getOperator() == 'v' &&
                (exp2.getRoot().getRightExpression().isEqual(new ExpressionNode(exp1.getRoot(), '~')) ||
                        exp1.getRoot().isEqual(new ExpressionNode(exp2.getRoot().getRightExpression(), '~')) ||
                        exp2.getRoot().getLeftExpression().isEqual(new ExpressionNode(exp1.getRoot(), '~')) ||
                        exp1.getRoot().isEqual(new ExpressionNode(exp2.getRoot().getLeftExpression(), '~'))
                        ));
    }

    @Override
    public ExpressionTree apply(ExpressionTree exp1, ExpressionTree exp2) throws Exception {
        if (exp1.getRoot().getOperator() == 'v'){
            if(exp1.getRoot().getRightExpression().isEqual(new ExpressionNode(exp2.getRoot(), '~')) ||
                    exp2.getRoot().isEqual(new ExpressionNode(exp1.getRoot().getRightExpression(), '~')))
                return new ExpressionTree(exp1.getRoot().getLeftExpression().getExpressionRepresentation());
            return new ExpressionTree(exp1.getRoot().getRightExpression().getExpressionRepresentation());

        }

        if(exp2.getRoot().getRightExpression().isEqual(new ExpressionNode(exp1.getRoot(), '~')) ||
                exp1.getRoot().isEqual(new ExpressionNode(exp2.getRoot().getRightExpression(), '~')))
            return new ExpressionTree(exp2.getRoot().getLeftExpression().getExpressionRepresentation());
        return new ExpressionTree(exp2.getRoot().getRightExpression().getExpressionRepresentation());
    }
}

