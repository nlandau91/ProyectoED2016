package TDAArbolBinario;

/**
 * Excepcion lanzada cuando la posicion es invalida.
 */
public class InvalidPositionException extends Exception {
	public InvalidPositionException(String msg){
		super(msg);
	}
}