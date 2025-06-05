package com.mycompany.postuladoscuanticos;

import javax.swing.*;
import java.awt.event.*;

// 🧱 Bloque 1: Definición de la clase y atributos 
public class VentanaPlanck extends JFrame implements ActionListener {
    private JTextField txtFrecuencia, txtTemperatura;
    private JLabel lblResultado;
    private JButton btnVolver;

    // 🧱 Bloque 2: Constructor de la interfaz gráfica 
    public VentanaPlanck() {
        setTitle("Ley de Planck - Cuerpo Negro");
        setSize(400, 350); 
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Etiquetas y campos de texto
        JLabel lblFrecuencia = new JLabel("Frecuencia (THz):");
        lblFrecuencia.setBounds(30, 30, 120, 20);
        txtFrecuencia = new JTextField();
        txtFrecuencia.setBounds(160, 30, 200, 25);

        JLabel lblTemperatura = new JLabel("Temperatura (K):");
        lblTemperatura.setBounds(30, 70, 120, 20);
        txtTemperatura = new JTextField();
        txtTemperatura.setBounds(160, 70, 200, 25);

        // Botón para calcular
        JButton btnCalcular = new JButton("Calcular Radiancia");
        btnCalcular.setBounds(120, 120, 160, 30);
        btnCalcular.addActionListener(this);

        // Etiqueta para mostrar resultado
        lblResultado = new JLabel("Resultado:", SwingConstants.CENTER);
        lblResultado.setBounds(30, 160, 340, 60);

        // Botón para volver al menú principal
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 240, 100, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Agregar todos los componentes
        add(lblFrecuencia);
        add(txtFrecuencia);
        add(lblTemperatura);
        add(txtTemperatura);
        add(btnCalcular);
        add(lblResultado);
        add(btnVolver);

        // Acción al cerrar ventana con "x"
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // 🧱 Bloque 3: Método para volver al menú principal 
    private void volverAPrincipal() {
        this.dispose(); // Cierra esta ventana
        new PostuladosCuanticos().setVisible(true); // Abre la principal
    }

    // 🧱 Bloque 4: Manejo del evento del botón Calcular 
    public void actionPerformed(ActionEvent e) {
        try {
            double frecuencia = Double.parseDouble(txtFrecuencia.getText()) * 1e12; // THz a Hz
            double temperatura = Double.parseDouble(txtTemperatura.getText());
            
            double B = calcularLeyPlanck(frecuencia, temperatura);

            // Mostrar en formato científico y también en kJ/m²·µm·sr
            lblResultado.setText(String.format(
                "<html>Radiancia espectral: <b>%.3e W/m²·Hz·sr</b><br>≈ %.3f kJ/m²·µm·sr</html>", 
                B, B * 1e-9));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🧱 Bloque 5: Método para calcular la Ley de Planck 
    private double calcularLeyPlanck(double frecuencia, double T) {
        double h = 6.626e-34, c = 3e8, k = 1.381e-23;
        return (2 * h * Math.pow(frecuencia, 3)) / 
               (Math.pow(c, 2) * (Math.exp((h * frecuencia) / (k * T)) - 1));
    }
}
