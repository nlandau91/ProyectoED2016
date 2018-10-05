package TDAArbolBinario;

/**
 * Excepcion lanzada cuando se intenta realizar una operacion no permitida para un arbol vacio.
 * @author nicolas
 *
 */
public class EmptyTreeException extends Exception {
	public EmptyTreeException(String msg){
		super(msg);
	}
}