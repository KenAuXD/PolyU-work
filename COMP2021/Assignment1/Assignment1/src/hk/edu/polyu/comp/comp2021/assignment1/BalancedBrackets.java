package hk.edu.polyu.comp.comp2021.assignment1;
import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str){
        // Task 7: Return true if and only if 'str' 1) is non-empty,
        // 2) contains only the six characters, and
        // 3) is balanced.
        Stack<Character> stack = new Stack<Character>();
        char k;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') stack.push(str.charAt(i));
            if (str.charAt(i) == ')') {
                if (stack.isEmpty()) return false;
                k = stack.pop();
                if (k != '(') return false;
            }
            if (str.charAt(i) == ']') {
                if (stack.isEmpty()) return false;
                k = stack.pop();
                if (k != '[') return false;
            }
            if (str.charAt(i) == '}') {
                if (stack.isEmpty()) return false;
                k = stack.pop();
                if (k != '{') return false;
            }
            //System.out.println(stack);
            //System.out.println(k);
        }
        if(stack.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
