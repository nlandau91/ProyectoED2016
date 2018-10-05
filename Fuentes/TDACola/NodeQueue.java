package TDACola;

/**
 * Implementacion de la interface Queue mediante una estructura simplemente enlazada.
 */
public class NodeQueue<E> implements Queue<E> {

	protected Node<E> head, tail;
	protected int size;         

	/**
	 * Crea una cola vacia.
	 */
	public NodeQueue() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {       
		return size;
	}

	public boolean isEmpty() {     
		return size == 0;
	}


	public void enqueue(E elem) {
		Node<E> node = new Node<E>();
		node.setElement(elem);
		node.setNext(null);
		if (size == 0){
			head = node; 
		}
		else{
			tail.setNext(node);
		}
		tail = node;
		size++;
	}

	public E front()throws EmptyQueueException {
		if (size == 0)
			throw new EmptyQueueException("Cola vacia.");
		return head.getElement();
	}

	public E dequeue() throws EmptyQueueException {
		if (size == 0){
			throw new EmptyQueueException("Cola vacia.");
		}
		E tmp = head.getElement();
		head = head.getNext();
		size--;
		if (size == 0){
			tail = null;
		}
		return tmp;
	}

	public String toString() {
		String s = "";
		s += "(";
		if (!isEmpty()) {
			Node<E> p = head;
			do {
				s += p.getElement() ;
				if (p != tail)
					s += ", ";
				p = p.getNext();
			} while (p != null); 
		}
		s += ")";
		return s;
	}

}