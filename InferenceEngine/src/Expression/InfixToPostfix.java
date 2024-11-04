package Expression;

import java.util.*;

public class InfixToPostfix {

    public static String convert(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if(c == ' '){
                continue;
            }else if (c == '(') {
                st.push(c);
            } else if (Character.isLetter(c) && c != 'v') {
                result.append(c);
            } else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    result.append(st.pop());
                }
                if (!st.isEmpty()) st.pop();
            } else {
                if(!st.isEmpty() && c=='~' && st.peek()=='~'){
                    st.pop();
                    continue;
                }
                while (!st.isEmpty() && Precedence.get(c) <= Precedence.get(st.peek())) {
                    result.append(st.pop());
                }
                st.push(c);
            }
        }
        while (!st.isEmpty()) {
            result.append(st.pop());
        }
        return result.toString();
    }
}