package TDACola;

/**
 * Node de una pila simplemente enlazada.
 * Almacena una referencia a su elemento y al siguiente nodo.
 */
public class Node<E> {
	private E elemento;
	private Node<E> siguiente;
	
	/**
	 * Crea un nodo con referencias nulas al elemento y al nodo siguiente.
	 */
	public Node() { 
		this(null, null); 
	}
	
	/**
	 * Crea un nodo con referencia al item pasado por parametro y nula al siguiente.
	 * @param item el elemento del nodo creado.
	 */
	public Node( E item ) {
		this(item,null); 
	}
	
	/**
	 * Crea un nodo con referencia al item y al siguiente nodo ambos pasados por parametro.
	 * @param item el elemento del nodo creado.
	 * @param sig el siguiente nodo del nodo creado.
	 */
	public Node( E item, Node<E> sig ){ 
		elemento=item; 
		siguiente=sig;
	}
	
	/**
	 * Devuelve el elemento almacenado
	 * @return el elemento almacenado
	 */
	public E getElement(){ 
		return elemento;
	}
	
	/**
	 * Actualiza el elemento almacenado por el pasado por parametro.
	 * @param elemento el nuevo elemento del nodo.
	 */
	public void setElement( E elemento ){ 
		this.elemento=elemento;
	}
	
	/**
	 * Devuelve el siguiente nodo.
	 * @return el siguiente nodo.
	 */
	public Node<E> getNext(){ 
		return siguiente;
	}
	
	/**
	 * Actualiza el siguiente nodo por el pasado por parametro.
	 * @param siguiente el nuevo nodo siguiente.
	 */
	public void setNext( Node<E> siguiente ) {
		this.siguiente = siguiente; 
	} 
}