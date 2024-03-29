import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.Math;

public class Adivinar2 extends JFrame implements ActionListener {

    // COMPONENTES VISUALES:
    private JLabel etiqueta, imagen, mensaje1, mensaje2, intentos, laCarta;
    private JTextArea area;
    private JScrollPane scroll;
    private JButton boton, comenzar;
    private JTextField campo1, campo2;
    private JMenuBar barra;
    private JMenu acerca;
    private JMenuItem creador;
    public ImageIcon imagenDeLaCarta;

    // variables de control:

    boolean noEsPrimerIntento = false;
    String cartaPalo, inPalo, paloAnterior = "", entradaNumero;
    int inNumero, cartaNumero, i, indiN, indiS;
    boolean carta = false; // boleanos que si ambos son true el jugador gano
    boolean palo = false;

    // ATRIBUTOS

    String palos[] = { "corazones", "diamantes", "treboles", "picas" }; // array con los posibles palos y cartas, seran
                                                                        // usados para generar la carta aleatoriamente
                                                                        // con Math.random
    int numeros[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

    public Adivinar2() { // constructor

        // colores usados varias veces:
        Color verde = new Color(70, 255, 70);
        Color verde2 = new Color(60, 233, 60);
        Color negro = new Color(0, 0, 0);
        Color gris = new Color(222, 222, 222);
        Color blanco = new Color(235, 235, 235);

        Font arial1 = new Font("Arial", 1, 12);
        String welcomeText = "\n Una carta aleatoria de la baraja inglesa ha sido generada.\n\n Intente adividarla, posee 3 intentos."
                + "\n\n Las cartas posibles son : 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | J | Q | K | A."
                + "\n Los palos posibles son : diamantes | treboles | corazones | picas."
                + "\n\n Pulse el boton comenzar para iniciar el juego.";

        setLayout(null);

        setIconImage(new ImageIcon(getClass().getResource("images/icon.ico")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Adivina la carta");
        getContentPane().setBackground(verde2);

        barra = new JMenuBar();
        setJMenuBar(barra);

        acerca = new JMenu("Acerca de");
        acerca.setBackground(gris);
        acerca.setForeground(verde2);
        acerca.setFont(arial1);

        creador = new JMenuItem("Programador");
        creador.setForeground(negro);
        creador.setFont(arial1);
        creador.addActionListener(this);
        acerca.add(creador);

        etiqueta = new JLabel(" Bienvenido al juego de adivinar la carta");
        etiqueta.setBounds(10, 10, 500, 50);
        etiqueta.setFont(new Font("Arial", 3, 24));
        etiqueta.setForeground(negro);
        add(etiqueta);

        mensaje1 = new JLabel("Ingrese el palo de la carta que cree que es:");
        mensaje1.setBounds(20, 80, 250, 25);
        mensaje1.setFont(arial1);
        mensaje1.setForeground(negro);
        add(mensaje1);

        campo1 = new JTextField();
        campo1.setBounds(20, 105, 250, 25);
        campo1.setBackground(gris); // un tipo de gris.
        campo1.setFont(new Font("Arial", 0, 12));
        campo1.setForeground(negro);
        campo1.setEnabled(false);
        add(campo1);

        mensaje2 = new JLabel("Ingrese el numero de la carta que cree que es: ");
        mensaje2.setBounds(20, 170, 270, 25);
        mensaje2.setFont(new Font("Arial", 1, 12));
        mensaje2.setForeground(negro);
        add(mensaje2);

        campo2 = new JTextField();
        campo2.setBounds(20, 195, 250, 25);
        campo2.setBackground(gris); // un tipo de gris.
        campo2.setFont(new Font("Arial", 0, 12));
        campo2.setForeground(negro); // negro
        campo2.setEnabled(false);
        add(campo2);

        ImageIcon image = new ImageIcon("images/inicio.png"); // label carta imagen
        imagen = new JLabel(image);
        imagen.setBounds(357, 60, 65, 98);
        add(imagen);

        area = new JTextArea();
        area.setText(welcomeText);
        area.setFont(new Font("Verdana", 2, 14));
        area.setEditable(false);
        area.setForeground(new Color(0, 0, 0));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(10, 290, 545, 180);
        add(scroll);

        boton = new JButton("INTENTAR");
        boton.setBounds(80, 240, 120, 30);
        boton.setBackground(blanco);
        boton.setFont(new Font("Arial", 1, 16));
        boton.setForeground(verde);
        boton.addActionListener(this);
        boton.setEnabled(false);
        add(boton);

        comenzar = new JButton("COMENZAR");
        comenzar.setBounds(320, 240, 150, 30);
        comenzar.setBackground(blanco);
        comenzar.setFont(new Font("Arial", 1, 16));
        comenzar.setForeground(verde); // verde
        comenzar.addActionListener(this);
        add(comenzar);

        intentos = new JLabel("Intentos restantes: - ");
        intentos.setBounds(325, 190, 270, 25);
        intentos.setFont(new Font("Arial", 1, 14));
        intentos.setForeground(negro);
        add(intentos);
    }

    public void mostrarCarta(String direccion) {

        /**
         * muestra la imagen de la carta en el display
         *
         * @param <direccion> direccion de la imagen para el ImageIcon
         * @return void
         */

        sacarImagenInicial();

        this.imagenDeLaCarta = new ImageIcon(direccion);
        this.laCarta = new JLabel(imagenDeLaCarta);
        this.laCarta.setBounds(357, 60, 65, 98);
        add(laCarta);
        this.laCarta.setVisible(true);
    }

    public void preguntarYmostrarCartas() {

        if (cartaPalo.equals("corazones")) {
            mostrarCarta("images/" + cartaNumero + "C.png");
        } else if (cartaPalo.equals("treboles")) {
            mostrarCarta("images/" + cartaNumero + "t.png");
        } else if (cartaPalo.equals("picas")) {
            mostrarCarta("images/" + cartaNumero + "p.png");
        } else if (cartaPalo.equals("diamantes")) {
            mostrarCarta("images/" + cartaNumero + "d.png");
        }
    }

    public void ponerImagenInicial() {

        imagen.setVisible(true);
    }

    public void sacarImagenInicial() {

        imagen.setVisible(false);
    }

    public void setearImagenes() {

        // evita errores
        laCarta.setVisible(false);
        ponerImagenInicial();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == comenzar) {

            if (noEsPrimerIntento) {

                setearImagenes();
            }

            noEsPrimerIntento = true;

            indiN = (int) (Math.random() * 13); // devolvera un numero entero entre 0 y 12, ya que la funcion random
                                                // nunca retorna el valor 1.0, sino lo
            indiS = (int) (Math.random() * 4); // devolvera un numero entero entre 0 y 3
            cartaPalo = palos[indiS];
            cartaNumero = numeros[indiN];
            paloAnterior = "";

            area.setText("\n El juego ha comenzado.");
            boton.setEnabled(true);
            campo1.setEnabled(true);
            campo2.setEnabled(true);
            comenzar.setEnabled(false);

            i = 0;

            intentos.setText("Intentos restantes: " + (3 - i));

        }

        if (e.getSource() == boton) {

            palo = carta = false; // para que no tengan el valor de la jugada anterior

            // a continuacionse toman las entradas y se las hace minusculas para mas
            // versatilidad
            inPalo = campo1.getText().toLowerCase(); // entrada de datos de los TextFields
            entradaNumero = campo2.getText().toString().toLowerCase();

            if ((!inPalo.equals("treboles") && !inPalo.equals("picas") && !inPalo.equals("corazones")
                    && !inPalo.equals("diamantes"))
                    || (!entradaNumero.equals("2") && !entradaNumero.equals("3") && !entradaNumero.equals("4")
                            && !entradaNumero.equals("5")// corrobora que este bien escrito lo que se toma
                            && !entradaNumero.equals("6") && !entradaNumero.equals("7") && !entradaNumero.equals("8")
                            && !entradaNumero.equals("9")
                            && !entradaNumero.equals("10") && !entradaNumero.equals("j") && !entradaNumero.equals("q")
                            && !entradaNumero.equals("k")
                            && !entradaNumero.equals("a"))) {

                JOptionPane.showMessageDialog(null, "Debes ingresar un palo o numero correcto de la baraja inglesa."); // y
                                                                                                                       // si
                                                                                                                       // esta
                                                                                                                       // mal
                                                                                                                       // muestra
                                                                                                                       // un
                                                                                                                       // mensaje

            } else {

                i++; // aumento el indice que verificara cuantas intentos se han producido

                if (entradaNumero.equals("J") || entradaNumero.equals("j")) { // verifica si es alguna de las letras y
                                                                              // se le da su numero hipotetico en valor
                                                                              // correspondiente, sino parsea a int.

                    inNumero = 11;

                } else if (entradaNumero.equals("Q") || entradaNumero.equals("q")) {

                    inNumero = 12;

                } else if (entradaNumero.equals("K") || entradaNumero.equals("k")) {

                    inNumero = 13;

                } else if (entradaNumero.equals("A") || entradaNumero.equals("a")) {

                    inNumero = 14;

                } else {

                    inNumero = Integer.parseInt(entradaNumero);
                }

                area.setText("\n Segun su respuesta:"); // pistas

                if (cartaNumero == inNumero) {

                    area.setText(area.getText() + "\n\n Segun los numeros de las carta: esa es la carta.");
                    carta = true;

                } else if (cartaNumero > inNumero) {

                    area.setText(area.getText() + "\n\n Segun los numeros de las cartas: la carta es mayor.");

                } else {

                    area.setText(area.getText() + "\n\n Segun los numeros de las cartas: la carta es menor.");
                }

                if (cartaPalo.equals(inPalo)) {

                    area.setText(area.getText() + "\n Segun los palos de las cartas: ese es el palo.");
                    palo = true;

                } else if (inPalo.equals(paloAnterior)) {

                    area.setText(area.getText() + "\n Ya te dijimos que ese no es el palo.");
                    paloAnterior = inPalo;

                } else {

                    area.setText(area.getText() + "\n Segun los palos de las cartas: es de otro palo");
                    paloAnterior = inPalo;
                }

                campo1.setText(""); // limpio los TextFields despues de cada intento.
                campo2.setText("");

                if (carta && palo) {

                    preguntarYmostrarCartas();

                    area.setText("\n GANASTE.");
                    intentos.setText("Intentos restantes: -");
                    campo1.setEnabled(false); // vuelvo a desactivar los botones
                    campo2.setEnabled(false);
                    boton.setEnabled(false);
                    comenzar.setEnabled(true);

                } else if (i >= 3) { // if de cuando ya no quedan intentos y se pierde

                    preguntarYmostrarCartas();

                    if (cartaNumero == 11) { // para que se vea bien el mensaje de perdiste en el caso que el numero
                                             // haya sido una de las letras j,q,k o a, ya que a ellas les corresponden
                                             // los numero 11,12,13 y 14 dentro de su variable.

                        area.setText("\n Perdiste." + "\n La carta era: J de " + cartaPalo + ".");

                    } else if (cartaNumero == 12) {

                        area.setText("\n Perdiste." + "\n La carta era: Q de " + cartaPalo + ".");

                    } else if (cartaNumero == 13) {

                        area.setText("\n Perdiste." + "\n La carta era: K de " + cartaPalo + ".");

                    } else if (cartaNumero == 14) {

                        area.setText("\n Perdiste." + "\n La carta era: A de " + cartaPalo + ".");

                    } else {

                        area.setText("\n Perdiste." + "\n La carta era: " + cartaNumero + " de " + cartaPalo + ".");

                    }

                    intentos.setText("Intentos restantes: -");
                    campo1.setEnabled(false);
                    campo2.setEnabled(false);
                    boton.setEnabled(false);
                    comenzar.setEnabled(true);

                } else {

                    intentos.setText("Intentos restantes: " + (3 - i));// actualizo los intentos restantes
                }

            }

        }

        if (e.getSource() == creador) {

            JOptionPane.showMessageDialog(null, "2019 | Programador: Jpsq | saluzzojuampi@gmail.com");
        }
    }

    public static void main(String args[]) {

        Adivinar2 ad = new Adivinar2();
        ad.setBounds(0, 0, 570, 540);
        ad.setResizable(false);
        ad.setVisible(true);
        ad.setLocationRelativeTo(null);

    }

}
