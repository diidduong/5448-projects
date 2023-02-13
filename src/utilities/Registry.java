package utilities;
import java.util.HashMap;

public class Registry<K,V> {
    private HashMap<String, HashMap<K,V>> registry = new HashMap<>();

    public void add(String entryKey, HashMap<K,V> entryHashMap){
        registry.put(entryKey, entryHashMap);
        //System.out.println(registry);
    }
    public void remove(String entryKey){
        registry.remove(entryKey);
        //System.out.println(registry);
    }

    public void setRegistry(HashMap<String, HashMap<K, V>> registry) {
        this.registry = registry;
    }

    public HashMap<String, HashMap<K,V>> getRegistry(){
        //System.out.println(registry);
        return registry;
    }

}