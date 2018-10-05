package TDAPila;

/**
 * Interface para una coleccions cuyos elementos son insertados
 * y removidos en orden LIFO (last-int first-out)
 */
public interface Stack<E> {
	
	/**
	 * Inserta item en el tope de la pila.
	 * @param item a insertar en el tope de la pila
	 */
	void push(E item);
	
	/**
	 * Retorna true si la pila esta vacia y false en caso contrario.
	 * @return true si la pila esta vacia y false en caso contrario.
	 */
	boolean isEmpty();
	
	/**
	 * Elimina el elemento del tope de la pila y lo retorna.
	 * Produce un error si la pila esta vacia.
	 * @return el elemento del tope de la pila.
	 * @throws EmptyStackException si la pila esta vacia.
	 */
	E pop() throws EmptyStackException;
	
	/**
	 * Retorna el elemento del tope de la pila.
	 * Produce un error si la pila esta vacia.
	 * @return el elemento del tope de la pila.
	 * @throws EmptyStackException si la pila esta vacia.
	 */
	E top() throws EmptyStackException;
	
	/**
	 * Retorna la cantidad de elementos de la pila.
	 * @return la cantidad de elementos de la pila.
	 */
	int size();
}