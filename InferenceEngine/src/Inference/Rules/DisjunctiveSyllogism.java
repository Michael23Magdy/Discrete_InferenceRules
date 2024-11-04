package Inference.Rules;

import Expression.ExpressionNode;
import Expression.ExpressionTree;

public class DisjunctiveSyllogism implements InferenceRule{
    public String getRuleName(){
        return "Disjunctive Syllogism";
    }
    @Override
    public boolean matches(ExpressionTree exp1, ExpressionTree exp2) {
        return  (exp1.getRoot().getOperator() == 'v' &&
                (exp1.getRoot().getRightExpression().isNegativeOf(exp2.getRoot()) ||
                        exp1.getRoot().getLeftExpression().isNegativeOf(exp2.getRoot())
                ))
                ||
                (exp2.getRoot().getOperator() == 'v' &&
                (exp2.getRoot().getRightExpression().isNegativeOf(exp1.getRoot()) ||
                        exp2.getRoot().getLeftExpression().isNegativeOf(exp1.getRoot())
                ));
    }

    @Override
    public ExpressionTree apply(ExpressionTree exp1, ExpressionTree exp2) throws Exception {
        if (exp1.getRoot().getOperator() == 'v'){
            if(exp1.getRoot().getRightExpression().isNegativeOf((exp2.getRoot())))
                return new ExpressionTree(exp1.getRoot().getLeftExpression().getExpressionRepresentation());
            return new ExpressionTree(exp1.getRoot().getRightExpression().getExpressionRepresentation());

        }

        if(exp2.getRoot().getRightExpression().isNegativeOf(exp1.getRoot()))
            return new ExpressionTree(exp2.getRoot().getLeftExpression().getExpressionRepresentation());
        return new ExpressionTree(exp2.getRoot().getRightExpression().getExpressionRepresentation());
    }
}

