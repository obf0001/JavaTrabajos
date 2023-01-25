package buscaminitas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BuscaMinitas extends javax.swing.JFrame implements ActionListener {

    private Boton boton[][] = new Boton[8][8];
    int xBomba;
    int yBomba;

    //private JButton b;
    public BuscaMinitas() {
        setLayout(null);

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                boton[i][j] = new Boton();
                //tamano de botones
                boton[i][j].setBounds(1 + i * 30, 1 + j * 30, 30, 30);
                add(boton[i][j]);
                boton[i][j].Bomba = false;

                boton[i][j].addActionListener(this);

            }

        }
        int aleatorio = (int) (Math.floor(Math.random() * (7 - 0 + 1)) + 0);
        int aleatorio2 = (int) (Math.floor(Math.random() * (7 - 0 + 1)) + 0);
        xBomba = aleatorio;
        yBomba = aleatorio2;
        boton[xBomba][yBomba].Bomba = true;

        //tamano de ventana
        setBounds(0, 0, boton[0][0].getWidth() * 8 + 18, boton[0][0].getHeight() * 9 + 11);

    }

    public class Boton extends JButton {

        boolean Bomba = false;
    }

    public void actionPerformed(ActionEvent e) {

        for (int x = 0; x < 8; x++) {

            for (int y = 0; y < 8; y++) {

                if (boton[x][y] == e.getSource()) {
                    //Comprobamos que no sea bomba
                    if (boton[x][y].Bomba == false) {
                        if (x - xBomba == 0 | x - xBomba == 1 | x - xBomba == -1 && y - yBomba == 0 | y - yBomba == 1 | y - yBomba == -1) {
                            boton[x][y].setBackground(Color.orange);
                        } else if (x - xBomba == 2 | x - xBomba == -2 | x - xBomba == -1 | x - xBomba == 0 | x - xBomba == 1 &&
                                 y - yBomba == 2 | y - yBomba == -2 | y - yBomba == -1 | y - yBomba == 0 | y - yBomba == 1) {
                            boton[x][y].setBackground(Color.blue);
                        } else {
                            boton[x][y].setBackground(Color.green);
                        }

                    } else {
                        boton[x][y].setBackground(Color.red);
                        JOptionPane.showMessageDialog(null, "Has Encontrado la mina");
                    }

                }

            }

        }

    }

    public static void main(String[] args) {
        BuscaMinitas b = new BuscaMinitas();
        b.setVisible(true);
        b.setLocationRelativeTo(null);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
