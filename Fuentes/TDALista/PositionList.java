package TDALista;

/**
 * Interface para una lista iterable.
 */
public interface PositionList<E> extends Iterable {
	/**
	 * Devuelve el numero de elementos de la lista.
	 * @return el numero de elementos de la lista.
	 */
	int size();
	
	/**
	 * Devuelve true si la lista esta vacia y false en caso contrario.
	 * @return true si la lista esta facia y false en caso contrario.
	 */
	boolean isEmpty();
	
	/**
	 * Devuelve la primer posicion de la lista.
	 * Error si la lista esta vacia.
	 * @return la primer posicion de la lista
	 * @throws EmptyListException si la lista esta vacia.
	 */
	Position<E> first() 
			throws EmptyListException;
	
	/**
	 * Devuelve la ultima posicion de la lista.
	 * Error si la lista esta vacia.
	 * @return la ultima posicion de la lista.
	 * @throws EmptyListException si la lista esta vacia.
	 */
	Position<E> last() 
			throws EmptyListException;
	
	/**
	 * Devuelve la posicion siguiente a la pasada por parametro.
	 * Error si la posicion es invalida o si la posicion pasada no tiene siguiente.
	 * @param p posicion de la cual queremos obtener la siguiente.
	 * @return la posicion siguiente a la pasada por parametro.
	 * @throws InvalidPositionException si la posicion pasada es invalida.
	 * @throws BoundaryViolationException si la posicion pasada no tiene siguiente.
	 */
	Position<E> next(Position<E> p) 
			throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la posicion anterior a la pasada por parametro.
	 * Error si la posicion es invalida o si la posicion pasada no tiene anterior.
	 * @param p posicion de la cual queremos obtener la anterior.
	 * @return la posicion anterior a la pasada por parametro.
	 * @throws InvalidPositionException si la posicion pasada es invalida.
	 * @throws BoundaryViolationException si la posicion pasada no tiene anterior.
	 */
	Position<E> prev(Position<E> p) 
			throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Reemplaza al elemento almacenado en p con el elemento pasado 
	 * por parametro, retornando el elemento almacenado anteriormente,
	 * Error si la posicion es invalida
	 * @param p posicion a la que vamos a reemplazar el elemento.
	 * @param e nuevo elemento de la posicion p.
	 * @return el elemento previamente almacenado en p.
	 * @throws InvalidPositionException si la posicion p es invalida.
	 */
	E set(Position<E> p, E e)
			throws InvalidPositionException;
	
	/**
	 * Inserta un nuevo elemento e como primera posicion.
	 * @param e elemento a insertar.
	 */
	void addFirst(E e);
	
	/**
	 * Inserta un nuevo elemento e como ultima posicion.
	 * @param e elemento a insertar.
	 */
	void addLast(E e);
	
	/**
	 * Inserta un nuevo elemento e antes de la posicion p.
	 * Error si la posicion es invalida.
	 * @param p posicion siguiente de la posicion se insertara el elemento e.
	 * @param e elemento a insertar antes de la posicion p.
	 * @throws InvalidPositionException si la posicion es invalida.
	 */
	void addBefore(Position<E> p, E e) 
		throws InvalidPositionException;
	
	/**
	 * Inserta un nuevo elemento e luego de la posicion p.
	 * Error si la posicion es invalida.
	 * @param p posicion
	 * @param e
	 * @throws InvalidPositionException
	 */
	void addAfter(Position<E> p, E e)
		throws InvalidPositionException;
	
	/**
	 * Elimina y retorna el elemento de la posicion p invalidando la posicion p.
	 * Error si la posicion es invalida.
	 * @throws EmptyListException 
	 */
	E remove(Position<E> p) throws InvalidPositionException;

	/**
	 * Devuelve una coleccion iterable de las posiciones de la lista.
	 * @return una coleccion iterable de las posiciones de la lista.
	 */
	public Iterable<Position<E>> positions();
}