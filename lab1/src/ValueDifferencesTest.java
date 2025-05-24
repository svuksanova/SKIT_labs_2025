import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValueDifferencesTest {

    @Test
    void allKeysAreCommon() {
        // T T T T C5.1 - Happy path
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> map2 = Map.of("A", 3, "B", 8, "C", 1);
        Map<String, Integer> expected = Map.of("A", 2, "B", 2, "C", 2);
        Assertions.assertEquals(expected, ValueDifferences.computeValueDifferences(map1, map2));
    }

    @Test
    void emptyMap1() {
        // T F T T C5.3 - Modified Test 3
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> expected = new HashMap<>();
        Assertions.assertEquals(expected, ValueDifferences.computeValueDifferences(map1, map2));
    }

    @Test
    void emptyMap2() {
        // T T T F C5.3 - Modified Test 5
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> map2 = new HashMap<>();
        Map<String, Integer> expected = new HashMap<>();
        Assertions.assertEquals(expected, ValueDifferences.computeValueDifferences(map1, map2));
    }

    @Test
    void atLeastOneOfTheKeysAreCommon() {
        // T T T T C5.2 - Test 6
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> map2 = Map.of("A", 7, "B", 8, "D", 12);
        Map<String, Integer> expected = Map.of("A", 2, "B", 2);
        Assertions.assertEquals(expected, ValueDifferences.computeValueDifferences(map1, map2));
    }

    @Test
    void noneOfTheKeysAreCommon() {
        // T T T T C5.3 - Test 7
        Map<String, Integer> map1 = Map.of("A", 5, "B", 10, "C", 3);
        Map<String, Integer> map2 = Map.of("X", 3, "Y", 8, "Z", 1);
        Map<String, Integer> expected = new HashMap<>();
        Assertions.assertEquals(expected, ValueDifferences.computeValueDifferences(map1, map2));
    }
}
