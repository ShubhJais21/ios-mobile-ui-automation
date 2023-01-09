package utils;


import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static Map<String, String> dataMap = new HashMap<>();

    public static void putData(String key, String data) {
        dataMap.put(key, data);
    }

    public static String getData(String key) {
        return dataMap.get(key);
    }
}
