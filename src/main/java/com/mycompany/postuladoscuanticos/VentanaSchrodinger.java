// 🧱 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;

import javax.swing.*;       //Componentes de interfaz gráfica
import java.awt.*;          //Clases para manejo de colores y fuentes
import java.awt.event.*;    //Manejo de eventos (clicks)

public class VentanaSchrodinger extends JFrame {
    
    // 🧱 Bloque 2: Declaración de componentes
    private JSpinner spinnerN;                //Selector para número cuántico (n)
    private JTextField txtL, txtMasa;         //Campo para la longitud de caja y masa de la partícula
    private JTextArea txtResultado;           //Área para mostrar los resultados
    private JButton btnVolver;                //Botón para volver al menú

    // 🧱 Bloque 3: Constructor de la ventana
    public VentanaSchrodinger() {
        setTitle("Ecuación de Schrödinger - Partícula en Caja");
        setSize(450, 400);                                   //Dimenciones (ancho, alto)
        setLayout(null);                                     //Layout absoluto posicionamiento
        setLocationRelativeTo(null);                         //Centrar ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //Cierra la ventana

        EstilosSwing.aplicarFondoVentana(this);              //Estilo constante de EstilosSwing estilo de fondo

        // 🧱 Bloque 4: Configuración del selector de número cuántico
        JLabel lblN = new JLabel("Número cuántico (n):");
        lblN.setBounds(30, 30, 150, 20);                     //Posición y tamaño
        EstilosSwing.estilizarLabel(lblN, false);            //Estilo constante de EstilosSwing
        add(lblN); //añade el componente(en esta ocasión no esta agrupado como codigod anteriores)
        
        //Spinner con valores de 1 a 10, incrementando de 1 en 1
        spinnerN = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerN.setBounds(190, 30, 50, 25);
        EstilosSwing.estilizarSpinner(spinnerN);
        add(spinnerN);

        // 🧱 Bloque 5: Configuración de campos de entrada
        //Campo para longitud de la caja (en Angstroms)
        JLabel lblL = new JLabel("Longitud de la caja (Å):");
        lblL.setBounds(30, 70, 150, 20);
        EstilosSwing.estilizarLabel(lblL, false);
        add(lblL);

        txtL = new JTextField("1.0"); //Valor por defecto: 1 Angstrom
        txtL.setBounds(190, 70, 100, 25);
        EstilosSwing.estilizarCampoTexto(txtL);
        add(txtL);

        //Campo para masa de la partícula (kg)
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 110, 150, 20);
        EstilosSwing.estilizarLabel(lblMasa, false);
        add(lblMasa);

        txtMasa = new JTextField("9.109e-31"); //Valor por defecto:masa del electrón
        txtMasa.setBounds(190, 110, 100, 25);
        EstilosSwing.estilizarCampoTexto(txtMasa);
        add(txtMasa);

        // 🧱 Bloque 6: Configuración de botones
        JButton btnCalcular = EstilosSwing.crearBotonConHover("Calcular Energía", 150, 150, 150, 30);
        add(btnCalcular);

        // 🧱 Bloque 7: Configuración del área de resultados
        txtResultado = new JTextArea();
        txtResultado.setEditable(false); //Solo lectura
        txtResultado.setForeground(Color.WHITE); //Texto Blanco
        txtResultado.setBackground(new Color(40, 40, 40)); // Fondo obscuro
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtResultado.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));

        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 190, 390, 100);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        add(scroll);
        
        //Botón para volver al menu principal
        //Estilo de boton con hover
        btnVolver = EstilosSwing.crearBotonConHover("Volver", 150, 300, 150, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());
        add(btnVolver);

        // 🧱 Bloque 8: Acción del botón calcular
        btnCalcular.addActionListener(e -> {
            try {
                int n = (int) spinnerN.getValue();
                double L = Double.parseDouble(txtL.getText()) * 1e-10; //Convertir A a m
                double m = Double.parseDouble(txtMasa.getText());

                double E = calcularEnergia(n, L, m);
                //Muestra resultado en Joules y electrovolts
                txtResultado.setText(String.format("Energía para n=%d:\n%.3e J\n≈ %.3f eV",
                        n, E, E / 1.602e-19));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // 🧱 Bloque 9: Si el usuario cierra la ventana con"X"
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // 🧱 Bloque 10 Metodos auxiliares
    //Volver a la ventana principal
    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }

    private double calcularEnergia(int n, double L, double m) {
        double hbar = 1.0545718e-34; // Constante de Planck reducida
        return (Math.pow(n * Math.PI * hbar, 2)) / (2 * m * Math.pow(L, 2));
    }
}
