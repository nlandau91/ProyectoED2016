package TDAArbolBinario;

/**
 * Excepcion lanzada cuando se intenta realizar una operacion invalida.
 */
public class InvalidOperationException extends Exception {
	public InvalidOperationException(String msg){
		super(msg);
	}
}