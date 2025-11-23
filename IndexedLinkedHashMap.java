import java.util.ArrayList;
import java.util.LinkedHashMap;

public class IndexedLinkedHashMap<K,V> extends LinkedHashMap<K,V> {
    
    ArrayList<K> index = new ArrayList<>();

    @Override
    public V put(K key, V val) {
        if (!super.containsKey(key)) {
            index.add(key);
        }

        V returnValue = super.put(key, val);
        return returnValue;
    }

    public V getValue(int i) {
        return (V) super.get(index.get(i));
    }

    public K getKey(int i) {
        return (K) index.get(i);
    }

    public int getIndex(K key) {
        return index.indexOf(key);
    }

    public int indexOf(V value) {
        for (int i = 0; i < this.index.size(); i++) {
            if (super.get(index.get(i)) == value) {
                return i;
            }
        }

        return -1;
    }

}
