package Inference.Rules;

import Expression.Expression;
import Expression.ExpressionTree;

public interface InferenceRule {
    boolean matches(ExpressionTree exp1, ExpressionTree exp2);
    ExpressionTree apply(ExpressionTree exp1, ExpressionTree exp2) throws Exception;
    String getRuleName();
}
