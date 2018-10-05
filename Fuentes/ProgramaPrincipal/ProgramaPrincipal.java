package ProgramaPrincipal;

import java.util.Iterator;

import TDAArbolBinario.*;
import TDACola.*;

import TDAMapeo.*;
import TDAPila.*;

public class ProgramaPrincipal {

	protected TablaHashAbierta<String, Float> variables;
	protected ArbolBinario<String> expresion;

	/**
	 * Inicializa el arbol binario donde se almacenara la expresion
	 * y el mapeo donde se almacenaran las variables.
	 */
	public ProgramaPrincipal() {
		expresion = new ArbolBinario<String>();
		variables = new TablaHashAbierta<String, Float>();
	}

	/**
	 * Agrega una variable s con valor n al mapeo
	 * @param s es el nombre de la variable
	 * @param n es el valor de la variable
	 * @throws InvalidVarException si el nombre contiene caracteres invalidos
	 */
	public void AgregarMapeo(String s, Float n) throws InvalidVarException {
		char[] chars = s.toCharArray();
		for (char c : chars) {
			if(!Character.isLetter(c)) {
				throw new InvalidVarException("La variable contiene caracteres que no son letras.");
			}
		}
		try {
			variables.put(s, n);
		} catch (InvalidKeyException e) {
			throw new InvalidVarException("Variable Invalida");
		}
	}

	/**
	 * Devuelve un string con las variables del mapeo 
	 * junto a sus respectivos valores.
	 */
	public String mostrarMapeo() {
		String s = "";
		Iterable<Entry<String, Float>> entradas = variables.entries();
		for (Entry<String, Float> entrada : entradas) {
			s = s + entrada.getKey() + " = " + entrada.getValue() + ",  ";
		}
		return s;
	}

