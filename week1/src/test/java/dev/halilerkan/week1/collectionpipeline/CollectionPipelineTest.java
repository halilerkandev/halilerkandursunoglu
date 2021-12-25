package dev.halilerkan.week1.collectionpipeline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class CollectionPipelineTest {

    @Test
    void mapNumbersTest() {
        List<Integer> numbers = List.of(0,1,2,3,4,5,6,7,8,9,10);
        List<Integer> doubleOfNumbers = List.of(0,2,4,6,8,10,12,14,16,18,20);

        assertEquals(doubleOfNumbers, CollectionPipeline.mapNumbers(numbers, CollectionPipeline::makeDouble));
    }

    @Test
    void makeDoubleTest() {
        Integer number = 2;
        Integer expected = 4;

        assertEquals(expected, CollectionPipeline.makeDouble(number));
    }

    @Test
    void concatNumbersTest() {
        List<Integer> numbers1 = List.of(0,1,2,3,4,5);
        List<Integer> numbers2 = List.of(6,7,8,9,10);

        List<Integer> expected = List.of(0,1,2,3,4,5,6,7,8,9,10);

        assertEquals(expected, CollectionPipeline.concatNumbers(numbers1, numbers2));
    }

    @Test
    void differenceNumbersTest() {
        List<Integer> numbers1 = List.of(0,1,2,3,4,5);
        List<Integer> numbers2 = List.of(3,4,5,9,10);

        List<Integer> differences = CollectionPipeline.differenceNumbers(numbers1, numbers2);

        List<Integer> expected = List.of(0,1,2);

        assertEquals(3, differences.size());
        assertEquals(expected, differences);
    }

    @Test
    void distinctNumbersTest() {
        List<Integer> numbers = List.of(0,1,2,3,4,5,4,5,1,2);
        List<Integer> expected = List.of(0,1,2,3,4,5);

        assertEquals(expected, CollectionPipeline.distinctNumbers(numbers));

    }

    @Test
    void dropNumbersTest() {
        List<Integer> numbers = List.of(0,1,2,3,4,5,4,5,1,2);
        List<Integer> expected = List.of(5,4,5,1,2);

        assertEquals(expected, CollectionPipeline.dropNumbers(numbers, 5L));
    }

    @Test
    void filterNumbersTest() {
        List<Integer> numbers = List.of(0,1,2,3,4,5);
        List<Integer> expected = List.of(0,2,4);

        assertEquals(expected, CollectionPipeline.filterNumbers(numbers, CollectionPipeline::isEven));
    }

    @Test
    void isEvenTest() {
        Integer number1 = 4;
        Integer number2 = 5;

        assertEquals(true, CollectionPipeline.isEven(number1));
        assertNotEquals(true, CollectionPipeline.isEven(number2));
    }

    @Test
    void flattenTwoNumberListsTest() {
        List<Integer> numbers1 = List.of(0,1,2,3,4,5);
        List<Integer> numbers2 = List.of(0,1,2,3,4,5);
        List<Integer> expected = List.of(0,1,2,3,4,5,0,1,2,3,4,5);

        assertEquals(expected, CollectionPipeline.flattenTwoNumberLists(numbers1, numbers2));
    }

    @Test
    void sumNumbersWithReduceTest() {
        List<Integer> numbers = List.of(0,1,2,3,4,5);
        Integer expected = 15;

        assertEquals(expected, CollectionPipeline.sumNumbersWithReduce(numbers));
    }

}
