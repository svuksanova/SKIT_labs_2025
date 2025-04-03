import java.util.HashMap;
import java.util.Map;

public class ValueDifferences {
    public static Map<String, Integer> computeValueDifferences(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> finalMap = new HashMap<>();
        if (map1 == null || map2 == null) {
            throw new IllegalArgumentException("Input maps cannot be null");
        }

        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                int difference = map1.get(key) - map2.get(key);
                finalMap.put(key, Math.abs(difference));
            }
        }

        return finalMap;
    }
}
