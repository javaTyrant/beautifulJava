package strings;

/**
 * @author lumac
 * @since 2020/8/24
 */
public class AtoiExpression {
    public boolean validExpression(String expression) {
        //(a * b) + b
        boolean v = checkParentheses(expression);
        if (!v) return false;
        boolean e = checkExpression(expression);
        if (!e) return false;
        return true;
    }

    private boolean checkExpression(String expression) {
        return false;
    }

    private boolean checkParentheses(String expression) {
        return false;
    }
}
