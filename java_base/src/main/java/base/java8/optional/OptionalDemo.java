package base.java8.optional;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<Object> empty = Optional.empty();
        System.out.println("empty = " + empty.get());
//        Optional<Object> optional1 = Optional.of(null);
//        System.out.println("optional1 = " + optional1);

    }
}
