package Inference.Rules;

import Expression.ExpressionNode;
import Expression.ExpressionTree;

public class Resolution implements InferenceRule{

    @Override
    public boolean matches(ExpressionTree exp1, ExpressionTree exp2) {
        return  exp1.getRoot().getOperator() == 'v' && exp2.getRoot().getOperator() == 'v' && (
                    exp1.getRoot().getLeftExpression().isNegativeOf(exp2.getRoot().getLeftExpression())
                    || exp1.getRoot().getLeftExpression().isNegativeOf(exp2.getRoot().getRightExpression())
                    || exp1.getRoot().getRightExpression().isNegativeOf(exp2.getRoot().getLeftExpression())
                    || exp1.getRoot().getRightExpression().isNegativeOf(exp2.getRoot().getRightExpression())
                );
    }

    @Override
    public ExpressionTree apply(ExpressionTree exp1, ExpressionTree exp2) throws Exception {
        if(exp1.getRoot().getOperator() == 'v' && exp2.getRoot().getOperator() == 'v' &&
                exp1.getRoot().getLeftExpression().isNegativeOf(exp2.getRoot().getLeftExpression())){
            ExpressionNode inferred = new ExpressionNode(exp1.getRoot().getRightExpression(),exp2.getRoot().getRightExpression(),'v');
            return new ExpressionTree(inferred.getExpressionRepresentation());
        } else if(exp1.getRoot().getOperator() == 'v' && exp2.getRoot().getOperator() == 'v' &&
                exp1.getRoot().getLeftExpression().isNegativeOf(exp2.getRoot().getRightExpression())){
            ExpressionNode inferred = new ExpressionNode(exp1.getRoot().getRightExpression(),exp2.getRoot().getLeftExpression(),'v');
            return new ExpressionTree(inferred.getExpressionRepresentation());
        } else if(exp1.getRoot().getOperator() == 'v' && exp2.getRoot().getOperator() == 'v' &&
                exp1.getRoot().getRightExpression().isNegativeOf(exp2.getRoot().getLeftExpression())){
            ExpressionNode inferred = new ExpressionNode(exp1.getRoot().getLeftExpression(),exp2.getRoot().getRightExpression(),'v');
            return new ExpressionTree(inferred.getExpressionRepresentation());
        } else {
            ExpressionNode inferred = new ExpressionNode(exp1.getRoot().getLeftExpression(),exp2.getRoot().getLeftExpression(),'v');
            return new ExpressionTree(inferred.getExpressionRepresentation());
        }

    }
}