	/**
	 * * Recibe una expresion aritmetica por medio de un string y la transforma a
	 * un arbol binario
	 * @param cad  que es la cadena de la expresion aritmetica
	 * @throws InvalidExpresionException en caso de que la expresion sea incorrecta
	 */
	public void programa3(String cad) throws InvalidExpresionException {
		char[] arr = cad.toCharArray();
		NodeQueue<String> cola = new NodeQueue<String>();
		PilaEnlazada<Character> pila = new PilaEnlazada<Character>();
		try {
			char c;
			int i = 0;
			int operadores = 0;
			int parentesis = 0;
			while (i < arr.length) {
				c = arr[i];
				if (c == '*' || c == '/' || c == '+' || c == '-' || c == '^' || c == '(') {
					if(c == '('){
						parentesis++;
					}
					else{
						if(pila.isEmpty()){
							throw new InvalidExpresionException("Expresion invalida.");
						}
						operadores++;
					}
					pila.push(c);
					i++;
				} else {
					if (c == ')') {
						parentesis++;
						while (!pila.isEmpty() && pila.top() != '(') {
							cola.enqueue("" + pila.pop());
						}
						try{
							pila.pop();
						}catch(EmptyStackException exc){
							throw new InvalidExpresionException("Expresion invalida: parentesis no balanceados.");
						}
						i++;
					} 
					else {
						if(Character.isLetter(c)){
							String var = "";
							while (Character.isLetter(c)) {
								var = var + c;
								i++;
								try{
									c = arr[i];
								}catch(ArrayIndexOutOfBoundsException exc){
									throw new InvalidExpresionException("Expresion invalida: parentesis no balanceados.");
								}
							}
							if(variables.get(var) == null){
								throw new InvalidExpresionException("La expresion contiene variables no definidas.");
							}
							cola.enqueue(var);
						}
						else{
							throw new InvalidExpresionException("La expresion contiene caracteres no validos");
						}
					}
				}
			}
			if((operadores * 2 != parentesis)){
				throw new InvalidExpresionException("Expresion invalida.");
			}
			PilaEnlazada<ArbolBinario<String>> elePend = new PilaEnlazada<ArbolBinario<String>>();
			while (!cola.isEmpty()) {
				String s = cola.dequeue();
				if (!(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^"))) {
					ArbolBinario<String> arb = new ArbolBinario<String>();
					arb.createRoot(s);
					elePend.push(arb);
				} else {
					ArbolBinario<String> arb = new ArbolBinario<String>();
					arb.createRoot(s);
					try{
						ArbolBinario<String> der = elePend.pop();
						ArbolBinario<String> izq = elePend.pop();
						arb.Attach(arb.root(), izq, der);
					}catch(EmptyStackException exc){
						throw new InvalidExpresionException("Expresion invalida");
					}					
					elePend.push(arb);
				}
			}
			try {
				expresion = elePend.pop();
			} catch (EmptyStackException e) {
				throw new InvalidExpresionException("Expresion invalida");
			}
		} catch (EmptyStackException | EmptyQueueException | InvalidOperationException | InvalidPositionException
				| EmptyTreeException | InvalidKeyException e) {
			//Estas excepciones estan checkeadas en el codigo y no deberian lanzarse nunca.
			e.printStackTrace();
		}
	}

	/**
	 * Shell para llamar al metodo programa4, el cual devuelve
	 * la expresion aritmetica en notacion prefija
	 * @return cadena con la expresion en notacion prefija
	 */
	public String llamarPrograma4() {
		try {
			return programa4(expresion.root());
		} catch (EmptyTreeException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Este metodo toma una expresion y la transforma a notacion prefija
	 * @param expresion
	 * @param r
	 * @return expresion en notacion prefija
	 */
	private String programa4(Position<String> r) {
		String toReturn = r.element();
		try {

			if (expresion.hasLeft(r)) {
				toReturn = toReturn + programa4(expresion.left(r));
			}
			if (expresion.hasRight(r)) {
				toReturn = toReturn + programa4(expresion.right(r));
			}

		} catch (InvalidPositionException | BoundaryViolationException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}
		return toReturn;

	}

	/**
	 * Shell para llamar al metodo programa4, el cual devuelve
	 * la expresion aritmetica en notacion infija
	 * @return cadena con la expresion en notacion infija
	 */
	public String llamarPrograma5() {
		try {
			return programa5(expresion.root());
		} catch (EmptyTreeException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Este metodo transforma una expresion a notacion infija
	 * 
	 * @param expresion
	 * @param r
	 * @return expresion en notacion infija
	 */
	private String programa5(Position<String> r) {
		String s = r.element();
		try {
			if(r.element().equals("+") || r.element().equals("-") || r.element().equals("*") || r.element().equals("/")
					|| r.element().equals("^")){
				
				s="("+ programa5(expresion.left(r))+s +programa5(expresion.right(r))+")";
				
			}
			
			
		} catch (InvalidPositionException | BoundaryViolationException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}
		
		return s;

	}
	

	/**
	 * Shell para llamar al metodo programa6, el cual devuelve
	 * la expresion aritmetica en notacion postfija
	 * @return cadena con la expresion en notacion postfija
	 */
	public String llamarPrograma6() {
		try {
			return programa6(expresion.root());
		} catch (EmptyTreeException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Este metodo transforma una expresion a notacion postfija
	 * 
	 * @param expresion
	 * @param r
	 * @return expresion en notacion postfija
	 */
	private String programa6(Position<String> r) {
		String toReturn = r.element();
		try {
			if (expresion.hasRight(r)) {
				toReturn = programa6(expresion.right(r)) + toReturn;
			}
			if (expresion.hasLeft(r)) {
				toReturn = programa6(expresion.left(r)) + toReturn;
			}

		} catch (InvalidPositionException | BoundaryViolationException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * Este metodo calcula el valor de la expresion aritmetica
	 * @return resultado de la expresion
	 */
	public String programa7() {
		String resultado = "";
		try {
			resultado = resultado + evaluar(expresion.root());
		} catch (EmptyTreeException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}
		return resultado;
	}

	private Float evaluar(Position<String> r) {
		Float toReturn = (float) 0;
		try {
			if (!(r.element().equals("+") || r.element().equals("-") || r.element().equals("*")
					|| r.element().equals("/") || r.element().equals("^"))) {
				toReturn = variables.get(r.element());
			} else {
				if (r.element().equals("+")) {
					toReturn = evaluar(expresion.left(r))
							+ evaluar(expresion.right(r));
				}
				if (r.element().equals("-")) {
					toReturn = evaluar(expresion.left(r))
							- evaluar(expresion.right(r));
				}
				if (r.element().equals("/")) {
					toReturn = evaluar(expresion.left(r))
							/ evaluar(expresion.right(r));
				}
				if (r.element().equals("*")) {
					toReturn = evaluar(expresion.left(r))
							* evaluar(expresion.right(r));
				}
				if (r.element().equals("^")) {
					toReturn = (float) Math.pow(evaluar(expresion.left(r)), evaluar(expresion.right(r)));
				}	 
			}
		} catch (InvalidKeyException | InvalidPositionException | BoundaryViolationException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * Metodo auxiliar para verificar que la variable no este ya ingresada en el
	 * mapeo
	 * 
	 * @param s
	 * @return true si se encontro en el mapeo, false en caso contrario
	 */
	public boolean buscar(String s) {
		boolean encontre = false;
		Iterable<String> keys = variables.keys();
		Iterator<String> it = keys.iterator();
		while (!encontre && it.hasNext()) {
			encontre = it.next().equals(s);
		}

		return encontre;
	}

	/**
	 * Metodo para reemplazar una variable por un nuevo valor
	 * 
	 * @param expresion
	 * @param l
	 */
	public void programa8(Queue<String> l) {

		try {
			if (expresion.altura() > 1) {
				reemplazar(expresion.root(), l);
			}
		} catch (EmptyTreeException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}

	}

	/**
	 * Metodo auxiliar para llamar el metodo cantAReemplazar desde la GUI
	 * @return un entero con la cantidad de variables necesarias
	 */
	public int cantAReemplazar(){
		try {
			if(expresion.altura() > 1){
				return cantAReemplazar(expresion.root());
			}
		} catch (EmptyTreeException e) {
			
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Metodo auxiliar para  calcular la cantidad de variables necesarias  para reemplazar la expresion
	 * @param r
	 * @return
	 */
	private int cantAReemplazar(Position<String> r){
		int cant = 0;
		try {
			if (expresion.isExternal(expresion.left(r)) && expresion.isExternal(expresion.right(r))) {
				cant = 1;

			} else {
				if(expresion.isInternal(expresion.left(r))){
					cant = cant + cantAReemplazar(expresion.left(r));
				}
				if(expresion.isInternal(expresion.right(r))){
					cant = cant + cantAReemplazar(expresion.right(r));
				}
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}

		return cant;
	}

	/**
	 * Metodo auxiliar para reemplazar una variable por un nuevo valor
	 * 
	 * @param expresion
	 * @param r
	 * @param variables
	 * @param l
	 */
	private void reemplazar(Position<String> r, Queue<String> l) {

		try {
			if (expresion.isExternal(expresion.left(r)) && expresion.isExternal(expresion.right(r))) {
				String var = l.dequeue();
				Float val = evaluar(r);
				expresion.remove(expresion.left(r));
				expresion.remove(expresion.right(r));
				expresion.replace(r, var);
				variables.put(var, val);

			} else {
				if(expresion.isInternal(expresion.left(r))){
					reemplazar(expresion.left(r), l);
				}
				if(expresion.isInternal(expresion.right(r))){
					reemplazar(expresion.right(r), l);
				}
			}
		} catch (InvalidPositionException | EmptyQueueException | BoundaryViolationException | InvalidOperationException
				| InvalidKeyException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}

	}

	/**
	 * Metodo para calcular la altura de un arbol binario
	 * 
	 * @param t
	 * @return altura
	 */
	public int altura() {
		int aux = -1;
		try {
			aux = expresion.altura();
		} catch (EmptyTreeException e) {
			//ESTA EXCEPCION ESTA CHEQUEADA Y NO SE LANZARA NUNCA

			e.printStackTrace();
		}
		return aux;
	}

	/**
	 * Metodo para calcular la cantidad de nodos de un arbol binario
	 * 
	 * @param e
	 * @return cantNodos
	 */
	public int cantNodos() {
		return expresion.size();
	}

	/**
	 * Metodo para calcular la cantidad de hojas de un arbol binario
	 * 
	 * @param e
	 * @return cantHojas
	 */
	public int cantHojas() {

		return (expresion.cantHojas());
	}

	/**
	 * Metodo para calcular la cantidad de nodos internos de un arbol binario
	 * 
	 * @param e
	 * @return cantNodosInternos
	 */
	public int cantInternos() {
		return expresion.cantInternos();
	}

	/**
	 * Metodo para determinar si un arbol binario es propio o no
	 * 
	 * @param e
	 * @return true si el arbol es propio, false si no lo es
	 */
	public boolean esPropio() {
		return expresion.esPropio();
	}
}