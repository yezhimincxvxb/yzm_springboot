package com.yzm.base.设计模式.StructuralPattern结构型模式.Adapter适配器模式;

/**
 * 对象适配器模式
 */
public class ObjectAdapterClient {
    public static void main(String[] args) {
        Computer computer = new ConcreteComputer();
        //原有功能
        MemoryCard mc = new ConcreteMemoryCard();
        computer.read(mc);
        System.out.println();

        //适配功能
        MemoryCard game = new AdapterGameCard(new GameCard());
        computer.read(game);
        System.out.println();

        //新增视频卡
        MemoryCard video = new AdapterGameCard(new VideoCard());
        computer.read(video);
    }

}

/**
 * 步骤 1 不可修改
 * 计算机接口：读取内存卡
 */
interface Computer {
    void read(MemoryCard card);
}

class ConcreteComputer implements Computer {
    @Override
    public void read(MemoryCard card) {
        card.readCard();
    }
}

/**
 * 步骤 2 不可修改
 * 内存卡
 */
interface MemoryCard {
    void readCard();
}

class ConcreteMemoryCard implements MemoryCard {

    @Override
    public void readCard() {
        System.out.println("读取内存卡");
    }
}

/**
 * 步骤 3 不可修改 被适配角色
 * 多功能卡
 */
interface ICard {
    void read();
}

/**
 * 游戏卡
 */
class GameCard implements ICard {
    @Override
    public void read() {
        System.out.println("读取游戏卡");
    }
}

/**
 * 步骤 4 适配器角色 新增
 * 原有计算机只能读取内存卡，在不改变计算机代码的情况下，
 * 要使计算机可以读取游戏卡，那就需要对游戏卡进行兼容
 */
class AdapterGameCard implements MemoryCard {
    private final ICard card;

    public AdapterGameCard(ICard card) {
        this.card = card;
    }

    @Override
    public void readCard() {
        card.read();
    }
}

/**
 * 新增视频卡
 */
class VideoCard implements ICard {
    @Override
    public void read() {
        System.out.println("读取视频卡");
    }
}