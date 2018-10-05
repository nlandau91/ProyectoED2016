package TDAMapeo;

/**
 * Implementacion de la interface Entry que modela una dupla de elementos genericos.
 */
public class Entrada<K,V> implements Entry<K,V> {

	private K key;
	private V value;
	
	/**
	 * Crea una Entrada con key y value pasados por parametro.
	 * @param key
	 * @param value
	 */
	public Entrada(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public void setKey(K newKey){
		key = newKey;
	}
	
	public void setValue(V newValue){
		value = newValue;
	}
	
	public K getKey(){
		return key;
	}
	
	public V getValue(){
		return value;
	}

	public String toString(){
		return "(" + key  + "," + value + ")";
	}
}