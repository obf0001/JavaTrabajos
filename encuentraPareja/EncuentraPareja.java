package encuentrapareja;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.Timer;

public class EncuentraPareja extends JFrame implements ActionListener {

    //Componentes
    private final JButton[][] tableroBtn = new JButton[6][6];

    //Arrays fila ArraysLists
    ArrayList<Integer> parejasNumeros = new ArrayList<>();
    int[][] tableroParejas = new int[6][6];
    boolean[][] arrayWinner = new boolean[6][6];
    
    //Integers
    int cardSelected1;
    int cardSelected2;
    int posX, posY;
    int intentos = 25; //Numero de intentos del jugador
    private int aux1, aux2;
    private int count = 0; //Contador
    
    //Booleans
    boolean isPulsado = false;
    boolean isGanador = false;
    
    //Timer
    long tiemposegunda;
    private Timer timer;
    int segundos = 0;

    //CONSTRUCTOR
    public EncuentraPareja() {
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(710, 780);
        this.setVisible(true);
        this.setLocationRelativeTo(null); //CENTRAR VENTANA JFRAME
        this.setTitle("Juego de Parejas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                tableroBtn[x][y] = new JButton(); //Array de botones
                tableroBtn[x][y].setBounds(50 + x * 100, 50 + y * 100, 100, 100);
                
                this.add(tableroBtn[x][y]);
                //AÃ±adimos un listener a cada boton
                tableroBtn[x][y].addActionListener((ActionListener) this);
            }
        }
        iniciarParejas();
        parejasAleatorio();
    }
    
    public void iniciarParejas() {
        isPulsado = false;
        //Meter imagenes de las parejas
        for (int x = 0; x < tableroBtn.length; x++) {
            for (int y = 0; y < tableroBtn.length; y++) {
                tableroBtn[x][y].setIcon((new ImageIcon("./src/encuentrapareja/imagenes/tuberia.jpg")));
                tableroBtn[x][y].setDisabledIcon((new ImageIcon("./src/encuentrapareja/imagenes/images" + (tableroParejas[x][y] + 1) + ".jpg")));
            }
        }
        for (int x = 0; x < arrayWinner.length; x++) {
            for (int y = 0; y < 6; y++) {
                arrayWinner[x][y] = false;
            }
        }
    }

    public void parejasAleatorio() {
        //Inicio el array llenandolo de parejas de numeros del 0 al 7, es decir, estaria asi: {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7}
        for (int i = 0; i < 18; i++) {
            parejasNumeros.add(i);
            parejasNumeros.add(i);
        }
        
        int columna = 0, fila = 0;
        //Entro en bucle para poder hacer los numeros aleatorios
        while (!parejasNumeros.isEmpty()) {
            //Math random del 0 al 7, mete el numero en la variable posicion
            int posicion = (int) (Math.random() * parejasNumeros.size());
            //Recogo lo que hay en la posicion aleatorio que salio del arraylist fila lo meto en otra variable
            int numero = parejasNumeros.get(posicion);
            //Quito la posicion aleatorio que salio del arraylist, ya he metido ese numero por ello ya no deberÃ­a poder volver a meterlo por ello lo elimino
            parejasNumeros.remove(posicion);
            //Meto el numero en otro array en la posicion [columna][fila]
            tableroParejas[fila][columna] = numero;
            //Como el tableroBtn es un array D2 (6x6), va del 0 al 5. Por ello, si la columna llega a 6 la devolmos a 0 para que no se salga
            if (columna == 5) {
                fila++;
                columna = 0;
            } else {
                columna++;
            }
            /*Al final, la idea es ir sacando numeros del arraylist y pasarselos al array del tableroBtn, eliminando opciones hasta que el arraylist se quede vacio.
            Las parejas de numeros ya se han metido de forma aleatoria en el array D2 tableroBtn*/
        }
    }
    
    //Comprobar si ha ganado el usuario
    public void checkGanar(){
        if (isGanador == true) {
            JOptionPane.showMessageDialog(null, "Ganaste!!!! :D");
            exit(0);
        } else if (intentos == 0) {
            JOptionPane.showMessageDialog(null, "Perdiste :(");
            exit(0);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton desvelarCarta = (JButton) e.getSource();
        count++;

        if (count == 1) {	
            tiemposegunda = System.currentTimeMillis();
        }

        //Controlar que tarde un poco en volver a taparse las cartas
        if (count < 3) {
            for (int f = 0; f < 6; f++) {
                for (int c = 0; c < 6; c++) {
                    if (desvelarCarta.equals(tableroBtn[f][c])) {
                        desvelarCarta.setIcon((new ImageIcon("./src/encuentrapareja/imagenes/images" + (tableroParejas[f][c] + 1) + ".jpg")));
                        if (isPulsado) {
                            cardSelected2 = tableroParejas[f][c];
                            isPulsado = false;

                            //Coinciden que son parejas las cartas seleccionadas
                            if (cardSelected1 == cardSelected2) {
                                //intentosDisponibles.setText("Intentos disponibles:" + intentos);
                                
                                tableroBtn[f][c].setEnabled(false); //Deshabilitar el boton
                                tableroBtn[f][c].setDisabledIcon((new ImageIcon("./src/encuentrapareja/imagenes/images" + (tableroParejas[f][c] + 1) + ".jpg")));
                                arrayWinner[f][c] = true;
                                count = 0;

                            } 
                            //NO coinciden que son parejas las cartas seleccionadas
                            else {
                                intentos--;
                                
                                desvelarCarta.setEnabled(false);
                                desvelarCarta.setIcon((new ImageIcon("./src/encuentrapareja/imagenes/images" + (tableroParejas[f][c] + 1) + ".jpg")));
                                tableroBtn[f][c].setEnabled(false);
                                tableroBtn[f][c].setDisabledIcon((new ImageIcon("./src/encuentrapareja/imagenes/images" + (tableroParejas[f][c] + 1) + ".jpg")));

                                aux1 = f;
                                aux2 = c;

                                if (timer != null) {
                                    timer.stop();
                                }
                                //Crear timer
                                timer = new Timer(1000, new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        
                                        if (segundos == 1) {
                                            segundos++;
                                        } else {
                                            count = 0;
                                        }
                                        tableroBtn[posX][posY].setEnabled(true);
                                        arrayWinner[posX][posY] = false;
                                        desvelarCarta.setEnabled(true);
                                        tableroBtn[posX][posY].setIcon((new ImageIcon("./src/encuentrapareja/imagenes/tuberia.jpg")));
                                        tableroBtn[aux1][aux2].setIcon((new ImageIcon("./src/encuentrapareja/imagenes/tuberia.jpg")));
                                        segundos = 0;
                                        count = 0;

                                        timer = null;
                                    }

                                });

                                timer.setRepeats(false);
                                timer.start(); //Comenzar timer
                            }

                        } else {
                            cardSelected1 = tableroParejas[f][c];
                            isPulsado = true;
                            arrayWinner[f][c] = true;
                            tableroBtn[f][c].setEnabled(false);
                            tableroBtn[f][c].setDisabledIcon((new ImageIcon("./src/encuentrapareja/imagenes/images" + (tableroParejas[f][c] + 1) + ".jpg")));
                            posX = f;
                            posY = c;
                        }
                        
                        isGanador = true;
                        for (int x = 0; x < 6; x++) {
                            for (int y = 0; y < 6; y++) {
                                if (!arrayWinner[x][y]) {
                                    isGanador = false;
                                }
                            }
                        }
                        checkGanar();
                    }
                }

            }
        } else {
            long tiempo = tiemposegunda - System.currentTimeMillis();
            //Si quito el IF de abajo, me permite desvelar otras cartas mientras ya hay dos desveladas (no queremos que esto pase)
            if ((tiempo > 1000L)) {
            } else {}
        }
    }

    //Main
    public static void main(String[] args) {
        new EncuentraPareja();
    }
}
