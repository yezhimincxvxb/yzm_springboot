package base.operator;

/**
 * 运算符
 */
public class OperatorDemo {

    public static void main(String[] args) {
        demo01();
    }

    /**
     * & 与 &&
     */
    private static void demo01() {
        method01();
    }

    /**
     * & 按位与
     * 10：0000 1010                              14：0000 1110
     * 2：0000 0010                              10：0000 1010
     * 0000 0010 ==> 2                           0000 1010 ==> 10
     */
    private static void method01() {
        int a = 14;
        int b = 10;
        int c = a & b;
        System.out.println("c = " + c);
    }

    /**
     * & 逻辑与
     * && 短路与
     * 者都要求运算符左右两端的布尔值都是true 整个表达式的值才是 true。
     * &&之所以称为短路运算，是因为如果&&左边的表达式的值是 false，右边的表达式会被直接短路掉，不会进行运算。
     */
    private static void method02() {

    }

}
