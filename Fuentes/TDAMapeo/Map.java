package TDAMapeo;

/**
 * Interface para un mapeo.
 */
public interface Map<K, V> {

	/**
	 * Devuelve el numero de elementos en el mapeo.
	 * @return el numero de elementos en el mapeo.
	 */
	public int size();
	
	/**
	 * Devuelve true si el mapeo esta vacio y falso en caso contrario.
	 * @return trie so el mapeo esta vacio y falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/** 
	 * Crea una nueva entrada con los valores del par key-value.
	 * y la pone en el mapeo, si ya existia una, la reemplaza.
	 * y devuelve su value.
	 * @param key key de la nueva entrada.
	 * @param value value de la nueva entrada.
	 * @return el valor de la entrada reemplazada.
	 * @throws InvalidKeyException si la key es invalida.
	 */
	public V put(K key, V value) throws InvalidKeyException;
	
	/**
	 * Devuelve el value asociado a la key pasada por parametro.
	 * @param key key asociada al value a devolver.
	 * @return value value asociado a la key pasada por parametro.
	 * @throws InvalidKeyException si la key es invalida.
	 */
	public V get(K key) throws InvalidKeyException;
	
	/** 
	 * Remueve el par key-value correspondiente a la key pasada por parametro.
	 * @param key key del par key-value a remover.
	 * @return el value asociado al a key pasada por parametro.
	 * @throws InvalidKeyException si la key es invalida.
	 */
	public V remove(K key) throws InvalidKeyException;
	
	/** 
	 * Devuelve un objeto iterable de todas las keys del mapeo.
	 * @return un objeto iterable de todas las keys del mapeo.
	 */
	public Iterable<K> keys();
	
	/** 
	 * Devuelve un objeto iterable de todos los value del mapeo.
	 * @return un objeto iterable de todos los value del mapeo.
	 */
	public Iterable<V> values();
	
	/** 
	 * Devuelve un objeto iterable de todas las entradas del mapeo.
	 * @return un objeto iterable de todas las entradas del mapeo.
	 */	 
	public Iterable<Entry<K,V>> entries();  
	}