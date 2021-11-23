package org.example.function;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Currying {

    static <A, B, R> Function<A, Function<B, R>> curry(BiFunction<? super A, ? super B, ? extends R> biFunction) {
        return a -> b -> biFunction.apply(a, b);
    }

    static <A, B, R> BiFunction<A, B, R> uncurry(Function<? super A, ? extends Function<? super B, ? extends R>> function) {
        return (a, b) -> function.apply(a).apply(b);
    }

    interface Of1<A, R> extends Function<A, R> {}
    interface Of2<A, B, R> extends Function<A, Function<B, R>> {}
    interface Of3<A, B, C, R> extends Function<A, Function<B, Function<C, R>>> {}
    interface Of4<A, B, C, D, R> extends Function<A, Function<B, Function<C, Function<D, R>>>> {}
}
