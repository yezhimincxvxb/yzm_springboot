package designmode.BehavioralModel行为型模式.Memento备忘录模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式（Memento Pattern）保存一个对象的某个状态，以便在适当的时候恢复对象。备忘录模式属于行为型模式。
 * <p>
 * 意图：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 * 主要解决：所谓备忘录模式就是在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先保存的状态。
 * 何时使用：很多时候我们总是需要记录一个对象的内部状态，这样做的目的就是为了允许用户取消不确定或者错误的操作，能够恢复到他原先的状态，使得他有"后悔药"可吃。
 * 如何解决：通过一个备忘录类专门存储对象状态。
 * 关键代码：客户不与备忘录类耦合，与备忘录管理类耦合。
 * 应用实例： 1、后悔药。 2、打游戏时的存档。 3、Windows 里的 ctrl + z。 4、IE 中的后退。 5、数据库的事务管理。
 * 优点： 1、给用户提供了一种可以恢复状态的机制，可以使用户能够比较方便地回到某个历史的状态。 2、实现了信息的封装，使得用户不需要关心状态的保存细节。
 * 缺点：消耗资源。如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存。
 * 使用场景： 1、需要保存/恢复数据的相关状态场景。 2、提供一个可回滚的操作。
 * 注意事项： 1、为了符合迪米特原则，还要增加一个管理备忘录的类。 2、为了节约内存，可使用原型模式+备忘录模式。
 */
public class MementoClient {
    public static void main(String[] args) {
        //打BOSS之前：血、蓝全部满值
        Role role = new Role(100, 100);
        System.out.println("----------大战BOSS之前----------");
        role.display();

        //存档一
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(role.saveMemento());

        //大战BOSS，快come Over了
        role.setBloodFlow(50);
        role.setMagicPoint(80);
        System.out.println("----------大战BOSS中----------");
        role.display();

        //存档二
        caretaker.setMemento(role.saveMemento());

        //恢复存档
        System.out.println("----------恢复存档二----------");
        role.restoreMemento(caretaker.getMemento(1));
        role.display();
        System.out.println("----------恢复存档一----------");
        role.restoreMemento(caretaker.getMemento(0));
        role.display();

    }
}

/**
 * 游戏角色
 */
class Role {
    private int bloodFlow;
    private int magicPoint;

    public Role(int bloodFlow, int magicPoint) {
        this.bloodFlow = bloodFlow;
        this.magicPoint = magicPoint;
    }

    public int getBloodFlow() {
        return bloodFlow;
    }

    public void setBloodFlow(int bloodFlow) {
        this.bloodFlow = bloodFlow;
    }

    public int getMagicPoint() {
        return magicPoint;
    }

    public void setMagicPoint(int magicPoint) {
        this.magicPoint = magicPoint;
    }

    /**
     * @desc 展示角色当前状态
     */
    public void display() {
        System.out.println("用户当前状态:");
        System.out.println("血量:" + getBloodFlow() + ";蓝量:" + getMagicPoint());
    }

    /**
     * @desc 存档
     */
    public Memento saveMemento() {
        return new Memento(getBloodFlow(), getMagicPoint());
    }

    /**
     * @desc 恢复存档
     */
    public void restoreMemento(Memento memento) {
        this.bloodFlow = memento.getBloodFlow();
        this.magicPoint = memento.getMagicPoint();
    }
}

class Memento {
    private final int bloodFlow;
    private final int magicPoint;

    public Memento(int bloodFlow, int magicPoint) {
        this.bloodFlow = bloodFlow;
        this.magicPoint = magicPoint;
    }

    public int getBloodFlow() {
        return bloodFlow;
    }

    public int getMagicPoint() {
        return magicPoint;
    }

}

class Caretaker {
    private final List<Memento> mementos = new ArrayList<>();

    public void setMemento(Memento memento) {
        this.mementos.add(memento);
    }

    public Memento getMemento(int index) {
        return mementos.get(index);
    }
}