package designmode.BehavioralModel行为型模式.State状态模式;

/**
 * 在状态模式（State Pattern）中，类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。
 * 在状态模式中，我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的 context 对象。
 *
 * 意图：允许对象在内部状态发生改变时改变它的行为，对象看起来好像修改了它的类。
 * 主要解决：对象的行为依赖于它的状态（属性），并且可以根据它的状态改变而改变它的相关行为。
 * 何时使用：代码中包含大量与对象状态有关的条件语句。
 * 如何解决：将各种具体的状态类抽象出来。
 * 关键代码：通常命令模式的接口中只有一个方法。而状态模式的接口中有一个或者多个方法。而且，状态模式的实现类的方法，一般返回值，或者是改变实例变量的值。也就是说，状态模式一般和对象的状态有关。实现类的方法有不同的功能，覆盖接口中的方法。状态模式和命令模式一样，也可以用于消除 if...else 等条件选择语句。
 * 应用实例： 1、打篮球的时候运动员可以有正常状态、不正常状态和超常状态。 2、曾侯乙编钟中，'钟是抽象接口','钟A'等是具体状态，'曾侯乙编钟'是具体环境（Context）。
 * 优点： 1、封装了转换规则。 2、枚举可能的状态，在枚举状态之前需要确定状态种类。 3、将所有与某个状态有关的行为放到一个类中，并且可以方便地增加新的状态，只需要改变对象状态即可改变对象的行为。 4、允许状态转换逻辑与状态对象合成一体，而不是某一个巨大的条件语句块。 5、可以让多个环境对象共享一个状态对象，从而减少系统中对象的个数。
 * 缺点： 1、状态模式的使用必然会增加系统类和对象的个数。 2、状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱。 3、状态模式对"开闭原则"的支持并不太好，对于可以切换状态的状态模式，增加新的状态类需要修改那些负责状态转换的源代码，否则无法切换到新增状态，而且修改某个状态类的行为也需修改对应类的源代码。
 * 使用场景： 1、行为随状态改变而改变的场景。 2、条件、分支语句的代替者。
 * 注意事项：在行为受状态约束的时候使用状态模式，而且状态不超过 5 个。
 */
public class StateClient {
    public static void main(String[] args) {
        //第一间房
        Room room = new Room();
        room.bookRoom();    //预订
        room.checkInRoom();   //入住
        room.bookRoom();    //预订
        System.out.println(room);
        System.out.println("---------------------------");

        //第二间房
        Room room2 = new Room();
        room2.checkInRoom(); //入住
        room2.bookRoom(); //预订
        room2.checkOutRoom(); //退房
        room2.bookRoom(); //预订
        System.out.println(room2);
        System.out.println("---------------------------");

        //第三间房
        Room room3 = new Room();
        room3.unsubscribeRoom(); //退订
        room3.checkOutRoom(); //退房
        room3.checkInRoom(); //入住
        room3.bookRoom(); //预订
        System.out.println(room3);
    }
}

interface IState {
    //预订房间
    void bookRoom();

    //退订房间
    void unsubscribeRoom();

    //入住
    void checkInRoom();

    //退房
    void checkOutRoom();
}

class Room {

    IState freeTimeState;    //空闲状态
    IState checkInState;     //入住状态
    IState bookedState;      //预订状态

    IState state;

    public Room() {
        freeTimeState = new FreeTimeState(this);
        checkInState = new CheckInState(this);
        bookedState = new BookedState(this);

        state = freeTimeState;  //初始状态为空闲
    }

    public void bookRoom() {
        state.bookRoom();
    }

    public void unsubscribeRoom() {
        state.unsubscribeRoom();
    }

    public void checkInRoom() {
        state.checkInRoom();
    }

    public void checkOutRoom() {
        state.checkOutRoom();
    }

    @Override
    public String toString() {
        return "该房间的状态是:" + getState().getClass().getName();
    }

    //getter和setter方法
    public IState getFreeTimeState() {
        return freeTimeState;
    }

    public void setFreeTimeState(IState freeTimeState) {
        this.freeTimeState = freeTimeState;
    }

    public IState getCheckInState() {
        return checkInState;
    }

    public void setCheckInState(IState checkInState) {
        this.checkInState = checkInState;
    }

    public IState getBookedState() {
        return bookedState;
    }

    public void setBookedState(IState bookedState) {
        this.bookedState = bookedState;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }
}

/**
 * @Description: 空闲状态可以预订和入住
 */
class FreeTimeState implements IState {

    Room hotelManagement;

    public FreeTimeState(Room hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    @Override
    public void bookRoom() {
        System.out.println("您已经成功预订了...");
        hotelManagement.setState(hotelManagement.getBookedState());   //状态变成已经预订
    }

    @Override
    public void checkInRoom() {
        System.out.println("您已经成功入住了...");
        hotelManagement.setState(hotelManagement.getCheckInState());   //状态变成已经入住
    }

    @Override
    public void unsubscribeRoom() {
        System.out.println("当前房间空闲，可预订或入住...");
    }

    @Override
    public void checkOutRoom() {
        System.out.println("当前房间空闲，可预订或入住...");
    }
}

/**
 * @Description: 预订状态可以入住和退订
 */
class BookedState implements IState {
    Room hotelManagement;

    public BookedState(Room hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    @Override
    public void bookRoom() {
        System.out.println("当前房间已预订，可入住或退订...");
    }

    @Override
    public void checkInRoom() {
        System.out.println("您已经成功入住了...");
        hotelManagement.setState(hotelManagement.getCheckInState());         //状态变成入住
    }

    @Override
    public void unsubscribeRoom() {
        System.out.println("退订成功,欢迎下次光临...");
        hotelManagement.setState(hotelManagement.getFreeTimeState());   //变成空闲状态
    }

    @Override
    public void checkOutRoom() {
        System.out.println("当前房间已预订，可入住或退订...");
    }
}

/**
 * @Description: 入住可以退房
 */
class CheckInState implements IState {
    Room hotelManagement;

    public CheckInState(Room hotelManagement) {
        this.hotelManagement = hotelManagement;
    }

    @Override
    public void bookRoom() {
        System.out.println("当前房间已入住，可退房...");
    }

    @Override
    public void checkInRoom() {
        System.out.println("当前房间已入住，可退房...");
    }

    @Override
    public void checkOutRoom() {
        System.out.println("退房成功....");
        hotelManagement.setState(hotelManagement.getFreeTimeState());     //状态变成空闲
    }

    @Override
    public void unsubscribeRoom() {
        System.out.println("当前房间已入住，可退房...");
    }
}