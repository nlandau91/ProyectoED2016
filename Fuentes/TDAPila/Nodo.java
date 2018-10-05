package TDAPila;

/**
 * Nodo de una pila simplemente enlazada.
 * Almacena una referencia a su elemento y al siguiente nodo.
 */
public class Nodo<E> {
	private E elemento;
	private Nodo<E> siguiente;
	
	/**
	 * Crea un nodo con referencias nulas al elemento y al nodo siguiente.
	 */
	public Nodo() { 
		this(null, null); 
	}
	
	/**
	 * Crea un nodo con referencia al item pasado por parametro y nula al siguiente.
	 * @param item el elemento del nodo creado.
	 */
	public Nodo( E item ) {
		this(item,null); 
	}
	
	/**
	 * Crea un nodo con referencia al item y al siguiente nodo ambos pasados por parametro.
	 * @param item el elemento del nodo creado.
	 * @param sig el siguiente nodo del nodo creado.
	 */
	public Nodo( E item, Nodo<E> sig ){ 
		elemento=item; 
		siguiente=sig;
	}
	
	/**
	 * Devuelve el elemento almacenado
	 * @return el elemento almacenado
	 */
	public E getElemento(){ 
		return elemento;
	}
	
	/**
	 * Actualiza el elemento almacenado por el pasado por parametro.
	 * @param elemento el nuevo elemento del nodo.
	 */
	public void setElemento( E elemento ){ 
		this.elemento=elemento;
	}
	
	/**
	 * Devuelve el siguiente nodo.
	 * @return el siguiente nodo.
	 */
	public Nodo<E> getSiguiente(){ 
		return siguiente;
	}
	
	/**
	 * Actualiza el siguiente nodo por el pasado por parametro.
	 * @param siguiente el nuevo nodo siguiente.
	 */
	public void setSiguiente( Nodo<E> siguiente ) {
		this.siguiente = siguiente; 
	} 
}