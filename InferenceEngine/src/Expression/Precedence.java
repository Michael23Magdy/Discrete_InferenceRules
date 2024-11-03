package Expression;

public class Precedence {
    public static int get(char c) {
        return switch (c) {
            case '~' -> 4;
            case '^', 'v' -> 3;
            case '>' -> 1;
            default -> -1;
        };
    }
}
