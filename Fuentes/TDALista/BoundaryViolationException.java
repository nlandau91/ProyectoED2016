package TDALista;

/**
 * Excepcion lanzada cuando se quiere acceder al elemento anterior
 * al primero de una lista, o al siguiente del ultimo elemento.
 */
public class BoundaryViolationException extends Exception {
	public BoundaryViolationException(String msg){
		super(msg);
	}
}