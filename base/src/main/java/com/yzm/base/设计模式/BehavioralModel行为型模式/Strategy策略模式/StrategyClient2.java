package com.yzm.base.设计模式.BehavioralModel行为型模式.Strategy策略模式;

public class StrategyClient2 {
    public static void main(String[] args) {
        System.out.println("-------组装宝马-------");
        Car baoma = new BaoMaCar();
        baoma.electrical();
        baoma.low();
        baoma.frame(new RedStrategy());
        baoma.engine(new FuelStrategy());

        System.out.println("-------组装奔驰-------");
        Car benchi = new BenChiCar();
        benchi.electrical();
        benchi.low();
        benchi.frame(new WhiteStrategy());
        benchi.engine(new NewEnergyStrategy());

    }
}

/**
 * 汽车超类
 */
abstract class Car {

    void electrical() {
        System.out.println("组装电器设备");
    }

    void low() {
        System.out.println("组装低盘");
    }

    //车身颜色可选
    abstract void frame(FrameStrategy strategy);

    //发动机类型可选
    abstract void engine(EngineStrategy strategy);
}

class BaoMaCar extends Car {

    @Override
    void frame(FrameStrategy strategy) {
        System.out.println("组装" + strategy.getColor() + "车身");
    }

    @Override
    void engine(EngineStrategy strategy) {
        System.out.println("组装" + strategy.getEngineName() + "发动机");
    }
}

class BenChiCar extends Car {

    @Override
    void frame(FrameStrategy strategy) {
        System.out.println("组装" + strategy.getColor() + "车身");
    }

    @Override
    void engine(EngineStrategy strategy) {
        System.out.println("组装" + strategy.getEngineName() + "发动机");
    }
}

/**
 * 车身策略
 */
interface FrameStrategy {
    String getColor();
}

class RedStrategy implements FrameStrategy {

    @Override
    public String getColor() {
        return "红色";
    }
}

class WhiteStrategy implements FrameStrategy {

    @Override
    public String getColor() {
        return "白色";
    }

}

class BlackStrategy implements FrameStrategy {

    @Override
    public String getColor() {
        return "黑色";
    }

}

/**
 * 发动机策略
 */
interface EngineStrategy {
    String getEngineName();
}


class FuelStrategy implements EngineStrategy {

    @Override
    public String getEngineName() {
        return "燃油";
    }
}

class NewEnergyStrategy implements EngineStrategy {

    @Override
    public String getEngineName() {
        return "新能源";
    }
}
