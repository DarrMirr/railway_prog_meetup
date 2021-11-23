package org.example.function;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Implementation of Either monad
 *
 * Object is stored either on left or on right.
 * There is no way to store object both on the right and on the left.
 *
 * @param <L> object's data type on the left
 * @param <R> object's data type on the right
 */
public interface Either<L ,R> {

    /**
     * Method to create Either with object on the left
     *
     * @param object to wrap by monad
     * @param <L> object's data type on the left
     * @param <R> expected object's data type on the right
     * @return {@link Either.Left monad}
     */
    static <L, R> Either<L, R> left(L object) {
        return Left.of(object);
    }

    /**
     * Method to create Either with object on the right
     *
     * @param object to wrap by monad
     * @param <L> expected object's data type on the left
     * @param <R> object's data type on the right
     * @return {@link Either.Right monad}
     */
    static <L, R> Either<L, R> right(R object) {
        return Right.of(object);
    }

    /**
     * Get object from the left.
     * Subinterface {@link Either.Left} must override this method
     * Method return {@link Optional} due to there is no object in case of Either is instance of {@link Either.Right}
     *
     * @return object on the left that is wrapped by {@link Optional}
     */
    default Optional<L> left() {
        return Optional.empty();
    }

    /**
     * Get object from the right.
     * Subinterface {@link Either.Right} must override this method
     * Method return {@link Optional} due to there is no object in case of Either is instance of {@link Either.Left}
     *
     * @return object on the right that is wrapped by {@link Optional}
     */
    default Optional<R> right() {
        return Optional.empty();
    }

    default <U> Either<U, R> mapLeft(Function<? super L, ? extends U> mapper) {
        return Either.left(null);
    }

    default <U> Either<U, R> flatMapLeft(Function<? super L, ? extends Either<U, R>> mapper) {
        return Either.left(null);
    }

    default <U> Either<L, U> mapRight(Function<? super R, ? extends U> mapper) {
        return Either.right(null);
    }

    default <U> Either<L, U> flatMapRight(Function<? super R, ? extends Either<L, U>> mapper) {
        return Either.right(null);
    }

    interface Left<L, R> extends Either<L, R>, Supplier<Optional<L>> {

        static <L, R> Either.Left<L, R> of(L object) {
            return () -> Optional.ofNullable(object);
        }

        @Override
        default Optional<L> left() {
            return this.get();
        }

        @Override
        default <U> Either<U, R> mapLeft(Function<? super L, ? extends U> mapper) {
            Optional<Either<U,R>> eitherOptional = this.left()
                    .map(mapper)
                    .map(Either::left);
            return eitherOptional.orElseGet(() -> Either.left(null));
        }

        @Override
        default <U> Either<U, R> flatMapLeft(Function<? super L, ? extends Either<U, R>> mapper) {
            Optional<Either<U,R>> eitherOptional = this.left().map(mapper);
            return eitherOptional.orElseGet(() -> Either.left(null));
        }
    }

    interface Right<L, R> extends Either<L, R>, Supplier<Optional<R>> {

        static <L, R> Either.Right<L, R> of(R object) {
            return () -> Optional.ofNullable(object);
        }

        @Override
        default Optional<R> right() {
            return this.get();
        }

        @Override
        default <U> Either<L, U> mapRight(Function<? super R, ? extends U> mapper) {
            Optional<Either<L,U>> eitherOptional = this.right()
                    .map(mapper)
                    .map(Either::right);
            return eitherOptional.orElseGet(() -> Either.right(null));
        }

        @Override
        default <U> Either<L, U> flatMapRight(Function<? super R, ? extends Either<L, U>> mapper) {
            Optional<Either<L,U>> eitherOptional = this.right().map(mapper);
            return eitherOptional.orElseGet(() -> Either.right(null));
        }
    }
}
