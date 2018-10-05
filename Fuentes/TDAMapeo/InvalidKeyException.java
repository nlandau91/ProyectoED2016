package TDAMapeo;

/**
 * Excepcion lanzada cuando la key es invalida.
 */
public class InvalidKeyException extends Exception {
	public InvalidKeyException(String msg){
		super(msg);
	}
}