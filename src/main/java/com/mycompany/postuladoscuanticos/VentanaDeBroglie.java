package com.mycompany.postuladoscuanticos;

// Importación de librerías gráficas y eventos
import javax.swing.*;
import java.awt.event.*;

// 🧱 Bloque 1: Declaración de clase y componentes 
// Clase que representa la ventana para calcular la longitud de onda de De Broglie
public class VentanaDeBroglie extends JFrame implements ActionListener {
    private JTextField txtMasa, txtVelocidad;
    private JLabel lblResultado;
    private JButton btnVolver;

    // 🧱 Bloque 2: Constructor de la ventana 
    public VentanaDeBroglie() {
        setTitle("Dualidad Onda-Partícula");           // Título de la ventana
        setSize(400, 300);                             // Tamaño de la ventana
        setLayout(null);                               // Layout absoluto (manual)
        setLocationRelativeTo(null);                   // Centrar en pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra esta ventana sin terminar el programa

        // Etiqueta y campo de texto para masa
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 30, 100, 20);
        txtMasa = new JTextField();
        txtMasa.setBounds(130, 30, 200, 25);

        // Etiqueta y campo de texto para velocidad
        JLabel lblVelocidad = new JLabel("Velocidad (m/s):");
        lblVelocidad.setBounds(30, 70, 100, 20);
        txtVelocidad = new JTextField();
        txtVelocidad.setBounds(130, 70, 200, 25);

        // Botón para calcular la longitud de onda
        JButton btnCalcular = new JButton("Calcular λ");
        btnCalcular.setBounds(150, 120, 100, 30);
        btnCalcular.addActionListener(this); // Evento al hacer clic

        // Etiqueta donde se mostrará el resultado
        lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(30, 160, 340, 30);

        // Botón para volver a la ventana principal
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 200, 100, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Agregando todos los componentes a la ventana
        add(lblMasa);
        add(txtMasa);
        add(lblVelocidad);
        add(txtVelocidad);
        add(btnCalcular);
        add(lblResultado);
        add(btnVolver);

        // Evento para cuando se cierre la ventana (reabre la principal)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // 🧱 Bloque 3: Método para volver a la ventana principal 
    private void volverAPrincipal() {
        this.dispose(); // Cierra esta ventana
        new PostuladosCuanticos().setVisible(true); // Reabre la principal
    }

    // 🧱 Bloque 4: Método para calcular la longitud de onda 
    public void actionPerformed(ActionEvent e) {
        try {
            double masa = Double.parseDouble(txtMasa.getText());
            double velocidad = Double.parseDouble(txtVelocidad.getText());
            double h = 6.626e-34; // Constante de Planck
            double lambda = h / (masa * velocidad); // Fórmula λ = h / (m·v)
            lblResultado.setText(String.format("λ = %.3e m (%.3f nm)", lambda, lambda * 1e9));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
