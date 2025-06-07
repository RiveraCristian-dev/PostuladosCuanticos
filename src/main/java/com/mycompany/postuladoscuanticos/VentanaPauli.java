package com.mycompany.postuladoscuanticos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 🧱 Bloque 1: Definición de clase y atributos 
public class VentanaPauli extends JFrame {
    private JSpinner spinnerParticulas, spinnerEstados;
    private JLabel lblResultado;
    private JButton btnVolver, btnCalcular;

    // 🧱 Bloque 2: Constructor de la ventanas
    public VentanaPauli() {
        setTitle("Principio de Exclusión de Pauli");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));

        // Panel principal con 3 filas, 2 columnas
        JPanel panelConfig = new JPanel(new GridLayout(3, 2, 10, 10));
        panelConfig.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelConfig.setBackground(new Color(30, 30, 30));

        // Componentes para entrada de datos
        JLabel lblParticulas = new JLabel("Número de fermiones:");
        estilizarEtiqueta(lblParticulas);
        spinnerParticulas = crearSpinnerEstilizado(2, 1, 10, 1);

        JLabel lblEstados = new JLabel("Estados disponibles:");
        estilizarEtiqueta(lblEstados);
        spinnerEstados = crearSpinnerEstilizado(4, 1, 20, 1);

        btnCalcular = crearBotonConHover("Calcular Combinaciones", null);
        btnCalcular.addActionListener(e -> calcularCombinaciones());

        lblResultado = new JLabel("", SwingConstants.CENTER);
        estilizarResultado(lblResultado);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(new Color(30, 30, 30));
        btnVolver = crearBotonConHover("Volver", new Dimension(120, 30));
        btnVolver.addActionListener(e -> volverAPrincipal());
        panelInferior.add(btnVolver);

        // Agrega componentes al panel
        panelConfig.add(lblParticulas);
        panelConfig.add(spinnerParticulas);
        panelConfig.add(lblEstados);
        panelConfig.add(spinnerEstados);
        panelConfig.add(btnCalcular);
        panelConfig.add(new JLabel());

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

    private Font obtenerFuenteEstilo() {
        return new Font("Segoe UI", Font.PLAIN, 13);
    }

    private void estilizarEtiqueta(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(obtenerFuenteEstilo());
    }

    private void estilizarResultado(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(new Color(40, 40, 40));
        label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    }

    private JSpinner crearSpinnerEstilizado(int initial, int min, int max, int step) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(initial, min, max, step));
        JComponent editor = spinner.getEditor();
        JFormattedTextField field = ((JSpinner.DefaultEditor) editor).getTextField();
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(obtenerFuenteEstilo());
        spinner.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        return spinner;
    }

    private JButton crearBotonConHover(String texto, Dimension size) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(new Color(60, 60, 60));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        if (size != null) boton.setPreferredSize(size);

        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(90, 90, 90));
            }

            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(60, 60, 60));
            }
        });

        return boton;
    }

    private void calcularCombinaciones() {
        try {
            int particulas = (int) spinnerParticulas.getValue();
            int estados = (int) spinnerEstados.getValue();

            if (particulas > estados) {
                lblResultado.setText("¡Error: Partículas > Estados!");
                return;
            }

            int combinaciones = factorial(estados) / (factorial(particulas) * factorial(estados - particulas));
            lblResultado.setText("Configuraciones posibles: " + combinaciones);
        } catch (Exception ex) {
            lblResultado.setText("Error en cálculo");
        }
    }

    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }

    private int factorial(int n) {
        return (n <= 1) ? 1 : n * factorial(n - 1);
    }
}
