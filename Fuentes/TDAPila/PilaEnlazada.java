package TDAPila;

/*
 * Implementacion de la interface Stack mediante una estructura simplemente enlazada.
 */
public class PilaEnlazada<E> implements Stack<E> {
	protected Nodo<E> head;
	protected int tama�o;
	
	/*
	 * Construye una pila vacia.
	 */
	public PilaEnlazada(){
		head = null;
		tama�o = 0;
	}

	public void push(E item) {
		Nodo<E> aux = new Nodo<E>();
		aux.setElemento(item);
		aux.setSiguiente(head);
		head = aux;
		tama�o++;

	}


	public boolean isEmpty() {
		return tama�o == 0;
	}

	public E pop() throws EmptyStackException {
		if(isEmpty()){
			throw new EmptyStackException("Pila vacia");
		}
		else{
			E aux = head.getElemento();
			head = head.getSiguiente();
			tama�o--;
			return aux;
		}
	}

	public E top() throws EmptyStackException {
		if(tama�o == 0){
			throw new EmptyStackException("Pila vacia");
		}
		else{
			return head.getElemento();
		}
	}

	public int size() {
		return tama�o;
	}
	
	public String toString() {
	    String s;
	    Nodo<E> cur = null;
	    s = "[";
	    int n = size();
	    if (n > 0) {
	      cur = head;
	      s += cur.getElemento();
	    }
	    if (n > 1)
	      for (int i = 1; i <= n-1; i++) {
		cur = cur.getSiguiente();
		s += ", " + cur.getElemento();
	      }
	    s += "]";
	    return s;
	  }

}