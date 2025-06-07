package com.mycompany.postuladoscuanticos;

// Importación de librerías
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 🧱 Bloque 1: Declaración de clase y atributos 
// Clase que crea una ventana para aplicar el principio de incertidumbre de Heisenberg
public class VentanaHeisenberg extends JFrame implements ActionListener {
    private JTextField txtDeltaX, txtMasa;
    private JLabel lblDeltaP, lblDeltaV;
    private JButton btnVolver, btnCalcular;

    // 🧱 Bloque 2: Constructor de la ventana
    public VentanaHeisenberg() {
        setTitle("Principio de Incertidumbre de Heisenberg");
        setSize(450, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));

        // Etiqueta y campo de texto para Δx (posición)
        JLabel lblDeltaX = new JLabel("Δx (posición, nm):");
        lblDeltaX.setBounds(30, 30, 180, 20);
        lblDeltaX.setForeground(Color.WHITE);
        txtDeltaX = new JTextField("0.1");
        txtDeltaX.setBounds(220, 30, 180, 25);
        estilizarCampoTexto(txtDeltaX);

        // Etiqueta y campo de texto para masa
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 70, 180, 20);
        lblMasa.setForeground(Color.WHITE);
        txtMasa = new JTextField("9.109e-31");
        txtMasa.setBounds(220, 70, 180, 25);
        estilizarCampoTexto(txtMasa);

        // Botón para realizar el cálculo
        btnCalcular = crearBotonConHover("Calcular Incertidumbre", 120, 110, 200, 30);
        btnCalcular.addActionListener(this);

        // Etiquetas donde se mostrara el resultado
        lblDeltaP = new JLabel("Δp = ?");
        lblDeltaP.setBounds(30, 160, 380, 20);
        estilizarEtiquetaResultado(lblDeltaP);

        lblDeltaV = new JLabel("Δv = ?");
        lblDeltaV.setBounds(30, 190, 380, 20);
        estilizarEtiquetaResultado(lblDeltaV);

        // Botón para volver a la pantalla principal
        btnVolver = crearBotonConHover("Volver", 150, 230, 150, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Agregar componentes a la ventana principal
        add(lblDeltaX);
        add(txtDeltaX);
        add(lblMasa);
        add(txtMasa);
        add(btnCalcular);
        add(lblDeltaP);
        add(lblDeltaV);
        add(btnVolver);

        // Si el usuario cierra la ventana con"X"
        addWindowListener(new WindowAdapter() {
            
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // Estilizar campos de texto
    private void estilizarCampoTexto(JTextField campo) {
        campo.setBackground(new Color(50, 50, 50));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    // Estilizar etiquetas de resultados
    private void estilizarEtiquetaResultado(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setOpaque(true);
        label.setBackground(new Color(40, 40, 40));
        label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    }

    // Botón con estilo y hover
    private JButton crearBotonConHover(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(new Color(60, 60, 60));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

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

    // 🧱 Bloque 3: Método para calcular incertidumbres
    public void actionPerformed(ActionEvent e) {
        try {
            double deltaX = Double.parseDouble(txtDeltaX.getText()) * 1e-9;
            double masa = Double.parseDouble(txtMasa.getText());

            double hbar = 1.0545718e-34; // Constante de Plank
            double deltaP = hbar / (2 * deltaX); // Δp ≥ ħ / 2Δx
            double deltaV = deltaP / masa; // Δv ≥ Δp / m

            lblDeltaP.setText(String.format("Δp ≥ %.3e kg·m/s", deltaP));
            lblDeltaV.setText(String.format("Δv ≥ %.3e m/s", deltaV));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🧱 Bloque 4: Método para volver a la pantalla principal
    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }
}
