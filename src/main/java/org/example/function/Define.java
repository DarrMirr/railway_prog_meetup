package org.example.function;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Interface to define recurse
 */
public interface Define {

    static <A, R> Function<A, R> def(Function<A, R> function) {
        return function;
    }

    static <T, U, R> BiFunction<T, U, R> def(BiFunction<T, U, R> biFunction) {
        return biFunction;
    }

    /**
     * currying recurse with 2 argument
     *
     * example1: var sum = def((Integer x) -> (Integer y) -> x + y)
     * example2: var sum = Define.<Integer, Integer, Integer>def(x -> y -> x + y );
     * restriction: java compile is failed if there is 1+ example1 at project
     *
     * @param function currying recurse
     * @param <A> first input argument
     * @param <B> second input argument
     * @param <R> return value
     * @return currying recurse result of type R
     */
    static <A, B, R> Function<A, Function<B, R>> def(Currying.Of2<A, B, R> function) {
        return function;
    }

    static <A, B, C, R> Function<A, Function<B, Function<C, R>>> def(Currying.Of3<A, B, C, R> function) {
        return function;
    }

    static <A, B, C, D, R> Function<A, Function<B, Function<C, Function<D, R>>>> def(Currying.Of4<A, B, C, D, R> function) {
        return function;
    }
}
