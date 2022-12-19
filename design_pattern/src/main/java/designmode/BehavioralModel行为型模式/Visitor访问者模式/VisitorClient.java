package designmode.BehavioralModel行为型模式.Visitor访问者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 在访问者模式（Visitor Pattern）中，我们使用了一个访问者类，它改变了元素类的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变。
 * 这种类型的设计模式属于行为型模式。根据模式，元素对象已接受访问者对象，这样访问者对象就可以处理元素对象上的操作。
 * <p>
 * 意图：主要将数据结构与数据操作分离。
 * 主要解决：稳定的数据结构和易变的操作耦合问题。
 * 何时使用：需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作"污染"这些对象的类，使用访问者模式将这些封装到类中。
 * 如何解决：在被访问的类里面加一个对外提供接待访问者的接口。
 * 关键代码：在数据基础类里面有一个方法接受访问者，将自身引用传入访问者。
 * 应用实例：您在朋友家做客，您是访问者，朋友接受您的访问，您通过朋友的描述，然后对朋友的描述做出一个判断，这就是访问者模式。
 * 优点： 1、符合单一职责原则。 2、优秀的扩展性。 3、灵活性。
 * 缺点： 1、具体元素对访问者公布细节，违反了迪米特原则。 2、具体元素变更比较困难。 3、违反了依赖倒置原则，依赖了具体类，没有依赖抽象。
 * 使用场景： 1、对象结构中对象对应的类很少改变，但经常需要在此对象结构上定义新的操作。
 * 2、需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作"污染"这些对象的类，也不希望在增加新操作时修改这些类。
 * 注意事项：访问者可以对功能进行统一，可以做报表、UI、拦截器与过滤器。
 */
public class VisitorClient {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        //电脑维修工
        computer.accept(new ComputerRepairman());
        System.out.println();
        //电脑用户
        computer.accept(new ComputerUser());
    }
}

/**
 * 表示元素接口
 */
interface ComputerPart {
    void accept(ComputerVisitor computerPartVisitor);
}

class Keyboard implements ComputerPart {

    @Override
    public void accept(ComputerVisitor visitor) {
        visitor.visit(this);
    }
}

class Mouse implements ComputerPart {

    @Override
    public void accept(ComputerVisitor visitor) {
        visitor.visit(this);
    }
}

class Monitor implements ComputerPart {

    @Override
    public void accept(ComputerVisitor visitor) {
        if (visitor instanceof ComputerRepairman) {
            //维修工才需要对显示屏进行操作，普通用户不需要
            visitor.visit(this);
        }
    }
}

class Computer implements ComputerPart {

    private final List<ComputerPart> parts = new ArrayList<>();

    public Computer() {
        parts.add(new Mouse());
        parts.add(new Keyboard());
        parts.add(new Monitor());
    }

    @Override
    public void accept(ComputerVisitor visitor) {
        for (ComputerPart part : parts) {
            part.accept(visitor);
        }
        visitor.visit(this);
    }
}

/**
 * 访问者接口
 */
interface ComputerVisitor {
    void visit(Computer computer);

    void visit(Mouse mouse);

    void visit(Keyboard keyboard);

    void visit(Monitor monitor);
}

class ComputerRepairman implements ComputerVisitor {

    @Override
    public void visit(Mouse mouse) {
        System.out.println("组装 鼠标.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("组装 键盘.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("组装 显示器.");
    }

    @Override
    public void visit(Computer computer) {
        System.out.println("维修 计算机.");
    }
}

class ComputerUser implements ComputerVisitor {

    @Override
    public void visit(Mouse mouse) {
        System.out.println("点击 鼠标.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("敲击 键盘.");
    }

    @Override
    public void visit(Monitor monitor) {
        //System.out.println("打开 显示器.");
    }

    @Override
    public void visit(Computer computer) {
        System.out.println("使用 计算机.");
    }
}