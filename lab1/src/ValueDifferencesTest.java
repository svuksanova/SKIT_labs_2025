import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValueDifferencesTest {
    //Happy path
    @Test
    void allKeysAreCommon() {
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> map2 = Map.of("A", 3, "B", 8, "C", 1);
        ValueDifferences valueDifferences = new ValueDifferences();
        Map<String, Integer> expected = Map.of("A", 2, "B", 2, "C", 2);
        assertEquals(expected, valueDifferences.computeValueDifferences(map1, map2));
    }
    //map1 is null
    @Test
    void nullMap1() {
        Map<String, Integer> map2 = Map.of("A", 5, "B", 10, "C", 3);
        assertThrows(IllegalArgumentException.class, () -> {
            ValueDifferences valueDifferences = new ValueDifferences();
            valueDifferences.computeValueDifferences(null, map2);
        });
    }

    //map1 is empty
    @Test
    void emptyMap1() {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> expected = new HashMap<>();
        ValueDifferences valueDifferences = new ValueDifferences();
        assertEquals(expected, valueDifferences.computeValueDifferences(map1, map2));
    }

    //map2 is null
    @Test
    void nullMap2() {
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        assertThrows(IllegalArgumentException.class, () -> {
            ValueDifferences valueDifferences = new ValueDifferences();
            valueDifferences.computeValueDifferences(map1, null);
        });
    }

    //map2 is empty
    @Test
    void emptyMap2() {
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> map2 = new HashMap<>();
        ValueDifferences valueDifferences = new ValueDifferences();
        Map<String, Integer> expected = new HashMap<>();
        assertEquals(expected, valueDifferences.computeValueDifferences(map1, map2));
    }

    //at least one of the keys in the maps are common
    @Test
    void atLeastOneOfTheKeysAreCommon() {
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> map2 = Map.of("A", 7, "B", 8, "D", 12);
        ValueDifferences valueDifferences = new ValueDifferences();
        Map<String, Integer> expected = Map.of("A", 2, "B", 2);
        assertEquals(expected, valueDifferences.computeValueDifferences(map1, map2));
    }

    //none of the keys in the maps are common
    @Test
    void noneOfTheKeysAreCommon() {
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> map2 = Map.of("X", 3, "Y", 8, "Z", 1);
        ValueDifferences valueDifferences = new ValueDifferences();
        Map<String, Integer> expected = new HashMap<>();
        assertEquals(expected, valueDifferences.computeValueDifferences(map1, map2));
    }
}