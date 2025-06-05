package com.mycompany.postuladoscuanticos;

// Importación de librerías gráficas y eventos
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 🧱 Bloque 1: Declaración de clase y atributos 
// Clase que crea una ventana para aplicar el principio de incertidumbre de Heisenberg
public class VentanaHeisenberg extends JFrame implements ActionListener {
    private JTextField txtDeltaX, txtMasa;
    private JLabel lblDeltaP, lblDeltaV;
    private JButton btnVolver;

    // 🧱 Bloque 2: Constructor - interfaz gráfica 
    public VentanaHeisenberg() {
        setTitle("Principio de Incertidumbre de Heisenberg");
        setSize(450, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Etiqueta y campo de texto para Δx (posición)
        JLabel lblDeltaX = new JLabel("Δx (incertidumbre posición, nm):");
        lblDeltaX.setBounds(30, 30, 200, 20);
        txtDeltaX = new JTextField("0.1");
        txtDeltaX.setBounds(240, 30, 100, 25);

        // Etiqueta y campo de texto para la masa
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 70, 200, 20);
        txtMasa = new JTextField("9.109e-31");
        txtMasa.setBounds(240, 70, 100, 25);

        // Botón para realizar el cálculo
        JButton btnCalcular = new JButton("Calcular Incertidumbre");
        btnCalcular.setBounds(120, 110, 200, 30);
        btnCalcular.addActionListener(this);

        // Etiquetas para mostrar resultados
        lblDeltaP = new JLabel("Δp = ?");
        lblDeltaP.setBounds(30, 150, 400, 20);

        lblDeltaV = new JLabel("Δv = ?");
        lblDeltaV.setBounds(30, 180, 400, 20);

        // Botón para volver a la pantalla principal
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 220, 150, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Agregar todos los componentes a la ventana
        add(lblDeltaX);
        add(txtDeltaX);
        add(lblMasa);
        add(txtMasa);
        add(btnCalcular);
        add(lblDeltaP);
        add(lblDeltaV);
        add(btnVolver);

        // Reabrir ventana principal al cerrar esta
        addWindowListener(new WindowAdapter() {
            
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // 🧱 Bloque 3: Método para calcular incertidumbres 
    
    public void actionPerformed(ActionEvent e) {
        try {
            double deltaX = Double.parseDouble(txtDeltaX.getText()) * 1e-9; // nm a metros
            double masa = Double.parseDouble(txtMasa.getText());

            double hbar = 1.0545718e-34; // Constante de Planck reducida (ħ)
            double deltaP = hbar / (2 * deltaX); // Δp ≥ ħ / 2Δx
            double deltaV = deltaP / masa;      // Δv ≥ Δp / m

            lblDeltaP.setText(String.format("Δp ≥ %.3e kg·m/s", deltaP));
            lblDeltaV.setText(String.format("Δv ≥ %.3e m/s", deltaV));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🧱 Bloque 4: Método para volver a la pantalla principal 
    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }
}
