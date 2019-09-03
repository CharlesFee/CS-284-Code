package CuckooHash;

import java.util.AbstractMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
/*
 *@author Charles Fee
 * I pledge my honor that I have abided by the Stevens Honor System.
 */
public class HashtableCuckoo<K, V>
        implements KWHashMap<K, V> {

    
    private Entry<K, V>[] table1;
    private Entry<K, V>[] table2;
    private List<Entry<K, V> > overflow;
    private static final int START_CAPACITY = 100;
    private double LOAD_THRESHOLD = 0.6;
    private int tableSize=(int)(START_CAPACITY*LOAD_THRESHOLD);
    private int numKeys;
 
    public HashtableCuckoo() {
        table1 = new Entry[tableSize];
        table2 = new Entry[tableSize];
        overflow = new ArrayList();
    }
  
    /** Contains key-value pairs for a hash table. */
    public static class Entry<K, V> implements Map.Entry<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        @Override
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

	/**
         * Return a String representation of the Entry
         * @return a String representation of the Entry
         *         in the form key = value
         */
        @Override
        public String toString() {
            return key.toString() + "," + value.toString();
        }
        
    }

    @Override
    public int size() {
	//does this really need explaining?
	int sizerino = 0;
	for(int i =0; i  < tableSize; i++){
	    if(null != table1[i]){
		sizerino++;
	    }
	    if(null != table2[i]){
		sizerino++;
	    }
	}
	return sizerino+overflow.size();
    }

    @Override
    public boolean isEmpty() {
	return size() == 0;
    }

    private int getHash(Object o, int num){
	//puts the hash in hashbrowns
	int hasherino = o.hashCode();
	if(num == 0){
	    if(hasherino%tableSize < 0){
		return tableSize+(hasherino%tableSize);
	    }else{
		return hasherino%tableSize;
	    }
	}else if(num == 1){
	    hasherino = hasherino<<16|hasherino>>>16;
	    if(hasherino%tableSize < 0){
		return tableSize+(hasherino%tableSize);
	    }else{
		return hasherino%tableSize;
	    }
	}else{
	    return -1;
	}
    }

    @Override
    public V get(Object key) {
	//checks the only two spots the key can be and the overflow list and returns its value
	if(null != table1[getHash(key, 0)] && table1[getHash(key, 0)].getKey().equals(key)){
	    return table1[getHash(key, 0)].getValue();
	}else if(null != table2[getHash(key, 1)] && table2[getHash(key, 1)].getKey().equals(key)){
	    return table2[getHash(key, 1)].getValue();
	}else{
	    for(int i = 0; i < overflow.size(); i++){
		if(overflow.get(i).getKey().equals(key)){
		    return overflow.get(i).getValue();
		}
	    }
	}
	return null;
    }
  
    @Override
    public V put(K key, V value) {
        Entry entryStatic = new Entry(key, value);
	Entry entry = new Entry(key, value);
	ArrayList visited1 = new ArrayList();
	ArrayList visited2 = new ArrayList();

	if(null== get(entry.getKey())){
	    //I love risking it all with infinite loops
	    while(true){
		//stops once there is nothing to place anymore or if there is an infinite cycle detected
		if(visited1.contains(getHash(entry.getKey(), 0))){
		    overflow.add(entry);
		    break;
		}
		else if(table1[getHash(entry.getKey(), 0)] == null){
		    table1[getHash(entry.getKey(), 0)] = entry;
		    break;
		}else{
		    visited1.add(getHash(entry.getKey(), 0));
		    Entry temp = table1[getHash(entry.getKey(), 0)];
		    table1[getHash(entry.getKey(), 0)] = entry;
		    entry = temp;

		    if(visited2.contains(getHash(entry.getKey(), 1))){
			overflow.add(entry);
			break;
		    }
		    else if(table2[getHash(entry.getKey(), 1)] == null){
			table2[getHash(entry.getKey(), 1)] = entry;
			break;
			
		    }else{
			visited2.add(getHash(entry.getKey(), 1));
			temp = table2[getHash(entry.getKey(), 1)];
			table2[getHash(entry.getKey(), 1)] = entry;
			entry = temp;
		    }
		}
	    }
	    return null;
	}else{
	    //happens if the entry is already in the lists and updates the value
	    if(table1[getHash(entry.getKey(), 0)].getKey().equals(entry.getKey())){
		table1[getHash(entry.getKey(), 0)].value = (V) entry.getValue();
		return (V) entryStatic.getValue();
	    }else if(table2[getHash(entry.getKey(), 1)].getKey().equals(entry.getKey())){
		table2[getHash(entry.getKey(), 1)].value = (V) entry.getValue();
		return (V) entryStatic.getValue();
	    }else{
		for(int i = 0; i < overflow.size(); i++){
		    if(overflow.get(i).getKey().equals(entry.getKey())){
			overflow.get(i).value = (V) entry.getValue();
			return (V) entryStatic.getValue();
		    }
		}
	    }
	    return (V) entryStatic.getValue();
	}
    }
    @Override
    public V remove(Object key) {
	Entry temp;
	//checks where it is and then smites it out of existence and replaces it with an overflow value if there is one for that spot
	if(key.equals(table1[getHash(key, 0)].getKey())){
	    temp = table1[getHash(key, 0)];
	    table1[getHash(key, 0)] = null;
	    for(int i = 0; i < overflow.size(); i++){
		if(getHash(overflow.get(i).getKey(), 0) == getHash(key, 0)){
		    table1[getHash(key, 0)] = overflow.get(i);
		    overflow.remove(i);
		    break;
		}
	    }
	    return (V) temp.getValue();
	}else if(key.equals(table2[getHash(key, 1)].getKey())){
	    temp = table2[getHash(key, 1)];
	    table2[getHash(key, 1)] = null;
	    for(int i = 0; i < overflow.size(); i++){
		if(getHash(overflow.get(i).getKey(), 1) == getHash(key, 1)){
		    table2[getHash(key, 1)] = overflow.get(i);
		    overflow.remove(i);
		    break;
		}
	    }
	    return (V) temp.getValue();
	}else{
	    for(int i = 0; i < overflow.size(); i++){
		if(key.equals(overflow.get(i).getKey())){
		    temp = overflow.get(i);
		    overflow.remove(i);
		    return (V) temp.getValue();
		}
	    }
	}
	return null;
    }

    @Override
    public String toString() {
	//Makes stringerinis as fast as you can say the word linguini
	//iterates throw everything and formats it properly
	StringBuilder sb = new StringBuilder();
	for(int i = 0; i < table1.length; i++){
	    if(table1[i] != null){
		sb.append("["+i+","+table1[i].getKey()+","+table1[i].getValue()+",table1"+"]"+"\n");
	    }
	}
	for(int i = 0; i < table1.length; i++){
	    if(table2[i] != null){
		sb.append("["+i+","+table2[i].getKey()+","+table2[i].getValue()+",table2"+"]"+"\n");
	    }
	}
	for(int i = 0; i < overflow.size(); i++){
	    sb.append("["+i+","+overflow.get(i).getKey()+","+overflow.get(i).getValue()+",overflow"+"]"+"\n");
	}
	return sb.toString();
    }
   

}
