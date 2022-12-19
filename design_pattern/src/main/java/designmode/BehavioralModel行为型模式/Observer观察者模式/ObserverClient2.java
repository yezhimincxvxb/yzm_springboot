package designmode.BehavioralModel行为型模式.Observer观察者模式;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * JDK自带的观察者模式
 */
public class ObserverClient2 {
    public static void main(String[] args) {
        Viewer v = new Viewer("李老师");
        Viewer v2 = new Viewer("沈老师");
        Viewer v3 = new Viewer("范老师");

        String[] ss = new String[]{"唱歌", "跳舞", "魔术"};
        for (int i = 0; i < 3; i++) {
            Contestants contestants = new Contestants(i);
            contestants.addObserver(v);
            contestants.addObserver(v2);
            contestants.addObserver(v3);
            contestants.act(ss[i]);
            System.out.println();
        }
    }
}

/**
 * 被观察者角色
 * 参赛者
 */
class Contestants extends Observable {

    public Contestants(int number) {
        System.out.println("**参赛选手" + (number + 1) + "号入场**");
    }

    /**
     * 表演
     */
    public void act(String works) {
        System.out.println("**表演开始**");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("**表演结束**");
        setChanged();
        notifyObservers(works);
    }

}

/**
 * 观察者角色
 * 评审员
 */
class Viewer implements Observer {
    private final String name;

    public Viewer(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("评审员{" + name + "}对作品[" + arg + "]打分：" + new Random().nextInt(10));
    }
}
