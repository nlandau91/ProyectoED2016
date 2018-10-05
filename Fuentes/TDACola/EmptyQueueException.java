package TDACola;

/**
 * Excepcion lanzada cuando la cola esta vacia.
 */
public class EmptyQueueException extends Exception {
	public EmptyQueueException(String msg){
		super(msg);
	}
}