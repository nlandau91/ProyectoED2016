package TDALista;

/**
 * Excepcion lanzada cuando se quiere acceder al primer o ultimo
 * elemento de una lista vacia.
 */
public class EmptyListException extends Exception {
	public EmptyListException(String msg){
		super(msg);
	}
}