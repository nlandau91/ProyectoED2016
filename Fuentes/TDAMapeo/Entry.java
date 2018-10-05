package TDAMapeo;

/**
 * Interface para una dupla de elementos genericos.
 */
public interface Entry<K,V> {

	/**
	 * Devuelve la key.
	 * @return la key.
	 */
	K getKey();
	
	/**
	 * Devuelve el value.
	 * @return el value.
	 */
	V getValue();
	
	/**
	 * Cambia la key actual por la pasada por parametro.
	 * @param newKey nueva key que reemplazara a la key actual.
	 */
	void setKey(K newKey);
	
	/**
	 * Cambia el value actual por el pasado por parametro.
	 * @param newValue nuevo value que reemplazara al value actual.
	 */
	void setValue(V newValue);
}