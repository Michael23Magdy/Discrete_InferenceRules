import Expression.ExpressionTree;
import Inference.InferenceEngine;
import Inference.InferenceEngineImpl;
import Inference.Pair;
import Inference.Rules.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InferenceEngineController {
    public void runEngine(){
        try{
            System.out.println("---- INFERENCE ENGINE ----");
            List<InferenceRule> inferenceRules = Arrays.asList(
                    new ModusPonens(),
                    new ModusTollens(),
                    new HypotheticalSyllogism(),
                    new DisjunctiveSyllogism(),
                    new Resolution()
            );
            InferenceEngine inferenceEngine = new InferenceEngineImpl(inferenceRules);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter number of given expressions: ");
            int numberOfGivenExpression = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < numberOfGivenExpression; i++) {
                System.out.print((i+1) + "- ");
                String expressionOne = scanner.nextLine();
                ExpressionTree expressionTree1 = new ExpressionTree(expressionOne);
                inferenceEngine.addExpression(expressionTree1);
            }

            List<Pair<ExpressionTree, String >> results = inferenceEngine.applyRules();
            if(results.size()==numberOfGivenExpression) {
                System.out.println("Nothing Can Be Inferred");
            } else {
                for (int i = numberOfGivenExpression; i < results.size(); i++) {
                    System.out.print((i+1) + "- ");
                    System.out.println(results.get(i).first.getRepresentation()+" ("+results.get(i).second+')');
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
