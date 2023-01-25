package ahorcado;

import java.awt.Image;
import javax.swing.*;

import java.awt.event.*;

public class juego extends JFrame implements ActionListener {

	//Donde ira la letra
	private JTextField texto;
	//Boton que activa el juego
	private JButton boton;
	//Lista de palabras
	private String[] words = { "cazar", "ordenador", "cara", "baloncesto", "rueda", "amigo", "pesado", "esternocleidomastoideo" };
	//Palabra seleccionada
	private String word;
	//Array de caracteres de la palabra seleccionada
	private char[] charWord;
        //Jlabel es texto que no se puede editar
        //Jlabel para mostrar los aciertos
	private JLabel[] cWord;
	//Array de jlabel con los caracteres usados
	private JLabel[] fChar = new JLabel[27];
	//Array de Imagenes
	private static ImageIcon[] fallo = new ImageIcon[8];
	//Contador de Imagenes
	private static int contador = 0;
	//Mostrador de Imagen
	private static JLabel showImage;
        //Constructor
	public juego() {
                //Layout por defecto
		setLayout(null);
		//Crea el texto que usará el usuario
		texto = new JTextField();
                //Texto tamanio
		texto.setBounds(215, 30, 20, 25);
                //Aniadimos el texto
		add(texto);

		//Crea el botón para comprobar la letra
		boton = new JButton("Probar");
                //Tamanio boton
		boton.setBounds(180, 70, 100, 30);
                //Aniadimos el boton
		add(boton);
                //Aniadimos action listener a boton
		boton.addActionListener(this);

		//De forma aleatoria selecciona una palabra del array y lo convierte a array de caracteres
		int selector = (int) Math.floor(Math.random() * words.length);
                //Elegimos la palabra
		word = words[selector];
                //Pasamos la palabra a un array de char
		charWord = word.toCharArray();
                //Elegimos el tamanio de la respuesta para las letras y guiones
		cWord = new JLabel[charWord.length];
		//Mostrar array de caracteres por ventana
                //Cantidad de guiones, cantidad de letras
		int cantidad = charWord.length;
                //Calculos para recorrer todo el array
		selector = (cantidad * 10) + ((cantidad - 1) * 10);
		selector = 225 - (selector / 2);
		for (int i = 0; i < cWord.length; i++) {
                        //Aniadimos los _ de cada letra
			cWord[i] = new JLabel("_");
			cWord[i].setBounds((i * 20) + selector, 110, 10, 25);
			add(cWord[i]);
		}

		//Guardamos las imagenes usadas para el ahorcado de una carpeta especifica
		for (int i = 0; i < fallo.length; i++) {
			fallo[i] = new ImageIcon("./src/fallos/fallo" + i + ".jpg");
                        Image image = fallo[i].getImage();
                        image = image.getScaledInstance(250, 280, java.awt.Image.SCALE_SMOOTH);
                        fallo[i] = new ImageIcon(image);
                        
		}

		//Aniade la imagen, usando un metodo para la creación de la imagen
		add(imagen());
		
		//Muestra el array de caracteres usados
		for (int i = 0; i < fChar.length; i++) {
			fChar[i] = new JLabel("");
			if (i<15) {
                            //Controlamos las posiciones del historial de letras
				fChar[i].setBounds((i * 20) + 80, 390, 10, 25);
				add(fChar[i]);
			} else {
                            //Segunda fila, son 27 letras
				fChar[i].setBounds(((i-15) * 20) + 80, 390, 10, 25);
				add(fChar[i]);
			}
		}
                setTitle("ESCAPA DE MR.X");

	}

	//Metodo usado para la creacion de la imagen del ahorcado como etiqueta
	public JLabel imagen() {
		showImage = new JLabel();
                //Metemos la imagen 0
		CambiarImagen();
		showImage.setEnabled(true);
		showImage.setBounds(100, 130, 250, 250);
		showImage.setVisible(true);
		return showImage;
	}

	public void CambiarImagen() {
		showImage.setIcon(fallo[contador]);
		contador++;
                //Si contador = al numero de imagenes que hay pues ha perdido
		if (contador == fallo.length) {
                        //Metodo de gameover en falso
			finDeJuego(false);
		}
	}

	//Metodo que comprueba si el texto introducido son numeros o no
	public boolean esUnChar(String c) {
                //Pillamos solo la primera letra
		char caracter = c.charAt(0);
		caracter = Character.toUpperCase(caracter);
                //Convertimos a String
                String letra = caracter+"";
                //Comprobamos que esta entre la A y la Z
                if (letra.matches("[A-Z]") || letra.matches("[Ñ-ñ]")){
                    return true;
                } else{
                    return false;
                }
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boton) {
                    //Metemos en la variable cad la letra introducida
			String cad = texto.getText();
			//Si es solo una letra
			if (cad.length() == 1) {
				//Si no hay numeros y esta entre la A y la Z
				if (esUnChar(cad)) {
					
					if (comprobarChar(cad)) {
						usedChar(cad);
						if (comprobarPalabra()) { finDeJuego(true); }
					} else {
						usedChar(cad);
						CambiarImagen();
					}
					
					
				} else {
					setTitle("Introduzca solo letras");
				}
			} else {
				setTitle("Introduzca una sola letra");
			}
                    texto.setText("");
		}
	}

	//Metodo que comprueba si la letra se encuentra en la palabra, devuelve false si no
	public boolean comprobarChar(String c) {
		char caracter = c.charAt(0);
		//Recorro el array de caracteres de la palabra seleccionada
		for (int i = 0; i < charWord.length; i++) {
			//Si encuentra el caracter, lo pone mayusculas y modifica el label
			if (charWord[i]==caracter) {
				c = c.toUpperCase();
				cWord[i].setText(c);
				return true;
			}
		}
		return false;

	}
	
	// Método que comprueba si la palabra se ha completado, devuelve true si no
	public boolean comprobarPalabra() {
		boolean res=true;
		for (int i = 0; i < cWord.length; i++) {
			if (cWord[i].getText().equals("_")) {
				res = false;
			}
		}
		return res;
	}
	
	public void usedChar(String cad) {
		for (int i = 0; i < fChar.length ; i++) {
			if (fChar[i].getText().equals(cad.toUpperCase()) || fChar[i].getText().equals("")) {
				cad = cad.toUpperCase();
				fChar[i].setText(cad);
				i = fChar.length;
			}
		}
	}
	
	public void finDeJuego(boolean b) {
		String res;
		if (b) {
			res = "¡GANASTE!";
		} else {
			res = "MORISTE :(";
		}
		
		texto.setVisible(false);
		boton.setEnabled(false);
		setTitle(res);
	}
	
	public static void main(String[] args) {

		juego game = new juego();

		game.setBounds(0, 0, 450, 510);
		game.setVisible(true);
                game.setLocationRelativeTo(null);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
