package Inference.Rules;

import Expression.ExpressionNode;
import Expression.ExpressionTree;

public class ModusTollens implements InferenceRule{

    @Override
    public boolean matches(ExpressionTree exp1, ExpressionTree exp2) {
        return  (exp1.getRoot().getOperator() == '>' &&
                    (exp1.getRoot().getRightExpression().isEqual(new ExpressionNode(exp2.getRoot(), '~')) ||
                        exp2.getRoot().isEqual(new ExpressionNode(exp1.getRoot().getRightExpression(), '~'))))
                ||
                (exp2.getRoot().getOperator() == '>' &&
                    (exp2.getRoot().getRightExpression().isEqual(new ExpressionNode(exp1.getRoot(), '~')) ||
                        exp1.getRoot().isEqual(new ExpressionNode(exp2.getRoot().getRightExpression(),'~'))));
    }

    @Override
    public ExpressionTree apply(ExpressionTree exp1, ExpressionTree exp2) throws Exception {
        if (exp1.getRoot().getOperator() == '>' &&
                (exp1.getRoot().getRightExpression().isEqual(new ExpressionNode(exp2.getRoot(), '~')) ||
                    exp2.getRoot().isEqual(new ExpressionNode(exp1.getRoot().getRightExpression(), '~')))){
            ExpressionNode negatedExpression = new ExpressionNode(exp1.getRoot().getLeftExpression(), '~');
            return new ExpressionTree(negatedExpression.getExpressionRepresentation());
        }

        ExpressionNode negatedExpression = new ExpressionNode(exp2.getRoot().getLeftExpression(), '~');
        return new ExpressionTree(negatedExpression.getExpressionRepresentation());
    }
}

