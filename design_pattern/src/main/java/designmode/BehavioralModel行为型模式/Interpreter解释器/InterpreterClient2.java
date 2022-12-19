package designmode.BehavioralModel行为型模式.Interpreter解释器;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InterpreterClient2 {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("4 + 3 * 2 + 2 * (2 * 6 / 3 % 5) / 2 - 5 = " + (4 + 3 * 2 + 2 * (2 * 6 / 3 % 5) / 2 - 5));
        System.out.println();
        int result = calculator.build("4 + 3 * 2 + 2 * " + calculator.build("2 * 6 / 3 % 5") + " / 2 - 5");
        System.out.println("result = " + result);
    }

}

/**
 * 抽象表达式
 */
interface Node {
    int interpret();
}

class VarNode implements Node {
    private final int value;

    public VarNode(int value) {
        this.value = value;
    }

    public int interpret() {
        return this.value;
    }
}

abstract class SymbolNode implements Node {
    protected Node left;
    protected Node right;

    public SymbolNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}

/**
 * 加、减、乘、除、取余
 */
class AddNode extends SymbolNode {
    public AddNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

class SubNode extends SymbolNode {
    public SubNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return left.interpret() - right.interpret();
    }
}

class MulNode extends SymbolNode {
    public MulNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return left.interpret() * right.interpret();
    }
}

class DivNode extends SymbolNode {
    public DivNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return left.interpret() / right.interpret();
    }
}

class ModNode extends SymbolNode {
    public ModNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return left.interpret() % right.interpret();
    }
}

/**
 * 计算器
 */
class Calculator {

    public int build(String statement) {
        Node left, right;
        Stack<Node> stack = new Stack<>();

        //先进行优先级运算
        List<String> list = this.priorityOperation(Arrays.asList(statement.split(" ")));

        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i)) {
                case "+": {
                    left = stack.pop();
                    right = new VarNode(Integer.parseInt(list.get(++i)));
                    stack.push(new AddNode(left, right));
                    break;
                }
                case "-": {
                    left = stack.pop();
                    right = new VarNode(Integer.parseInt(list.get(++i)));
                    stack.push(new SubNode(left, right));
                    break;
                }
                default:
                    stack.push(new VarNode(Integer.parseInt(list.get(i))));
                    break;
            }
        }

        return stack.pop().interpret();
    }

    /**
     * 优先级运算：乘、除、取余
     */
    public List<String> priorityOperation(List<String> list) {
        Node left, right;

        List<String> next = new ArrayList<>();
        boolean first = true;
        for (int i = 0; i < list.size(); i++) {
            if (first && ("*".equals(list.get(i)) || "/".equals(list.get(i)) || "%".equals(list.get(i)))) {
                left = new VarNode(Integer.parseInt(list.get(i - 1)));
                right = new VarNode(Integer.parseInt(list.get(i + 1)));

                SymbolNode symbolNode;
                if ("*".equals(list.get(i))) {
                    symbolNode = new MulNode(left, right);
                } else if ("/".equals(list.get(i))) {
                    symbolNode = new DivNode(left, right);
                } else {
                    symbolNode = new ModNode(left, right);
                }
                int result = symbolNode.interpret();

                next.remove(next.size() - 1);
                next.add(String.valueOf(result));
                i++; //右边的数字可直接跳过
                first = false;
            } else {
                next.add(list.get(i));
            }
        }

        if (first) return next;
        return priorityOperation(next);
    }

}