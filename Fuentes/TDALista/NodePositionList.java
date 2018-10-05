package TDALista;

import java.util.Iterator;

/**
 * Implementacion de la interface PositionList mediante una estructura
 * doblemente enlazada con referencia al primer y ultimo elemento.
 */
public class NodePositionList<E> implements PositionList<E> {
	
	protected int size;
	protected DNode<E> header;
	protected DNode<E> trailer;
	
	/**
	 * Crea una cola vacia.
	 */
	public NodePositionList(){
		header = new DNode<E>();
		trailer = new DNode<E>();
		header.setNext(trailer);
		trailer.setPrev(header);
		size = 0;
	}
	
	
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	
	public int size() {
		return size;
	}

	
	public boolean isEmpty() {
		return size() == 0;
	}

	
	public Position<E> first() throws EmptyListException {
		if(isEmpty()){
			throw new EmptyListException("lista vacia");
		}
		return header.getNext();
	}

	
	public Position<E> last() throws EmptyListException {
		if(isEmpty()){
			throw new EmptyListException("lista vacia");
		}
		return trailer.getPrev();
	}

	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> n = checkPosition(p);
		if(n.getNext() == trailer){
			throw new BoundaryViolationException("el ultimo elemento no tiene siguiente");
		}
		return n.getNext();
	}

	
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<E> n = checkPosition(p);
		if(n.getPrev() == header){
			throw new BoundaryViolationException("el primer elemento no tiene anterior");
		}
		return n.getPrev();
	}

	
	public E set(Position<E> p, E e) throws InvalidPositionException {
		if(isEmpty()){
			throw new InvalidPositionException("Lista vacia.");
		}
		DNode<E> n = checkPosition(p);
		E toReturn = n.element();
		n.setElement(e);
		return toReturn;
	}

	
	public void addFirst(E e) {
		DNode<E> n = new DNode<E>(header, header.getNext(), e);
		n.getPrev().setNext(n);
		n.getNext().setPrev(n);
		size++;
	}

	
	public void addLast(E e) {
		DNode<E> n = new DNode<E>(trailer.getPrev(), trailer, e);
		n.getPrev().setNext(n);
		n.getNext().setPrev(n);
		size++;
		

	}

	
	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		DNode<E> n = checkPosition(p);
		DNode<E> v = new DNode<E>(n.getPrev(), n, e);
		v.getPrev().setNext(v);
		v.getNext().setPrev(v);
		size++;

	}

	
	public void addAfter(Position<E> p, E e) throws InvalidPositionException {
		DNode<E> n = checkPosition(p);
		DNode<E> v = new DNode<E>(n, n.getNext(), e);
		v.getPrev().setNext(v);
		v.getNext().setPrev(v);
		size++;
	}

	
	public E remove(Position<E> p) throws InvalidPositionException{
		if(isEmpty()){
			throw new InvalidPositionException("Lista vacia");
		}
		DNode<E> n = checkPosition(p);
		n.getPrev().setNext(n.getNext());
		n.getNext().setPrev(n.getPrev());
		size--;
		return n.element();
	}
	
	
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> P = new NodePositionList<Position<E>>();
		if(!isEmpty()){
			try {
				
				Position<E> p = first();
				P.addFirst(p);
				while(p != last()){
					p = next(p);
					P.addLast(p);
				}
				
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return P;
	}
	
	/**
	 * Devuelve un String que representa el contenido de la lisa.
	 */
	public String toString() {
	    Iterator<E> it = iterator();
	    String s = "[";
	    while (it.hasNext()) {
	      s += it.next();
	      if (it.hasNext())
		s += ", ";
	      }
	    s += "]";
	    return s;
	  }
	
	protected DNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (p == null){
			throw new InvalidPositionException("null");
		}
		if (p == header){
			throw new InvalidPositionException("header");
		}
		if (p == trailer){
			throw new InvalidPositionException("trailer");
		}
		try{
			DNode<E> n = (DNode<E>)p;
			if(n.getNext() == null || n.getPrev() == null ){
				throw new InvalidPositionException("posicion invalida");
			}
			return n;
		}
		catch (ClassCastException e){
			throw new InvalidPositionException("tipo invalido");
		}
	}		
	
}