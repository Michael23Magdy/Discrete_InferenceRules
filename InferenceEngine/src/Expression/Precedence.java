package Expression;

public class Precedence {
    public static int get(char c) {
        return switch (c) {
            case '~' -> 4;
            case '^' -> 3;
            case 'v' -> 2;
            case '>' -> 1;
            default -> -1;
        };
    }
}
