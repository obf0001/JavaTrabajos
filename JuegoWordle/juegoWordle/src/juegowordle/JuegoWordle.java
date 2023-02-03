package juegowordle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class JuegoWordle {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JLabel title;
    private final JPanel wordGuessesPanel;
    private final JPanel[] wordGuessRowPanels;

    /**
     * Fuente Custom
     */
    private Font robotoBlack24px;
    private Font robotoBlack18px;

    /**
     * La fila y la columna actuales en
     * la cuadrícula de juego
     */
    private int cursorFila;
    private int cursorColumna;

    /**
     * Colores
     */
    private static final Color charColorDefault = new Color(230, 230, 230);
    private static final Color charFalloColor = new Color(255, 102, 102);
    private static final Color charPosicionIncorrectaColor = new Color(255, 255, 127);
    private static final Color charCorrrectoColor = new Color(127, 255, 127);

    /*
     * Si el juego esta activo
     */
    private boolean juegoActivo;

    /*
     * Plabra random que hay que adivinar
     */
    private String palabraAdivinar = "";

    public JuegoWordle() {
        /* Registramos la Fuente customizable */
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            gEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Roboto-Black.ttf")));

            robotoBlack24px = new Font("Cooper Black", Font.PLAIN, 24);
            robotoBlack18px = new Font("Cooper Black", Font.PLAIN, 18);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        /* Escoje una palabra random al principio */
        EscogerPalabraRandom();

        /* Activamos el juego */
        juegoActivo = true;
        
        /* Posicion inicial del cursor */
        cursorFila = 0;
        cursorColumna = -1;

        /* Creamos un JFrame */
        frame = new JFrame("Wordle");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(700, 400, 0, 0);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {

                /* Ignora los inputs si el juego no esta activo */
                if (!juegoActivo) {
                    return;
                }

                /* Comprueba que ha pulsado una tecla */
                if (Character.isLetter(event.getKeyChar())) {
                    /* Ignora si esta al final de la columna */
                    if (cursorColumna >= 4) {
                        return;
                    }

                    /* Incrementa la columna e inserta un char */
                    cursorColumna++;
                    setAdivinarChar(cursorFila, cursorColumna, charColorDefault, Character.toUpperCase(event.getKeyChar()));
                    return;
                }
                
                /* Sin pulsa borrar, borra un char */
                if (event.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    /* Ignora si esta al final de la columna*/
                    if (cursorColumna < 0) {
                        return;
                    }
                    
                    /* Le ponemos al char ' ' y restamos la columna*/
                    setAdivinarChar(cursorFila, cursorColumna, charColorDefault, ' ');
                    cursorColumna--;
                    return;
                }

                /* Sin pulsa ENTER, inserta la palabra */
                if (event.getKeyChar() == KeyEvent.VK_ENTER) {
                    /* Ignore if not enough letters */
                    if (cursorColumna < 4) {
                        return;
                    }

                    /* Construct word and check it exists in the file */
                    String word = "";
                    for (int i = 0; i < 5; i++) {
                        word = word + getAdivinarChar(cursorFila, i);
                    }
                    if (!palabrasGuardadas(word)) {
                        JOptionPane.showMessageDialog(frame, "Esa palabra no existe", "Esa palabra no existe", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    /* Check if guess was correct */
                    boolean success = checkIntento();

                    /* If not success, but not on last row, increment row and reset column */
                    if (!success && cursorFila < 4) {
                        cursorColumna = -1;
                        cursorFila++;
                        return;
                    }

                    /* If not success and at on last row, stop game and reveal answer */
                    if (!success && !(cursorFila < 4)) {
                        JOptionPane.showMessageDialog(frame, "La palabra era "+palabraAdivinar, "Has failed", JOptionPane.ERROR_MESSAGE);
                        juegoActivo = false;
                        return;
                    }

                    JOptionPane.showMessageDialog(frame, "Bien hecho lo has adivinado", "Has ganado", JOptionPane.INFORMATION_MESSAGE);
                    juegoActivo = false;
                }
            }
        });
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel);

        title = new JLabel("Wordle");
        title.setFont(robotoBlack24px);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(title);

        wordGuessesPanel = new JPanel();
        wordGuessesPanel.setLayout(new BoxLayout(wordGuessesPanel, BoxLayout.Y_AXIS));
        mainPanel.add(wordGuessesPanel);

        wordGuessRowPanels = new JPanel[5];

        for (int y = 0; y < 5; y++) {
            JPanel wordGuessRowPanel = new JPanel();
            wordGuessRowPanel.setLayout(new BoxLayout(wordGuessRowPanel, BoxLayout.X_AXIS));
            wordGuessRowPanels[y] = wordGuessRowPanel;

            for (int x = 0; x < 5; x++) {
                JPanel characterPanel = new JPanel();
                characterPanel.setLayout(new BorderLayout());
                characterPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                characterPanel.setMinimumSize(new Dimension(50, 50));
                characterPanel.setPreferredSize(new Dimension(50, 50));
                characterPanel.setMaximumSize(new Dimension(50, 50));

                JLabel characterLabel = new JLabel("", SwingConstants.CENTER);
                characterLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                characterLabel.setOpaque(true);
                characterLabel.setFont(robotoBlack18px);
                characterLabel.setBackground(charColorDefault);
                characterPanel.add(characterLabel);

                wordGuessRowPanel.add(characterPanel);
            }

            wordGuessesPanel.add(wordGuessRowPanel);
        }

        frame.pack();
    }

    private final boolean palabrasGuardadas(String word) {
        boolean inWordsFile = false;

        /* Linear search for word, def could be optimised */
        try {
            long count = Files.readAllLines(new File("palabras.txt").toPath())
                .stream()
                .filter(s -> s.equalsIgnoreCase(word))
                .count();
            
            if (count > 0) {
                inWordsFile = true;
            }
        } catch (IOException e) {
            
        }

        return inWordsFile;
    }

    private final void EscogerPalabraRandom() {
        File file = new File("palabras.txt");
        try {
            List<String> words = Files.readAllLines(file.toPath());

            palabraAdivinar = words.get(new Random().nextInt(words.size())).toUpperCase();
        } catch (IOException e) {
            //Si da error que se ponga morir como palabra por defecto
            palabraAdivinar = "MORIR";
        }
    }

    private final boolean checkIntento() {

        Hashtable<Character, Integer> characterCounts = new Hashtable<>();

        for (int i = 0; i < 5; i++) {
            char c = getAdivinarChar(cursorFila, i);

            if (characterCounts.get(c) == null) {
                characterCounts.put(c, 0);
            }
        }

        Boolean[] checkedIndexes = new Boolean[] {false, false, false, false, false};

        boolean isAllCorrect = true;

        for (int i = 0; i < 5; i++) {
            char c = getAdivinarChar(cursorFila, i);

            if (palabraAdivinar.charAt(i) == c) {
                checkedIndexes[i] = true;
                setAdivinarChar(cursorFila, i, charCorrrectoColor, c);
                characterCounts.put(c, characterCounts.get(c)+1);
                continue;
            }

            isAllCorrect = false;
        }

        if (isAllCorrect) {
            return true;
        }
        
        for (int i = 0; i < 5; i++) {
            if (checkedIndexes[i]) {
                continue;
            }

            char c = getAdivinarChar(cursorFila, i);

            long characterAppearances = palabraAdivinar.chars().filter(ch -> ch == c).count();

            if (characterCounts.get(c) >= characterAppearances) {
                setAdivinarChar(cursorFila, i, charFalloColor, c);
                characterCounts.put(c, characterCounts.get(c)+1);
                continue;
            }
            setAdivinarChar(cursorFila, i, charPosicionIncorrectaColor, c);
            characterCounts.put(c, characterCounts.get(c)+1);
        }

        return false;
    }

    private final char getAdivinarChar(int row, int col) {
        JPanel characterPanel = (JPanel) wordGuessRowPanels[row].getComponent(col);
        JLabel characterLabel = (JLabel) characterPanel.getComponent(0);

        return characterLabel.getText().toCharArray()[0];
    }

    private final void setAdivinarChar(int row, int col, Color color, char character) {
        JPanel characterPanel = (JPanel) wordGuessRowPanels[row].getComponent(col);
        JLabel characterLabel = (JLabel) characterPanel.getComponent(0);

        characterLabel.setText(Character.toString(character));
        characterLabel.setBackground(color);
    }

    /**
     * Empezamos el juego
     */
    public final void start() {
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        JuegoWordle game = new JuegoWordle();
        game.start();
    }
}
