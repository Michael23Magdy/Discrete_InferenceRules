import Expression.ExpressionTree;
import Inference.Rules.*;

public class Main {


    public static void main(String[] args) {
        try{
            ExpressionTree expressionTree1 = new ExpressionTree("~P");
            ExpressionTree expressionTree2 = new ExpressionTree("PvQ");

            InferenceRule modusPonens = new ModusPonens();
            if(modusPonens.matches(expressionTree1,expressionTree2)){
                ExpressionTree expressionTree = modusPonens.apply(expressionTree1,expressionTree2);
                System.out.println(expressionTree.getRepresentation());
            }

            InferenceRule modusTollens = new ModusTollens();
            if(modusTollens.matches(expressionTree1,expressionTree2)){
                ExpressionTree expressionTree = modusTollens.apply(expressionTree1,expressionTree2);
                System.out.println(expressionTree.getRepresentation());
            }

            InferenceRule hypotheticalSyllogism = new HypotheticalSyllogism();
            if(hypotheticalSyllogism.matches(expressionTree1,expressionTree2)){
                ExpressionTree expressionTree = hypotheticalSyllogism.apply(expressionTree1,expressionTree2);
                System.out.println(expressionTree.getRepresentation());
            }

            InferenceRule disjunctiveSyllogism = new DisjunctiveSyllogism();
            if(disjunctiveSyllogism.matches(expressionTree1,expressionTree2)){
                ExpressionTree expressionTree = disjunctiveSyllogism.apply(expressionTree1,expressionTree2);
                System.out.println(expressionTree.getRepresentation());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}