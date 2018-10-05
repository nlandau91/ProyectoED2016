package TDAArbolBinario;

/**
 * Excepcion lanzada cuando se intenta acceder al padre del nodo raiz,
 * o a un hijo no existente.
 */

public class BoundaryViolationException extends Exception {
	public BoundaryViolationException(String msg){
		super(msg);
	}
}