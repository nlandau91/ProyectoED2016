package TDAArbolBinario;

import java.util.Iterator;

import TDALista.*;

/**
 * Implementacion de la interface BinaryTree.
 */
public class ArbolBinario<E> implements BinaryTree<E> {

	private BNode<E> root;
	private int size;

	/**
	 * Crea un arbol vacio.
	 */
	public ArbolBinario(){
		root = null;
		size = 0;
	}

	public Position<E> createRoot(E e) throws InvalidOperationException{
		if(root != null){
			throw new InvalidOperationException("El arbol ya tiene una raiz");
		}
		root = new BNode<E>();
		root.setElement(e);
		size++;
		return root;
	}

	@Override
	public Position<E> left(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		BNode<E> n = checkPosition(p);
		if( n.getLeft() == null ){			
			throw new BoundaryViolationException("La posicion no tiene hijo izquierdo");
		}

		return n.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		BNode<E> n = checkPosition(p);
		if( n.getRight() == null ){			
			throw new BoundaryViolationException("La posicion no tiene hijo derecho");
		}

		return n.getRight();
	}

	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		BNode<E> n = checkPosition(p);
		return n.getLeft() != null;
	}

	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		BNode<E> n = checkPosition(p);
		return n.getRight() != null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E replace(Position<E> p, E e) throws InvalidPositionException {
		BNode<E> n = checkPosition(p);
		E toReturn = n.element();
		n.setElement(e);
		return toReturn;

	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(root == null){
			throw new EmptyTreeException("Arbol vacio");
		}
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		BNode<E> n = checkPosition(p);
		if(n == root){
			throw new BoundaryViolationException("La raiz no tiene padre");
		}
		return n.getParent();
	}

	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		return hasLeft(p) || hasRight(p);
	}

	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		return !isInternal(p);
	}

	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		BNode<E> n = checkPosition(p);
		return root == n;
	}

	public int altura() throws EmptyTreeException{
		try {
			return altura(root);
		} catch (InvalidPositionException e) {
			throw new EmptyTreeException("arbol vacio");
		}
	}

	public int altura(Position<E> v) throws InvalidPositionException{
		BNode<E> n = checkPosition(v);
		int h = 0;
		if(isInternal(v)){
			for(Position<E> p : children(v)){
				h = Math.max(h, altura(p));
			}
			h++;
		}
		return h;
	}

	public int cantHojas(){
		int toReturn = 0;
		try{
			for(Position<E> pos : positions()){
				if(isExternal(pos)){
					toReturn++;
				}
			}
		}catch(InvalidPositionException e){
			e.printStackTrace();
		}


		return toReturn;
	}

	public int cantInternos(){
		int toReturn = 0;
		try{
			for(Position<E> pos : positions()){
				if(isInternal(pos)){
					toReturn++;
				}
			}
		}catch(InvalidPositionException e){
			e.printStackTrace();
		}
		return toReturn;
	}

	public boolean esPropio(){
		return esPropioAux(root);
	}

	protected boolean esPropioAux(Position<E> r){
		boolean es = false;
		try {
			if(isExternal(r)){
				es = true;
			}
			else{
				BNode<E> n = checkPosition(r);
				if(n.getLeft() != null && n.getRight() != null){
					es = true && esPropioAux(n.getLeft()) && esPropioAux(n.getRight());
				}
			}
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return es;
	}




	private BNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if(p == null){
			throw new InvalidPositionException("Posicion nula");
		}
		else try{
			return (BNode<E>)p;
		}catch(ClassCastException e){
			throw new InvalidPositionException("La posicion no es un nodo compatible");
		}
	}

	@Override
	public Iterator<E> iterator() {		
		PositionList<E> elements = new NodePositionList<E>();
		for(Position<E> pos: positions()){
			elements.addLast(pos.element());
		}				
		return elements.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
		if(size != 0)
			try {
				preorderPositions(root(), positions);
			} catch (InvalidPositionException | EmptyTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  // assign positions in preorder
		return positions;
	}

	protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException{
		pos.addLast(v);
		for(Position<E> w : children(v)){
			preorderPositions(w, pos);
		}
	}
	
	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		BNode<E> n = checkPosition(v);
		NodePositionList<Position<E>> children = new NodePositionList<Position<E>>();
		if(n.getLeft() != null){
			children.addFirst(n.getLeft());
		}
		if(n.getRight() != null){
			children.addLast(n.getRight());
		}
		return children;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BNode<E> n = checkPosition(v);
		if(n.getLeft() != null){
			throw new InvalidOperationException("El nodo ya tiene un hijo izquierdo");
		}
		BNode<E> hi = new BNode<E>();
		hi.setElement(r);
		size++;
		return hi;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BNode<E> n = checkPosition(v);
		if(n.getLeft() != null){
			throw new InvalidOperationException("El nodo ya tiene un hijo derecho");
		}
		BNode<E> hd = new BNode<E>();
		hd.setElement(r);
		size++;
		return hd;
	}

	@Override
	public E remove(Position<E> v)throws InvalidPositionException, InvalidOperationException {
		BNode<E> vv = checkPosition(v);
		BNode<E> leftPos = vv.getLeft();
		BNode<E> rightPos = vv.getRight();
		if (leftPos != null && rightPos != null){
			throw new InvalidOperationException("No se puede remover un nodo con dos hijos.");
		}
		BNode<E> ww;
		if (leftPos != null){
			ww = leftPos;
		}
		else if (rightPos != null){
			ww = rightPos;
		}
		else{
			ww = null;
		}
		if (vv == root) {
			if (ww != null){
				ww.setParent(null);
			}
			root = ww;
		}
		else { 
			BNode<E> uu = vv.getParent();
			if (vv == uu.getLeft()){
				uu.setLeft(ww);
			}
			else{
				uu.setRight(ww);
			}
			if(ww != null){
				ww.setParent(uu);
			}
		}
		size--;
		return v.element();
	}

	@Override
	public void Attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		if(isEmpty()){
			throw new InvalidPositionException("El arbol esta vacio");
		}
		BNode<E> n = checkPosition(r);
		if(n.getRight() != null || n.getLeft() != null){
			throw new InvalidPositionException("La posicion pasada no es una hoja");
		}
		BNode<E> izq = null;
		try {
			izq = checkPosition(T1.root());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BNode<E> der = null;
		try {
			der = checkPosition(T2.root());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		n.setLeft(izq);
		n.setRight(der);
		if(izq != null){
			izq.setParent(n);
		}
		if(der != null){
			der.setParent(n);
		}

		size = size + T1.size();
		size = size + T2.size();

	}
}