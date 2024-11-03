package Expression;

import java.util.Map;
import java.util.Stack;

public class ExpressionTree implements Expression {
    private ExpressionNode root;
    private String representation;
    private String postfixRepresentation;
    private Map<Character, Boolean> variableValues;

    public ExpressionNode getRoot() {
        return root;
    }
    public void setRoot(ExpressionNode root) {
        this.root = root;
    }

    public Map<Character, Boolean> getVariableValues() {
        return variableValues;
    }
    public void setVariableValues(Map<Character, Boolean> variableValues) {
        this.variableValues = variableValues;
    }

    public String getRepresentation() {
        return representation;
    }
    public String getPostfixRepresentation() {
        return postfixRepresentation;
    }

    public void setRepresentation(String representation) throws Exception {
        if(!Validation.isValid(representation)) throw new Exception("Invalid Input");
        this.representation = representation;
        this.postfixRepresentation = InfixToPostfix.convert(representation);
        Stack<ExpressionNode> st = new Stack<>();
        for (char c: postfixRepresentation.toCharArray()){
            if(Character.isLetter(c) & c!='v'){
                st.push(new ExpressionNode(c));
            } else if(c == '~'){
                ExpressionNode exp1 = st.pop();
                st.push(new ExpressionNode(exp1, c));
            } else {
                ExpressionNode exp1 = st.pop();
                ExpressionNode exp2 = st.pop();
                st.push(new ExpressionNode(exp2,exp1,c));
            }
        }
        this.root = st.pop();
    }

    public ExpressionTree(String expression) throws Exception{
        setRepresentation(expression);
    }
}
