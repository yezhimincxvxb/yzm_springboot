package designmode.StructuralPattern结构型模式.Adapter适配器模式;

/**
 * 适配器模式（Adapter Pattern）是作为两个不兼容的接口之间的桥梁。它结合了两个独立接口的功能。
 * 这种模式涉及到一个单一的类，该类负责加入独立的或不兼容的接口功能。
 * <p>
 * 意图：将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * 主要解决：主要解决在软件系统中，常常要将一些"现存的对象"放到新的环境中，而新环境要求的接口是现对象不能满足的。
 * 何时使用： 1、系统需要使用现有的类，而此类的接口不符合系统的需要。 2、想要建立一个可以重复使用的类，用于与一些彼此之间没有太大关联的一些类，包括一些可能在将来引进的类一起工作，这些源类不一定有一致的接口。 3、通过接口转换，将一个类插入另一个类系中。（比如老虎和飞禽，现在多了一个飞虎，在不增加实体的需求下，增加一个适配器，在里面包容一个虎对象，实现飞的接口。）
 * 如何解决：继承或依赖（推荐）。
 * 关键代码：适配器继承或依赖已有的对象，实现想要的目标接口。
 * 应用实例： 1、美国电器 110V，中国 220V，就要有一个适配器将 110V 转化为 220V。 2、JAVA JDK 1.1 提供了 Enumeration 接口，而在 1.2 中提供了 Iterator 接口，想要使用 1.2 的 JDK，则要将以前系统的 Enumeration 接口转化为 Iterator 接口，这时就需要适配器模式。 3、在 LINUX 上运行 WINDOWS 程序。 4、JAVA 中的 jdbc。
 * 优点： 1、可以让任何两个没有关联的类一起运行。 2、提高了类的复用。 3、增加了类的透明度。 4、灵活性好。
 * 缺点： 1、过多地使用适配器，会让系统非常零乱，不易整体进行把握。比如，明明看到调用的是 A 接口，其实内部被适配成了 B 接口的实现，一个系统如果太多出现这种情况，无异于一场灾难。因此如果不是很有必要，可以不使用适配器，而是直接对系统进行重构。 2.由于 JAVA 至多继承一个类，所以至多只能适配一个适配者类，而且目标类必须是抽象类。
 * 使用场景：有动机地修改一个正常运行的系统的接口，这时应该考虑使用适配器模式。
 * 注意事项：适配器不是在详细设计时添加的，而是解决正在服役的项目的问题。
 * <p>
 * 分类：类的适配器模式(通过继承实现)、对象的适配器模式(通过注入或创建被适配者)
 * <p>
 * 类的适配器模式
 */
public class ClassAdapterClient {
    public static void main(String[] args) {
        //美国人充电
        AmericanSocket target = new ConcreteSocket();
        target.charge();

        //中国人充电
        AmericanSocket adapter = new Adapter();
        adapter.charge();
    }
}

/**
 * 案例：美国电器 110V，中国 220V。去美国旅游，给手机充电，由于电压不兼容不能直接使用，
 * 好在你带了电压转换器(适配器)，手机充电线插在适配器上，适配器在插在美国插座上解决充电问题。
 */

/**
 * 步骤 1 标准接口 不可修改
 * 美式插座
 */
interface AmericanSocket {
    void charge();
}

class ConcreteSocket implements AmericanSocket {
    public void charge() {
        System.out.println("美式插座支持 110V");
    }
}

/**
 * 步骤 2 被适配者类 不可修改
 * 手机充电器
 */
class MobileCharger {
    public void charge() {
        System.out.println("手机充电器支持 220V");
    }
}

/**
 * 步骤 3 适配器 新增
 * 继承被适配者并且实现标准接口
 */
class Adapter extends MobileCharger implements AmericanSocket {

    @Override
    public void charge() {
        System.out.println("手机充电器通过适配器在美式插座上充电");
        super.charge();
    }
}