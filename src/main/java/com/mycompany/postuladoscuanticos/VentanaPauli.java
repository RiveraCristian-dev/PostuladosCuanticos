package com.mycompany.postuladoscuanticos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 🧱 Bloque 1: Definición de clase y atributos 
public class VentanaPauli extends JFrame {
    private JSpinner spinnerParticulas, spinnerEstados;
    private JLabel lblResultado;
    private JButton btnVolver;

    // 🧱 Bloque 2: Constructor diseño de interfaz 
    public VentanaPauli() {
        setTitle("Principio de Exclusión de Pauli");
        setSize(500, 450);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal con 3 filas, 2 columnas
        JPanel panelConfig = new JPanel(new GridLayout(3, 2, 10, 10));
        panelConfig.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Componentes para entrada de datos
        JLabel lblParticulas = new JLabel("Número de fermiones:");
        spinnerParticulas = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));

        JLabel lblEstados = new JLabel("Estados disponibles:");
        spinnerEstados = new JSpinner(new SpinnerNumberModel(4, 1, 20, 1));

        // Etiqueta para mostrar el resultado
        lblResultado = new JLabel("", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));

        // Botón para calcular combinaciones
        JButton btnCalcular = new JButton("Calcular Combinaciones");
        btnCalcular.addActionListener(e -> calcularCombinaciones());

        // Agrega componentes al panel
        panelConfig.add(lblParticulas);
        panelConfig.add(spinnerParticulas);
        panelConfig.add(lblEstados);
        panelConfig.add(spinnerEstados);
        panelConfig.add(btnCalcular);
        panelConfig.add(new JLabel()); 

        // Panel inferior con botón para volver
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volverAPrincipal());
        panelInferior.add(btnVolver);

        // Agrega los paneles a la ventana principal
        add(panelConfig, BorderLayout.NORTH);
        add(lblResultado, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Evento al cerrar con la "X"
        addWindowListener(new WindowAdapter() {
            
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // 🧱 Bloque 3: Método para calcular combinaciones válidas 
    private void calcularCombinaciones() {
        try {
            int particulas = (int) spinnerParticulas.getValue();
            int estados = (int) spinnerEstados.getValue();
            
            if (particulas > estados) {
                lblResultado.setText("¡Error: Partículas > Estados!");
                return;
            }
            
            // Combinatoria: C(estados, particulas) = estados! / (particulas!(estados - particulas)!)
            int combinaciones = factorial(estados) / (factorial(particulas) * factorial(estados - particulas));
            lblResultado.setText("Configuraciones posibles: " + combinaciones);
        } catch (Exception ex) {
            lblResultado.setText("Error en cálculo");
        }
    }

    // 🧱 Bloque 4: Método para volver a la pantalla principal 
    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }

    // 🧱 Bloque 5: Método auxiliar para calcular factorial 
    private int factorial(int n) {
        return (n <= 1) ? 1 : n * factorial(n - 1);
    }
}
