package utilities;
import java.util.HashMap;

/**
 * @author Ahmed.H.Biby
 * Purpose: This class offers container capabilities to store elements with their associated attributes.
 */
public class Registry<K,V> {
    private HashMap<String, HashMap<K,V>> registry = new HashMap<>();

    /**
     * This method aims at adding an element with it associated attributes to the registry.
     * @param entryKey: the name of the element to be added.
     * @param entryHashMap: the set of associated attributes stored in the form of keys and values.
     */
    public void add(String entryKey, HashMap<K,V> entryHashMap){
        registry.put(entryKey, entryHashMap);
        //System.out.println(registry);
    }

    /**
     * This method aims at removing an element with it associated attributes to the registry.
     * @param entryKey: the name of the element to be removed.
     */
    public void remove(String entryKey){
        registry.remove(entryKey);
        //System.out.println(registry);
    }

    /**
     * This method acts as a getter for the registry.
     * @return the registry.
     */
    public HashMap<String, HashMap<K,V>> getRegistry(){
        //System.out.println(registry);
        return registry;
    }
}