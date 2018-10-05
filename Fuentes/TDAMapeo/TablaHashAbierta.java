package TDAMapeo;

import TDALista.*;


/**
 * Una tabla de hash abierto que almacena las entradas en un arreglo de mapeos
 */
public class TablaHashAbierta<K, V> implements Map<K, V> {

	protected Map<K,V>[] A;
	protected int size;
	protected int N = 7919;

	/**
	 * Crea una tabla de hash abierta vacia;
	 */
	public TablaHashAbierta(){
		size = 0;
		//A = (Map<K,V>[]) new MapConLista<K,V>();
		A = (MapConLista<K,V>[]) new MapConLista[N];
		for(int i = 0; i< N; i++){
			A[i] = new MapConLista<K,V>();
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		V toReturn = A[h(key)].put(key, value);
		if(toReturn == null){
			size++;
		}
		if(size > N/2){
			rehash();
		}
		return toReturn;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		return A[h(key)].get(key);
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		V toReturn = A[h(key)].remove(key);
		if(toReturn != null){
			size--;
		}
		return toReturn;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> toReturn = new NodePositionList<K>();
		for(Map<K,V> map : A){
			Iterable<K> keys = map.keys();
			for(K key : keys){
				toReturn.addLast(key);
			}
		}
		return toReturn;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> toReturn = new NodePositionList<V>();
		for(Map<K,V> map : A){
			Iterable<V> values = map.values();
			for(V value : values){
				toReturn.addLast(value);
			}
		}
		return toReturn;
	}

	@Override
	public Iterable<Entry<K,V>> entries() {
		PositionList<Entry<K,V>> toReturn = new NodePositionList<Entry<K,V>>();
		for(Map<K,V> map : A){
			Iterable<Entry<K,V>> entries = map.entries();
			for(Entry<K,V> entry : entries){
				toReturn.addLast(entry);
			}
		}
		return toReturn;
	}

	protected int h(K key){		
		return Math.abs(key.hashCode()) % N;
	}

	protected void rehash(){

		Iterable<Entry<K,V>> entries = entries();
		N = N*2+1; //El nuevo N no necesariamente sera un numero primo.
		A = (MapConLista<K,V>[]) new MapConLista[N];
		for(int i = 0; i< N; i++){
			A[i] = new MapConLista<K,V>();
		}
		size = 0;
		try{
			for(Entry<K,V> entry : entries){
				put(entry.getKey(), entry.getValue());

			}
		}catch(InvalidKeyException e){
			e.printStackTrace();

		}
	}

	protected void checkKey(K key) throws InvalidKeyException{
		if(key == null){
			throw new InvalidKeyException("key nula");
		}
	}

}