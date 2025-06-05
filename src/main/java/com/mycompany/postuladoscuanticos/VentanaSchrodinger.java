package com.mycompany.postuladoscuanticos;

import javax.swing.*;
import java.awt.event.*;

// 🧱 Bloque 1: Declaración de la clase y atributos 
public class VentanaSchrodinger extends JFrame {
    private JSpinner spinnerN;
    private JTextField txtL, txtMasa;
    private JTextArea txtResultado;
    private JButton btnVolver;

    // 🧱 Bloque 2: Constructor (interfaz gráfica) 
    public VentanaSchrodinger() {
        setTitle("Ecuación de Schrödinger - Partícula en Caja");
        setSize(450, 400); 
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Etiqueta y spinner para número cuántico
        JLabel lblN = new JLabel("Número cuántico (n):");
        lblN.setBounds(30, 30, 150, 20);
        spinnerN = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerN.setBounds(190, 30, 50, 25);

        // Etiqueta y campo de texto para longitud de la caja
        JLabel lblL = new JLabel("Longitud de la caja (Å):");
        lblL.setBounds(30, 70, 150, 20);
        txtL = new JTextField("1.0");
        txtL.setBounds(190, 70, 100, 25);

        // Etiqueta y campo para masa de la partícula
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 110, 150, 20);
        txtMasa = new JTextField("9.109e-31"); // masa del electrón por defecto
        txtMasa.setBounds(190, 110, 100, 25);

        // Botón para realizar el cálculo
        JButton btnCalcular = new JButton("Calcular Energía");
        btnCalcular.setBounds(150, 150, 150, 30);
        
        // Área de texto para mostrar resultado
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 190, 390, 100);

        // Botón para volver a la ventana principal
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 300, 150, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Acción del botón Calcular
        btnCalcular.addActionListener(e -> {
            try {
                int n = (int) spinnerN.getValue();
                double L = Double.parseDouble(txtL.getText()) * 1e-10; // Ångstrom a metros
                double m = Double.parseDouble(txtMasa.getText());
                
                double E = calcularEnergia(n, L, m);
                txtResultado.setText(String.format("Energía para n=%d:\n%.3e J\n≈ %.3f eV", 
                    n, E, E / 1.602e-19)); // Conversión a eV
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar todos los componentes a la ventana
        add(lblN);
        add(spinnerN);
        add(lblL);
        add(txtL);
        add(lblMasa);
        add(txtMasa);
        add(btnCalcular);
        add(scroll);
        add(btnVolver);

        // Listener para cuando se cierra la ventana con la "x"
        addWindowListener(new WindowAdapter() {
            
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // 🧱 Bloque 3: Método para volver al menú principal
    private void volverAPrincipal() {
        this.dispose(); 
        new PostuladosCuanticos().setVisible(true);
    }

    // 🧱 Bloque 4: Método para calcular la energía según la ecuación de Schrödinger 
    private double calcularEnergia(int n, double L, double m) {
        double hbar = 1.0545718e-34; // Constante de Planck reducida
        return (Math.pow(n * Math.PI * hbar, 2)) / (2 * m * Math.pow(L, 2));
    }
}

