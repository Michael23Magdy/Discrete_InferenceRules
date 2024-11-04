package Inference;

import Expression.ExpressionTree;
import Inference.Rules.InferenceRule;

import java.util.List;

public interface InferenceEngine {
    void addRule(InferenceRule rule);
    void addExpression(ExpressionTree exp);
    List<Pair<ExpressionTree, String>> applyRules() throws Exception;
}
