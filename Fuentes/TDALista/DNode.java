package TDALista;

/**
 * Nodo de una lista doblemente enlazada.
 * Implementa la interface Position.
 */
public class DNode<E> implements Position<E> {
	
	protected DNode<E> next;
	protected DNode<E> prev;
	protected E element;
	
	/**
	 * Crea un nodo con referencias al siguiente nodo, al nodo anterior
	 * y al elemento que almacena.
	 * @param prev nodo anteior.
	 * @param next nodo siguiente.
	 * @param element elemento almacenado.
	 */
	public DNode (DNode<E> prev, DNode<E> next, E element) {
		this.next = next;
		this.prev = prev;
		this. element = element;
	}
	
	/**
	 * Cera un nodo con referencias nulas.
	 */
	public DNode() {
		this(null, null, null);
	}
	
	/**
	 * Actualiza el nodo siguiente por el pasado por parametro.
	 * @param next nuevo nodo siguiente.
	 */
	public void setNext(DNode<E> next){
		this.next = next;
	}
	
	/**
	 * Actualiza el nodo anterior por el pasado por parametro.
	 * @param prev el nuevo nodo anteiror.
	 */
	public void setPrev(DNode<E> prev){
		this.prev = prev;
	}
	
	/**
	 * Devuelve el nodo anterior.
	 * @return el nodo anterior.
	 */
	public DNode<E> getNext(){
		return next;
	}
	
	/**
	 * Devuelve el nodo siguiente.
	 * @return el nodo siguiente.
	 */
	public DNode<E> getPrev(){
		return prev;
	}
	
	/**
	 * Actualiza el elemento almacenado por el pasado por parametro.
	 * @param element el nuevo elemento a almacenar.
	 */
	public void setElement(E element){
		this.element = element;
	}
	

	public E element() {
		return element;
	}

}