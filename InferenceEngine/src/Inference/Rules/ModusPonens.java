package Inference.Rules;

import Expression.ExpressionTree;

public class ModusPonens implements InferenceRule{
    public String getRuleName(){
        return "Modus Ponens";
    }

    @Override
    public boolean matches(ExpressionTree exp1, ExpressionTree exp2) {
        return  (exp1.getRoot().getOperator() == '>' &&
                        exp1.getRoot().getLeftExpression().isEqual(exp2.getRoot())) ||
                (exp2.getRoot().getOperator() == '>' &&
                        exp2.getRoot().getLeftExpression().isEqual(exp1.getRoot()));
    }

    @Override
    public ExpressionTree apply(ExpressionTree exp1, ExpressionTree exp2) throws Exception {
        if (exp1.getRoot().getOperator() == '>' && exp1.getRoot().getLeftExpression().isEqual(exp2.getRoot()))
            return new ExpressionTree(exp1.getRoot().getRightExpression().getExpressionRepresentation());

        return new ExpressionTree(exp2.getRoot().getRightExpression().getExpressionRepresentation());

    }
}
