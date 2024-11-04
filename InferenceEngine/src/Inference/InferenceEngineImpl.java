package Inference;

import Expression.ExpressionTree;
import Inference.Rules.InferenceRule;

import java.util.ArrayList;
import java.util.List;


public class InferenceEngineImpl  implements InferenceEngine{
    private List<InferenceRule> inferenceRules;
    private List<Pair<ExpressionTree, String>> expressionTrees = new ArrayList<>();

    public InferenceEngineImpl(List<InferenceRule> inferenceRules) {
        this.inferenceRules = inferenceRules;
    }

    @Override
    public void addRule(InferenceRule rule) {
        inferenceRules.add(rule);
    }

    public void clearRules(){
        inferenceRules.clear();
    }

    @Override
    public void addExpression(ExpressionTree exp) {
        expressionTrees.add(new Pair<>(exp, "given"));
    }

    private void addInferredExpression(ExpressionTree exp, String rule){
        expressionTrees.add(new Pair<>(exp, rule));
    }

    public void ClearExpressions(){
        expressionTrees.clear();
    }

    @Override
    public List<Pair<ExpressionTree, String>> applyRules() throws Exception{
        for (int i = 0; i < expressionTrees.size(); i++) {
            for (int j = 0; j < i; j++) {
                for (InferenceRule rule:inferenceRules){
                    if(rule.matches(expressionTrees.get(i).first,expressionTrees.get(j).first)){
                        ExpressionTree inferredExpressionTree = rule.apply(expressionTrees.get(i).first, expressionTrees.get(j).first);
                        boolean isPresent = false;
                        for (int k = 0; k < expressionTrees.size(); k++) {
                            isPresent = inferredExpressionTree.getRoot().isEqual(expressionTrees.get(k).first.getRoot());
                        }
                        if(!isPresent){
                            addInferredExpression(inferredExpressionTree, rule.getRuleName()+ " From " + (j+1) +"&" +(i+1));
                        }
                    }
                }
            }
        }
        return expressionTrees;
    }

}
