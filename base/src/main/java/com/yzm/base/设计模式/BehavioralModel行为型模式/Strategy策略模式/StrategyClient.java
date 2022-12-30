package com.yzm.base.设计模式.BehavioralModel行为型模式.Strategy策略模式;

/**
 * 策略模式
 * 定义一系列算法，将每个算法封装到具有公共接口的一系列策略类中，从而使它们可以相互替换，并让算法可以在不影响到客户端的情况下发生变化。
 * <p>
 * 在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
 * 在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。策略对象改变 context 对象的执行算法。
 * <p>
 * 作用
 * 策略模式仅仅封装算法（包括添加 & 删除），但策略模式并不决定在何时使用何种算法，算法的选择由客户端来决定
 * 将算法的责任和本身进行解耦
 * 对算法进行封装，将算法的责任和算法本身分割开，委派给不同的对象管理。
 * <p>
 * 意图：定义一系列的算法,把它们一个个封装起来, 并且使它们可相互替换。
 * 主要解决：在有多种算法相似的情况下，使用 if...else 所带来的复杂和难以维护。
 * 何时使用：一个系统有许多许多类，而区分它们的只是他们直接的行为。
 * 如何解决：将这些算法封装成一个一个的类，任意地替换。
 * 关键代码：实现同一个接口。
 * 应用实例： 1、诸葛亮的锦囊妙计，每一个锦囊就是一个策略。 2、旅行的出游方式，选择骑自行车、坐汽车，每一种旅行方式都是一个策略。 3、JAVA AWT 中的 LayoutManager。
 * 优点： 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。
 * 缺点： 1、策略类会增多。 2、所有策略类都需要对外暴露。
 * 使用场景： 1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。
 * 2、一个系统需要动态地在几种算法中选择一种。 3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。
 * 注意事项：如果一个系统的策略多于四个，就需要考虑使用混合模式，解决策略类膨胀的问题。
 */
public class StrategyClient {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setStrategy(new OperationAdd());
        System.out.println("10 + 5 = " + calculator.executeStrategy(10, 5));
        System.out.println();

        calculator.setStrategy(new OperationSubtract());
        System.out.println("10 - 5 = " + calculator.executeStrategy(10, 5));
        System.out.println();

        calculator.setStrategy(new OperationMultiply());
        System.out.println("10 * 5 = " + calculator.executeStrategy(10, 5));
        System.out.println();

        calculator.setStrategy(new OperationDivision());
        System.out.println("10 / 5 = " + calculator.executeStrategy(10, 5));
    }
}

/**
 * 策略接口
 */
interface IStrategy {
    int doOperation(int num1, int num2);
}

/**
 * 具体策略算法
 */
class OperationAdd implements IStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class OperationSubtract implements IStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class OperationMultiply implements IStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

class OperationDivision implements IStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 / num2;
    }
}

/**
 * 计算器
 */
class Calculator {
    private IStrategy strategy;

    public Calculator() {
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}