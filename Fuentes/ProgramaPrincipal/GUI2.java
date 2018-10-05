package ProgramaPrincipal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import TDACola.NodeQueue;
import TDACola.Queue;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

public class GUI2 {
	private ProgramaPrincipal l = new ProgramaPrincipal();
	private JFrame frame;

	// CAMPOS DE TEXTO
	private JTextField textFieldAgregarVariableNombre;
	private JFormattedTextField textFieldAgregarVariableValor;
	private JTextField textFieldAgregarExpresion;

	// PANELES
	private JPanel panelMapeo;
	private JPanel panelAgregar;
	private JPanel panelVacio;
	private JPanel panelGeneral;
	private JPanel panelConvertirExp;
	private JPanel panelArbol;
	private JPanel panelExpresion;
	private JPanel panelAgregarExp;
	private JPanel panel;
	private JPanel panelReemplazar;

	// BOTONES
	private JButton btnAceptar;
	private JButton btnVolverMostrarMapeo;
	private JButton btnAgregar;
	private JButton btnConvertir;
	private JButton btnCalcular;
	private JButton btnVolverExpresionConvertir;
	private JButton btnPrefija;
	private JButton btnInfija;
	private JButton btnPostfija;
	private JButton buttonReemplazar;
	private JButton btnVolverAgregarExpresion;
	private JButton btnArbolMenu;
	private JButton btnMostrarMapeo;

	// ETIQUETAS

	private JLabel lblAgregadoConExito;
	private JLabel lblValor;
	private JLabel lblNombre;
	private JLabel lblExpresion;
	private JLabel lblInformacionDelArbol;
	private JLabel lblNumeroDeNodos;
	private JLabel lblAlturaDelArbol;
	private JLabel lblNumeroDeHojas;
	private JLabel lblNumeroDeNodos_1;
	private JLabel lblArbolPropio;
	private JLabel lblAltura;
	private JLabel lblNDeHojas;
	private JLabel lblNDeNodos;
	private JLabel lblNDeNodos_1;
	private JLabel lblEsArbolPropio;
	private JLabel lblExpresionCorrecta;
	private JLabel lblExpresionEscrita;
	private JLabel lblValorExpresion;
	private JLabel lblSeReemplazoCorrectamente;
	private JLabel lblIngreseVariables;
	private JLabel lblExpresionActual;

	// OTROS
	private boolean activated = false;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private JTextField textFieldReemplazar;
	private Queue<String> q = new NodeQueue<String>();
	int cant = 0;
	private JLabel lblIngreseUnaExpresion;
	private JLabel lblReemplaceLaExpresion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI2 window = new GUI2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Proyecto Estructuras de Datos");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panelReemplazar = new JPanel();

		panelReemplazar.setBorder(new LineBorder(new Color(83, 121, 154), 2, true));
		panelReemplazar.setBackground(new Color(114, 166, 213));
		panelReemplazar.setBounds(0, 65, 434, 196);
		frame.getContentPane().add(panelReemplazar);
		panelReemplazar.setLayout(null);
		panelReemplazar.setVisible(false);

		textFieldReemplazar = new JTextField();
		textFieldReemplazar.setBounds(193, 94, 86, 20);
		panelReemplazar.add(textFieldReemplazar);
		textFieldReemplazar.setColumns(10);

		textFieldReemplazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = textFieldReemplazar.getText();
              
				lblIngreseVariables.setText("Ingrese " + (cant - q.size()) + " variables");
				boolean cumple = true;
				char[] c = s.toCharArray();
				if (s.isEmpty())
					cumple = false;

				for (int i = 0; i < c.length && cumple; i++)
					if (!Character.isLetter(c[i]))
						cumple = false;

