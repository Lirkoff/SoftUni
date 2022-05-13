package implementations;

import interfaces.Solvable;

import java.util.ArrayDeque;

import java.util.Deque;

public class BalancedParentheses implements Solvable {
    private String parentheses;
    private int middle;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses.trim();
    }

    @Override
    public Boolean solve() {
        if (this.parentheses == null) {
            throw new IllegalArgumentException("Empty input");
        }

        if (this.parentheses.length() % 2 != 0) {
            return false;
        }

        if (elementNotAllowed()) {
            return false;
        }

        this.middle = this.parentheses.length() / 2;


        Deque<Character> stack = new ArrayDeque<>();
        Deque<Character> queue = new ArrayDeque<>();


        fillStackWithFirstHalf(stack);
        fillQueueWithSecondHalf(queue);

        return balanced(stack, queue);
    }

    private boolean balanced(Deque<Character> stack, Deque<Character> queue) {
        for (int i = 0; i < middle; i++) {
            char firstElement = stack.pop();
            char secondElement = queue.poll();

            if ((firstElement == '(' && secondElement != ')') ||(firstElement == '{' && secondElement != '}')
                    || (firstElement == '[' && secondElement != ']')) {
                return false;
            }
        }
        return true;
    }

    private boolean elementNotAllowed() {
        String allowedElements = "{[()]}";
        for (int i = 0; i < this.parentheses.length(); i++) {
            char element = this.parentheses.charAt(i);

            if (!allowedElements.contains(element + "")) {
                return true;
            }
        }
        return false;
    }

    private void fillQueueWithSecondHalf(Deque<Character> queue) {
        for (int i = this.middle; i < this.parentheses.length(); i++) {
            char element = this.parentheses.charAt(i);
            queue.offer(element);
        }
    }

    private void fillStackWithFirstHalf(Deque<Character> stack) {
        for (int i = 0; i < this.middle; i++) {
            char element = this.parentheses.charAt(i);
            stack.push(element);
        }
    }
}
