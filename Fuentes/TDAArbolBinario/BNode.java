package TDAArbolBinario;

/**
 * Implementacion de un nodo de un arbol binario
 * Mantiene una referencia al padre y a los hijos izquierdo y derecho ademas de almacenar un elemento.
 */
public class BNode<E> implements Position<E> {

	private BNode<E> parent, left, right;
	private E elem;
	
	
	/**
	 * Crea un nodo con todas sus referencias pasadas por por parametro.
	 * @param parent padre del nodo.
	 * @param left hijo izquierdo.
	 * @param right hijo derecho.
	 * @param elem elemento a almacenar.
	 */
	public BNode(BNode<E> parent, BNode<E> left, BNode<E> right, E elem){
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.elem = elem;
	}
	
	/**
	 * Crea un nodo con todas sus referencias nulas.
	 */
	public BNode(){
		this(null, null, null, null);
	}
	
	/**
	 * Actualiza el elemento del nodo.
	 * @param e nuevo elemento del nodo.
	 */
	public void setElement(E e){
		elem = e;
	}
	
	/**
	 * Actualiza la referencia al padre.
	 * @param newParent nuevo padre.
	 */
	public void setParent(BNode<E> newParent){
		parent = newParent;
	}
	
	/**
	 * Actualiza el hijo derecho.
	 * @param newRight nuevo hijo derecho.
	 */
	public void setRight(BNode<E> newRight){
		right = newRight;
	}
	
	/**
	 * Actualiza el hijo izquierdo.
	 * @param newLeft nuevo hijo izquierdo.
	 */
	public void setLeft(BNode<E> newLeft){
		left = newLeft;
	}	
	
	public E element() {
		return elem;
	}

	/**
	 * Devuelve el padre del nodo.
	 * @return el padre del nodo.
	 */
	public BNode<E> getParent(){
		return parent;
	}
	
	/**
	 * Devuelve el hijo izquierdo del nodo.
	 * @return el hijo izquierdo del nodo.
	 */
	public BNode<E> getLeft(){
		return left;
	}
	
	/**
	 * Devuelve el hijo derecho del nodo.
	 * @return el hijo derecho del nodo.
	 */
	public BNode<E> getRight(){
		return right;
	}
	
}