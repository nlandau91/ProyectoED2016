package TDAMapeo;

/**
 * Implementacion de la interface Map utilizando una lista para almacenar las entradas.
 */
import java.util.Iterator;
import TDALista.*;

public class MapConLista<K, V> implements Map<K, V> {

	protected NodePositionList<Entrada<K,V>> entradas;

	/**
	 * Crea un map vacio.
	 */
	public MapConLista(){
		entradas = new NodePositionList<Entrada<K,V>>();
	}

	@Override
	public int size() {
		return entradas.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		V aux = null;
		for( Position<Entrada<K,V>> p : entradas.positions()){
			if(p.element().getKey().equals(key)){
				aux = p.element().getValue();
				p.element().setValue(value);
			}

		}
		if(aux == null){
			entradas.addLast(new Entrada<K,V>(key,value));	
		}
		return aux;




	}

	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		for(Position<Entrada<K,V>> p: entradas.positions()){
			if(p.element().getKey().equals(key)){
				return p.element().getValue();
			}
		}
		return null;

	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		V toReturn = null;
		for(Position<Entrada<K,V>> p : entradas.positions()){
			if(p.element().getKey().equals(key)){
				toReturn = p.element().getValue();
				try {
					entradas.remove(p);
				} catch (InvalidPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return toReturn;

	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> keys = new NodePositionList<K>();
		for(Position<Entrada<K,V>> p : entradas.positions()){
			keys.addLast(p.element().getKey());
		}
		return keys;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> values = new NodePositionList<V>();
		for(Position<Entrada<K,V>> p : entradas.positions()){
			values.addLast(p.element().getValue());
		}
		return values;
	}
	
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entrada<K,V>> entries = new NodePositionList<Entrada<K,V>>();
		for(Position<Entrada<K,V>> p : entradas.positions()){
			entries.addLast(p.element());
		}

		return entries;
	}

	protected void checkKey(K key) throws InvalidKeyException{
		if(key == null){
			throw new InvalidKeyException("key nula");
		}
	}
}