package TDACola;

/*
 * Interface para una coleccion cuyos elementos son insertados
 * y removidos en orden FIFO (first-in first-out)
 */
public interface Queue<E> {
	/**
	 * inserta el elemento e al final de la cola.
	 * @param e elemento a insertal al final de la cola.
	 */
	void enqueue(E e);
	
	/**
	 * Elimina el elemento del frente de la cola y lo retorna.
	 * Si la cola esta vacia se produce un error.
	 * @return el elemento del frente de la cola.
	 * @throws EmptyQueueException si la cola esta vacia.
	 */
	E dequeue() throws EmptyQueueException;
	
	/**
	 * Retorna el elemento del frente de la cola sin eliminarlo.
	 * Si la  cola esta vacia se produce un error.
	 * @return el elemento del frente de la cola.
	 * @throws EmptyQueueException si la cola esta vacia.
	 */
	E front() throws EmptyQueueException;
	
	/**
	 *Retorna true si la cola no tiene elementos false en caso contrario.
	 * @return true si la cola no tiene elementos y false en caso contrario.
	 */
	boolean isEmpty();
	
	/**
	 * Retorna la cantidad de elementos de la cola.
	 * @return la cantidad de elementos de la cola.
	 */
	int size();
}