				if (cumple) {

					lblSeReemplazoCorrectamente.setVisible(true);

					if (!l.buscar(s)) {

						q.enqueue(s);
						lblIngreseVariables.setText("Ingrese " + (cant - q.size()) + " variables");
						lblSeReemplazoCorrectamente.setText("Se Reemplazo Correctamente");
						textFieldReemplazar.setText(null);

						if (q.size() == l.cantAReemplazar()) {

							lblSeReemplazoCorrectamente.setText("Expresion Reemplazada Correctamente");
							l.programa8(q);
							lblExpresionActual.setText("Expresion Actual= " + l.llamarPrograma5());
							lblExpresionEscrita.setText(l.llamarPrograma5());

							if (l.altura() == 1) {
								textFieldReemplazar.setEnabled(false);
								lblReemplaceLaExpresion.setText("Ya no se puede reemplazar. La expresion tiene un solo termino");

							} else {
								

								lblReemplaceLaExpresion.setText("Puede volver a reemplazar");
								cant=l.cantAReemplazar();
								lblIngreseVariables.setText("Ingrese " + (cant - q.size()) + " variables");
 						}
						}
					} else
						lblSeReemplazoCorrectamente.setText("La variable ya se encuentra almacenada");

				} else {
					lblSeReemplazoCorrectamente.setVisible(true);
					lblSeReemplazoCorrectamente.setText("La variable es incorrecta");
				}
			}}
		
		);

		JLabel lblNombreReemplazo = new JLabel("Nombre");
		lblNombreReemplazo.setBounds(123, 97, 46, 14);
		panelReemplazar.add(lblNombreReemplazo);

		lblSeReemplazoCorrectamente = new JLabel("Se reemplazo correctamente");
		lblSeReemplazoCorrectamente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeReemplazoCorrectamente.setBounds(70, 125, 275, 14);
		panelReemplazar.add(lblSeReemplazoCorrectamente);
		lblSeReemplazoCorrectamente.setVisible(false);

		JButton btnVolverReemplazar = new JButton("Volver");
		btnVolverReemplazar.setMnemonic('v');
		btnVolverReemplazar.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnVolverReemplazar.setBackground(new Color(40, 212, 189));
		btnVolverReemplazar.setBounds(335, 162, 89, 23);
		panelReemplazar.add(btnVolverReemplazar);

		lblIngreseVariables = new JLabel();
		lblIngreseVariables.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseVariables.setBounds(59, 49, 306, 14);
		panelReemplazar.add(lblIngreseVariables);

		lblExpresionActual = new JLabel("Expresion Actual:");
		lblExpresionActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpresionActual.setBounds(103, 150, 222, 14);
		panelReemplazar.add(lblExpresionActual);

		lblReemplaceLaExpresion = new JLabel("Reemplace la expresion");
		lblReemplaceLaExpresion.setHorizontalAlignment(SwingConstants.CENTER);
		lblReemplaceLaExpresion.setBounds(10, 11, 414, 14);
		panelReemplazar.add(lblReemplaceLaExpresion);
		btnVolverReemplazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultar();

			}
		});

		panelAgregarExp = new JPanel();
		panelAgregarExp.setBorder(new LineBorder(new Color(83, 121, 154), 2, true));
		panelAgregarExp.setBackground(new Color(114, 166, 213));
		panelAgregarExp.setBounds(0, 65, 434, 196);
		frame.getContentPane().add(panelAgregarExp);
		panelAgregarExp.setLayout(null);
		panelAgregarExp.setVisible(false);

		lblExpresion = new JLabel("Expresion");
		lblExpresion.setBounds(98, 66, 67, 14);
		panelAgregarExp.add(lblExpresion);

		textFieldAgregarExpresion = new JTextField();
		textFieldAgregarExpresion.setColumns(10);
		textFieldAgregarExpresion.setBounds(175, 63, 124, 20);
		panelAgregarExp.add(textFieldAgregarExpresion);
		textFieldAgregarExpresion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito

				if (((caracter < 'A') || (caracter > 'z')) && (caracter < '(' || caracter > '/')
						|| ((caracter > 'Z' && caracter < 'a') && caracter != '^') || caracter == '.'
						|| caracter == ',') {

					e.consume(); // ignorar el evento de teclado
				}
			}
		});

		textFieldAgregarExpresion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cad = textFieldAgregarExpresion.getText();
				lblExpresionCorrecta.setVisible(true);
				try {
					l.programa3(cad);

					lblExpresionCorrecta.setText("Expresión Correcta");
					lblExpresionEscrita.setText(cad);

					panelReemplazar.setVisible(false);
					lblValorExpresion.setText("----");
					lblExpresionActual.setText("Expresión Actual= " + cad);
                    
					cant=l.cantAReemplazar();
					if (l.altura() == 1) {
						textFieldReemplazar.setEnabled(false);
						lblIngreseVariables.setText("Ingrese 0 variables");
						lblReemplaceLaExpresion.setText("Ya no se puede reemplazar. La expresión tiene un solo término.");
					} else {
						textFieldReemplazar.setEnabled(true);
						lblReemplaceLaExpresion.setText("Puede Reemplazar la expresión");
						lblIngreseVariables.setText("Ingrese " + l.cantAReemplazar() + " variables");
						
					}

					
					btnArbolMenu.setEnabled(true);
					btnConvertir.setEnabled(true);
					buttonReemplazar.setEnabled(true);

				} catch (InvalidExpresionException e1) {
					lblExpresionCorrecta.setText(e1.getMessage());
					btnArbolMenu.setEnabled(false);
					buttonReemplazar.setEnabled(false);
					btnConvertir.setEnabled(false);

				}

			}
		});

		btnVolverAgregarExpresion = new JButton("Volver");
		btnVolverAgregarExpresion.setMnemonic('v');
		btnVolverAgregarExpresion.setBounds(335, 162, 89, 23);
		btnVolverAgregarExpresion.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnVolverAgregarExpresion.setBackground(new Color(40, 212, 189));
		panelAgregarExp.add(btnVolverAgregarExpresion);
		btnVolverAgregarExpresion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultar();
			}
		});

		lblExpresionCorrecta = new JLabel("Expresion Correcta");
		lblExpresionCorrecta.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpresionCorrecta.setBounds(63, 108, 299, 14);
		panelAgregarExp.add(lblExpresionCorrecta);

		lblIngreseUnaExpresion = new JLabel("Ingrese una expresion aritmetica");
		lblIngreseUnaExpresion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseUnaExpresion.setBounds(104, 26, 226, 14);
		panelAgregarExp.add(lblIngreseUnaExpresion);
		lblExpresionCorrecta.setVisible(false);

		panelExpresion = new JPanel();
		panelExpresion.setBounds(0, 0, 434, 65);
		panelExpresion.setBorder(new LineBorder(new Color(83, 90, 154), 2, true));
		panelExpresion.setBackground(new Color(116, 126, 215));
		frame.getContentPane().add(panelExpresion);
		panelExpresion.setLayout(null);
		panelExpresion.setVisible(false);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setMnemonic('a');
		btnAgregar.setBackground(new Color(211, 145, 244));
		btnAgregar.setBorder(new LineBorder(new Color(155, 106, 179), 2, true));
		btnAgregar.setBounds(0, 10, 144, 45);
		panelExpresion.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelConvertirExp.setVisible(false);
				panelAgregarExp.setVisible(true);
				panelReemplazar.setVisible(false);
			}
		});

		btnConvertir = new JButton("Convertir/Calcular");
		btnConvertir.setMnemonic('c');
		btnConvertir.setEnabled(false);
		btnConvertir.setBackground(new Color(211, 145, 244));
		btnConvertir.setBorder(new LineBorder(new Color(155, 106, 179), 2, true));
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAgregarExp.setVisible(false);
				panelConvertirExp.setVisible(true);
				panelReemplazar.setVisible(false);
			}
		});

		btnConvertir.setBounds(145, 10, 144, 45);
		panelExpresion.add(btnConvertir);

		buttonReemplazar = new JButton("Reemplazar");

		buttonReemplazar.setEnabled(false);
		buttonReemplazar.setBackground(new Color(211, 145, 244));
		buttonReemplazar.setBorder(new LineBorder(new Color(155, 106, 179), 2, true));
		buttonReemplazar.setBounds(290, 10, 144, 45);
		panelExpresion.add(buttonReemplazar);
		buttonReemplazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAgregarExp.setVisible(false);
				panelConvertirExp.setVisible(false);
				panelReemplazar.setVisible(true);

			}
		});

		panelConvertirExp = new JPanel();
		panelConvertirExp.setBackground(new Color(114, 166, 213));
		panelConvertirExp.setBorder(new LineBorder(new Color(83, 121, 154), 2, true));
		panelConvertirExp.setBounds(0, 65, 434, 196);
		frame.getContentPane().add(panelConvertirExp);
		panelConvertirExp.setLayout(null);
		panelConvertirExp.setVisible(false);

		btnVolverExpresionConvertir = new JButton("Volver");
		btnVolverExpresionConvertir.setMnemonic('v');
		btnVolverExpresionConvertir.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnVolverExpresionConvertir.setBackground(new Color(40, 212, 189));
		btnVolverExpresionConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultar();
			}
		});
		btnVolverExpresionConvertir.setBounds(335, 162, 89, 23);
		panelConvertirExp.add(btnVolverExpresionConvertir);

		btnPrefija = new JButton("Prefija");
		btnPrefija.setMnemonic('p');
		btnPrefija.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnPrefija.setBackground(new Color(40, 212, 189));
		btnPrefija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String s;
				s = l.llamarPrograma4();
				lblExpresionEscrita.setText(s);

			}
		});
		btnPrefija.setBounds(10, 20, 89, 23);
		panelConvertirExp.add(btnPrefija);

		btnInfija = new JButton("Infija");
		btnInfija.setMnemonic('i');
		btnInfija.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnInfija.setBackground(new Color(40, 212, 189));
		btnInfija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t = "";

				t = l.llamarPrograma5();
				lblExpresionEscrita.setText(t);
			}
		}

		);
		btnInfija.setBounds(10, 60, 89, 23);
		panelConvertirExp.add(btnInfija);

		btnPostfija = new JButton("PostFija");
		btnPostfija.setMnemonic('o');
		btnPostfija.setBounds(10, 100, 89, 23);
		panelConvertirExp.add(btnPostfija);
		btnPostfija.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnPostfija.setBackground(new Color(40, 212, 189));
		btnPostfija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String aux = l.llamarPrograma6();
				lblExpresionEscrita.setText(aux);

			}
		});

		JLabel lblExpresionConvertir = new JLabel("Expresion");
		lblExpresionConvertir.setBounds(144, 30, 77, 14);
		panelConvertirExp.add(lblExpresionConvertir);

		lblExpresionEscrita = new JLabel("----");
		lblExpresionEscrita.setHorizontalAlignment(SwingConstants.LEFT);
		lblExpresionEscrita.setBounds(231, 30, 193, 14);
		panelConvertirExp.add(lblExpresionEscrita);

		btnCalcular = new JButton("Calcular");
		btnCalcular.setMnemonic('c');
		btnCalcular.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnCalcular.setBackground(new Color(40, 212, 189));
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblValorExpresion.setText(l.programa7());
			}

		});
		btnCalcular.setBounds(10, 140, 89, 23);
		panelConvertirExp.add(btnCalcular);

		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setBounds(144, 72, 77, 14);
		panelConvertirExp.add(lblResultado);

		lblValorExpresion = new JLabel("----");
		lblValorExpresion.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorExpresion.setBounds(231, 72, 114, 14);
		panelConvertirExp.add(lblValorExpresion);

		panelAgregar = new JPanel();
		panelAgregar.setBorder(new LineBorder(new Color(83, 121, 154), 2, true));
		panelAgregar.setBounds(0, 65, 434, 196);
		panelAgregar.setBackground(new Color(114, 166, 213));
		frame.getContentPane().add(panelAgregar);
		panelAgregar.setLayout(null);
		panelAgregar.setVisible(false);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(121, 27, 58, 14);
		panelAgregar.add(lblNombre);

		lblValor = new JLabel("Valor");
		lblValor.setBounds(121, 59, 46, 14);
		panelAgregar.add(lblValor);

		textFieldAgregarVariableNombre = new JTextField();
		textFieldAgregarVariableNombre.setBounds(189, 24, 86, 20);
		panelAgregar.add(textFieldAgregarVariableNombre);
		textFieldAgregarVariableNombre.setColumns(10);

		textFieldAgregarVariableValor = new JFormattedTextField();
		textFieldAgregarVariableValor.setBounds(189, 56, 86, 20);
		panelAgregar.add(textFieldAgregarVariableValor);
		textFieldAgregarVariableValor.setColumns(1);
		int limite = 10;
		textFieldAgregarVariableValor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				{
					if (textFieldAgregarVariableValor.getText().length() == limite)

						e.consume();
				}
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if ((caracter != '-') && (caracter != '.') && ((caracter < '0') || (caracter > '9'))) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setMnemonic('c');
		btnAceptar.setBounds(145, 99, 89, 23);
		btnAceptar.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnAceptar.setBackground(new Color(40, 212, 189));
		panelAgregar.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean cumple = true;
				int cant = 0;
				lblAgregadoConExito.setVisible(true);
				char[] arr = textFieldAgregarVariableValor.getText().toCharArray();
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] == '.')
						cant++;
					if (arr[i] == '-' && i != 0) {

						cumple = false;
						lblAgregadoConExito.setText("Valor de variable invalido");
					}
				}
				if (cant > 1) {
					cumple = false;
					lblAgregadoConExito.setText("Valor de variable invalido");
				}
				if (cumple) {

					if (textFieldAgregarVariableValor.getText().isEmpty())
						lblAgregadoConExito.setText("Valor de variable vacio");
					else {
						float num = Float.parseFloat(textFieldAgregarVariableValor.getText());

						boolean vacio;
						if (textFieldAgregarVariableNombre.getText().isEmpty()
								|| textFieldAgregarVariableNombre.getText().trim().isEmpty())
							vacio = true;
						else
							vacio = false;

						if (!vacio) {

							try {
								l.AgregarMapeo(textFieldAgregarVariableNombre.getText(), num);
								lblAgregadoConExito.setText("Variable agregada con exito");
							} catch (InvalidVarException e) {
								lblAgregadoConExito.setText(e.getMessage());
							}

							if (!activated) {
								btnMostrarMapeo.setEnabled(true);
								activated = true;
							}
						} else {
							lblAgregadoConExito.setText("Nombre de variable vacio");
						}
					}

				}

			}
		}

		);

		lblAgregadoConExito = new JLabel("Variable Agregada con Exito");
		lblAgregadoConExito.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregadoConExito.setBounds(37, 147, 294, 14);
		panelAgregar.add(lblAgregadoConExito);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setMnemonic('v');
		btnVolver.setBounds(335, 162, 89, 23);
		btnVolver.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnVolver.setBackground(new Color(40, 212, 189));
		panelAgregar.add(btnVolver);
		lblAgregadoConExito.setVisible(false);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultar();
			}
		});

		panelArbol = new JPanel();
		panelArbol.setBackground(new Color(114, 166, 213));
		panelArbol.setBorder(new LineBorder(new Color(83, 121, 154), 2, true));
		panelArbol.setBounds(0, 65, 434, 196);
		frame.getContentPane().add(panelArbol);
		panelArbol.setLayout(null);
		panelArbol.setVisible(false);

		lblInformacionDelArbol = new JLabel("INFORMACION DEL ARBOL");
		lblInformacionDelArbol.setBounds(146, 11, 164, 14);
		panelArbol.add(lblInformacionDelArbol);

		lblNumeroDeNodos = new JLabel("Numero de Nodos");
		lblNumeroDeNodos.setBounds(71, 86, 133, 14);
		panelArbol.add(lblNumeroDeNodos);

		lblAlturaDelArbol = new JLabel("Altura del Arbol");
		lblAlturaDelArbol.setBounds(71, 36, 88, 14);
		panelArbol.add(lblAlturaDelArbol);

		lblNumeroDeHojas = new JLabel("Numero de Hojas");
		lblNumeroDeHojas.setBounds(71, 61, 133, 14);
		panelArbol.add(lblNumeroDeHojas);

		lblNumeroDeNodos_1 = new JLabel("Numero de Nodos Internos");
		lblNumeroDeNodos_1.setBounds(71, 111, 175, 14);
		panelArbol.add(lblNumeroDeNodos_1);

		lblArbolPropio = new JLabel("Arbol Propio");
		lblArbolPropio.setBounds(71, 136, 88, 14);
		panelArbol.add(lblArbolPropio);

		lblAltura = new JLabel("----");
		lblAltura.setBounds(287, 36, 46, 14);
		panelArbol.add(lblAltura);

		lblNDeHojas = new JLabel("----");
		lblNDeHojas.setBounds(287, 61, 69, 14);
		panelArbol.add(lblNDeHojas);

		lblNDeNodos = new JLabel("----");
		lblNDeNodos.setBounds(287, 86, 69, 14);
		panelArbol.add(lblNDeNodos);

		lblNDeNodos_1 = new JLabel("----");
		lblNDeNodos_1.setBounds(287, 111, 114, 14);
		panelArbol.add(lblNDeNodos_1);

		lblEsArbolPropio = new JLabel("----");
		lblEsArbolPropio.setBounds(287, 136, 114, 14);
		panelArbol.add(lblEsArbolPropio);

		JButton btnVolverArbolBinario = new JButton("Volver");
		btnVolverArbolBinario.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnVolverArbolBinario.setMnemonic('v');
		btnVolverArbolBinario.setForeground(Color.DARK_GRAY);
		btnVolverArbolBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultar();
			}
		});
		btnVolverArbolBinario.setBounds(335, 162, 89, 23);
		btnVolverArbolBinario.setBackground(new Color(40, 212, 189));
		panelArbol.add(btnVolverArbolBinario);

		panelMapeo = new JPanel();
		panelMapeo.setBounds(0, 0, 434, 65);
		panelMapeo.setBorder(new LineBorder(new Color(83, 90, 154), 2, true));
		panelMapeo.setBackground(new Color(116, 126, 215));
		frame.getContentPane().add(panelMapeo);
		panelMapeo.setLayout(null);
		panelMapeo.setVisible(false);

		JButton btnAgregarMapeo = new JButton("Agregar");
		btnAgregarMapeo.setMnemonic('a');
		btnAgregarMapeo.setBounds(52, 10, 144, 45);
		btnAgregarMapeo.setBackground(new Color(211, 145, 244));
		btnAgregarMapeo.setBorder(new LineBorder(new Color(155, 106, 179), 2, true));
		panelMapeo.add(btnAgregarMapeo);
		btnAgregarMapeo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelVacio.setVisible(false);
				panel.setVisible(false);
				panelAgregar.setVisible(true);
			}
		});

		btnMostrarMapeo = new JButton("Mostrar");
		btnMostrarMapeo.setMnemonic('m');
		btnMostrarMapeo.setEnabled(false);
		btnMostrarMapeo.setBackground(new Color(211, 145, 244));
		btnMostrarMapeo.setBorder(new LineBorder(new Color(155, 106, 179), 2, true));
		btnMostrarMapeo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelVacio.setVisible(false);
				panelAgregar.setVisible(false);
				panel.setVisible(true);
				textPane.setText(l.mostrarMapeo());
			}
		});
		btnMostrarMapeo.setBounds(238, 10, 144, 45);
		panelMapeo.add(btnMostrarMapeo);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(83, 121, 154), 2, true));
		panel.setBackground(new Color(114, 166, 213));
		panel.setBounds(0, 65, 434, 196);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 146);
		panel.add(scrollPane);

		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

		btnVolverMostrarMapeo = new JButton("Volver");
		btnVolverMostrarMapeo.setMnemonic('v');
		btnVolverMostrarMapeo.setBorder(new LineBorder(new Color(29, 154, 137), 2, true));
		btnVolverMostrarMapeo.setBackground(new Color(40, 212, 189));
		btnVolverMostrarMapeo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultar();
			}
		});
		btnVolverMostrarMapeo.setBounds(335, 162, 89, 23);
		panel.add(btnVolverMostrarMapeo);

		panelGeneral = new JPanel();
		panelGeneral.setBounds(0, 0, 434, 64);
		panelGeneral.setBorder(new LineBorder(new Color(83, 90, 154), 2, true));
		panelGeneral.setBackground(new Color(116, 126, 215));
		frame.getContentPane().add(panelGeneral);
		panelGeneral.setLayout(null);

		JButton btnMapeoMenu = new JButton("Mapeo");
		btnMapeoMenu.setMnemonic('m');
		btnMapeoMenu.setBackground(new Color(211, 145, 244));
		btnMapeoMenu.setBorder(new LineBorder(new Color(155, 106, 179), 2, true));
		btnMapeoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ocultar();
				panelGeneral.setVisible(false);
				panelMapeo.setVisible(true);
			}
		});
		btnMapeoMenu.setBounds(0, 10, 144, 45);
		panelGeneral.add(btnMapeoMenu);

		JButton btnExpresionMenu = new JButton("Expresion Numerica");
		btnExpresionMenu.setMnemonic('e');
		btnExpresionMenu.setBackground(new Color(211, 145, 244));
		btnExpresionMenu.setBorder(new LineBorder(new Color(155, 106, 179), 2, true));
		btnExpresionMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultar();
				panelGeneral.setVisible(false);
				panelExpresion.setVisible(true);

			}
		});
		btnExpresionMenu.setBounds(145, 10, 144, 45);
		panelGeneral.add(btnExpresionMenu);

		btnArbolMenu = new JButton("Arbol Binario");
		btnArbolMenu.setMnemonic('a');
		btnArbolMenu.setEnabled(false);
		btnArbolMenu.setBounds(290, 10, 144, 45);
		btnArbolMenu.setBackground(new Color(211, 145, 244));
		btnArbolMenu.setBorder(new LineBorder(new Color(155, 106, 179), 2, true));
		panelGeneral.add(btnArbolMenu);
		btnArbolMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocultar();
				panelGeneral.setVisible(false);
				lblAltura.setText("" + l.altura());
				lblNDeHojas.setText("" + l.cantHojas());
				lblNDeNodos.setText("" + l.cantNodos());
				lblNDeNodos_1.setText("" + l.cantInternos());
				lblEsArbolPropio.setText("" + l.esPropio());
				panelArbol.setVisible(true);
			}
		});

		panelVacio = new JPanel();
		panelVacio.setBackground(new Color(114, 166, 213));
		panelVacio.setBorder(new LineBorder(new Color(83, 121, 154), 2, true));
		panelVacio.setBounds(0, 65, 434, 196);
		frame.getContentPane().add(panelVacio);

		JPanel panelGeneralArbol = new JPanel();
		panelGeneralArbol.setBounds(0, 0, 434, 65);
		panelGeneralArbol.setBorder(new LineBorder(new Color(83, 90, 154), 2, true));
		panelGeneralArbol.setBackground(new Color(116, 126, 215));
		frame.getContentPane().add(panelGeneralArbol);
	}

	/**
	 * Metodo Creado para hacer invisibles los paneles, botones y otros objetos
	 * y mostrar el menu principal
	 */
	private void ocultar() {
		panelMapeo.setVisible(false);
		panelAgregar.setVisible(false);
		// panelExpresion.setVisible(false);
		// panelArbol.setVisible(false);
		panelVacio.setVisible(true);
		panel.setVisible(false);
		panelMapeo.setVisible(false);
		panelGeneral.setVisible(true);
		panelExpresion.setVisible(false);
		panelAgregarExp.setVisible(false);
		panelConvertirExp.setVisible(false);
		panelArbol.setVisible(false);
		lblExpresionCorrecta.setVisible(false);
		textFieldAgregarExpresion.setText("");
		panelReemplazar.setVisible(false);
		// lblExp.setVisible(false);
		// textFieldExp.setVisible(false);

	}
}
