package TDALista;

/**
 * Excepcion lanzada cuando la posicion es invalida.
 */
public class InvalidPositionException extends Exception {
	public InvalidPositionException(String msg){
		super(msg);
	}
}