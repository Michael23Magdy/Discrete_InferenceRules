import Expression.ExpressionTree;
import LogicalExpressionSolver.LogicalExpressionSolver;
import LogicalExpressionSolver.Evaluator;

import java.util.*;

public class LogicalExpressionSolverController {
    public void runSolver(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a logical expression: ");
        String expr = scanner.nextLine();
        try{
            ExpressionTree expression = new ExpressionTree(expr);
            Set<Character> variables = extractVariables(expression.getRepresentation());
            Map<Character, Boolean> valueOfVariables = new HashMap<>();
            for (char var : variables) {
                while (true) {
                    System.out.print("Enter value for " + var + " (true/false): ");
                    if (scanner.hasNextBoolean()) {
                        valueOfVariables.put(var, scanner.nextBoolean());
                        break;
                    } else {
                        System.out.println("Invalid input.");
                        scanner.next();
                    }
                }
            }
            expression.setVariableValues(valueOfVariables);
            LogicalExpressionSolver solver = new Evaluator();
            boolean result = solver.evaluateExpression(expression);
            System.out.println("The result of the expression is: " + result);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

    private static Set<Character> extractVariables(String expression) {
        Set<Character> variables = new HashSet<>();
        for (char ch : expression.toCharArray()) {
            if (Character.isLetter(ch) && ch != 'v') {
                variables.add(ch);
            }
        }
        return variables;
    }
}
