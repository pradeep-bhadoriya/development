import java.util.*;

public class InfixExpression {
    // helper
    public static int priority(char optr) {
        if (optr == '/' || optr == '*')
            return 2;
        else if (optr == '+' || optr == '-')
            return 1;
        return 0;
    }

    public static int solve(int val1, int val2, char optr) {
        if (optr == '/') {
            // System.out.print("val1:" + val1 + " val2 : " + val2);

            // System.out.print("divide" + (val1 / val2));
            return val1 / val2;
        } else if (optr == '*') {
            // System.out.print("val1:" + val1 + " val2 : " + val2);
            // System.out.print("multi" + (val1 * val2));
            return val1 * val2;
        } else if (optr == '+') {
            // System.out.print("val1:" + val1 + " val2 : " + val2);
            // System.out.print("add" + (val1 + val2));
            return val1 + val2;
        } else if (optr == '-') {

            // System.out.print("val1:" + val1 + " val2 : " + val2);
            // System.out.print("minus" + (val1 - val2));
            return val1 - val2;
        } else
            return 0;
    }

    // infix
    // Scanner scn = new Scanner(System.in);

    public static int infixEvaluation(String str) {
        Stack<Integer> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                continue;
            } else if (operand.size() < 1) {
                operand.push(ch - '0');
                // System.out.print(ch - '0' + " ");

            } else if (operator.size() == 0 || priority(ch) > priority(operator.peek())) {
                operator.push(ch);
                // System.out.print(ch);
            } else if (ch == ')') {
                while (operator.peek() != '(' && operand.size() > 1 && operator.size() > 0) {
                    int val2 = operand.pop();
                    int val1 = operand.pop();
                    int tempres = solve(val1, val2, operator.pop());
                    // operator.pop();
                    // System.out.print("temp :" + tempres);
                    operand.push(tempres);
                    // System.out.print(operand.peek());
                }
                operator.pop();
            } else if (ch >= '0' && ch <= '9') {
                // System.out.print(ch - '0' + " ");
                operand.push(ch - '0');
            } else if (ch == '(') {
                operator.push(ch);
            } else {
                while (operand.size() > 1 && operator.size() > 0 && priority(ch) <= priority(operator.peek())) {
                    int val2 = operand.pop();
                    int val1 = operand.pop();
                    int tempres = solve(val1, val2, operator.peek());
                    // System.out.print("temp :" + tempres);

                    operator.pop();
                    operand.push(tempres);
                    // System.out.print(operand.peek());
                }
                operator.push(ch);
            }

        }
        while (operator.size() > 0 && operand.size() > 1) {
            int val2 = operand.pop();
            int val1 = operand.pop();
            int tempres = solve(val1, val2, operator.pop());
            // System.out.print("temp :" + tempres);

            // operator.pop();
            operand.push(tempres);
            // System.out.print(operand.peek());
        }
        return operand.pop();
    }

    // public static void infixToPrefix(String str) {

    // }

    // public static void infixToPostfix(String str) {

    // }

    // // prefix
    // public static int prefixEvaluation(String str) {

    // }

    // public static void prefixToInfix(String str) {

    // }

    // public static void prefixToPostfix(String str) {

    // }

    // // postfix
    // public static int postfixEvaluation(String str) {

    // }

    // public static void postfixToPrefix(String str) {

    // }

    // public static void postixToinfix(String str) {

    // }

    // public static void evaluation() {
    // String str = "";

    // }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // String exp = "2 + 4 / (5 - 3)* 3";
        // int ans = infixEvaluation(exp);
        // System.out.print(ans);
        int[] arr = { 1, 2, 3, 4, 5 };
        // int[] res = nextGreaterElements(arr);
        // System.out.print(Arrays.toString(res));
    }
}
