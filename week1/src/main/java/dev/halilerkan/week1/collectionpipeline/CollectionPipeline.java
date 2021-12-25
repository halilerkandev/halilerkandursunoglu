package dev.halilerkan.week1.collectionpipeline;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CollectionPipeline {

    private CollectionPipeline() {}

    /**
     * Method to get numbers using for collection pipeline.
     *
     * @param numbers {@link List} of {@link Integer} to be used for mutating
     * @param mutator {@link UnaryOperator} of {@link Integer} to be used for mutate first param
     * @return {@link List} of {@link Integer} representing mutating numbers
     */
    public static List<Integer> mapNumbers(List<Integer> numbers, UnaryOperator<Integer> mutator) {
        return numbers.stream()
                .map(mutator)
                .collect(Collectors.toList());
    }

    public static Integer makeDouble(Integer number) {
        return number * 2;
    }

    public static List<Integer> concatNumbers(List<Integer> numbers1, List<Integer> numbers2) {
        return Stream.concat(numbers1.stream(), numbers2.stream())
                .collect(Collectors.toList());
    }

    public static List<Integer> differenceNumbers(List<Integer> numbers1, List<Integer> numbers2) {
        return numbers1.stream()
                .filter(element -> !numbers2.contains(element))
                .collect(Collectors.toList());
    }

    public static List<Integer> distinctNumbers(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Integer> dropNumbers(List<Integer> numbers, Long n) {
        return numbers.stream()
                .skip(n)
                .collect(Collectors.toList());
    }

    public static List<Integer> filterNumbers(List<Integer> numbers, Predicate<Integer> condition) {
        return numbers.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    public static Boolean isEven(Integer number) {
        return number % 2 == 0;
    }

    public static List<Integer> flattenTwoNumberLists(List<Integer> numbers1, List<Integer> numbers2) {
        return Stream.of(numbers1, numbers2)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static Integer sumNumbersWithReduce(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }


}
