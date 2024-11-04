
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            LogicalExpressionSolverController logicalSolver = new LogicalExpressionSolverController();
            InferenceEngineController inferenceEngine = new InferenceEngineController();
            System.out.println("---- Application Menu ----");
            System.out.println("Choose an option:");
            System.out.println("1 - Logical Expression Solver");
            System.out.println("2 - Inference Engine");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    logicalSolver.runSolver();
                    break;
                case 2:
                    inferenceEngine.runEngine();
                    break;
                default:
                    System.out.println("Invalid input. Program ending.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Program ending.");
        }
    }
}