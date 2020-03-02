package com.alevel.sandbox.lambda;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntPredicate;

@FunctionalInterface
public interface IntFilter {

    int[] apply(int... elements);

    default IntFilter thenApply(IntFilter another) {
        Objects.requireNonNull(another);
        return elements -> another.apply(apply(elements));
    }

    default IntFilter and(IntPredicate predicate) {
        Objects.requireNonNull(predicate);
        return elements -> innerFilter(predicate, apply(elements));
    }

    static IntFilter all(IntPredicate... predicates) {
        Objects.requireNonNull(predicates);

        IntPredicate resultingPredicate = i -> true;

        for (IntPredicate predicate : predicates) {
            resultingPredicate = resultingPredicate.and(predicate);
        }

        return filter(resultingPredicate);
    }

    static IntFilter filter(IntPredicate predicate) {
        Objects.requireNonNull(predicate);

        return elements -> innerFilter(predicate, elements);
    }

    private static int[] innerFilter(IntPredicate predicate, int[] elements) {
        Objects.requireNonNull(elements);

        int length = elements.length;

        int[] result = new int[length];

        int count = 0;

        for (int element : elements) {
            if (predicate.test(element)) {
                result[count++] = element;
            }
        }

        if (count == length) return result;

        return Arrays.copyOf(result, count);
    }

}
