package TDAPila;

/**
 * Excepcion lanzada cuando la pila esta vacia.
 */
public class EmptyStackException extends Exception {
	public EmptyStackException(String msg){
		super(msg);
	}
}