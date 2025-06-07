package com.mycompany.postuladoscuanticos;

// Importación de librerías 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 🧱 Bloque 1: Declaración de clase y componentes
// Clase que rempresenta la ventana
public class VentanaDeBroglie extends JFrame implements ActionListener {

    private JTextField txtMasa, txtVelocidad;
    private JLabel lblResultado;
    private JButton btnVolver, btnCalcular;

    // 🧱 Bloque 2: Constructor de la ventana
    public VentanaDeBroglie() {
        setTitle("Dualidad Onda-Partícula"); // Título de la ventana
        setSize(400, 300); // Tamaño de la ventana
        setLayout(null); // Layout absoluto (manual)
        setLocationRelativeTo(null); // centrar en pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra esta ventana sin terminar el programa
        getContentPane().setBackground(new Color(30, 30, 30)); // Fondo gris oscuro elegante

        // Etiqueta y campo de texto para masa
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 30, 100, 20);
        lblMasa.setForeground(Color.WHITE);
        txtMasa = new JTextField();
        txtMasa.setBounds(130, 30, 200, 25);
        estilizarCampoTexto(txtMasa);

        // Etiqueta y campo de texto para velocidad
        JLabel lblVelocidad = new JLabel("Velocidad (m/s):");
        lblVelocidad.setBounds(30, 70, 120, 20);
        lblVelocidad.setForeground(Color.WHITE);
        txtVelocidad = new JTextField();
        txtVelocidad.setBounds(130, 70, 200, 25);
        estilizarCampoTexto(txtVelocidad);

        // Botón para calcular la longitud de onda
        btnCalcular = crearBotonConHover("Calcular λ", 150, 120, 100, 30);
        btnCalcular.addActionListener(this);

        // Etiqueta donde se mostrara el resultado
        lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(30, 160, 340, 30);
        lblResultado.setForeground(Color.WHITE);
        lblResultado.setBackground(new Color(40, 40, 40));
        lblResultado.setOpaque(true);
        lblResultado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblResultado.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        // Botón volver
        btnVolver = crearBotonConHover("Volver", 150, 200, 100, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Agregar componentes
        add(lblMasa);
        add(txtMasa);
        add(lblVelocidad);
        add(txtVelocidad);
        add(btnCalcular);
        add(lblResultado);
        add(btnVolver);

        // Si el usuario cierra la ventana con la "X"
        addWindowListener(new WindowAdapter() {
            
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // Método para aplicar estilo a campos de texto
    private void estilizarCampoTexto(JTextField campo) {
        campo.setBackground(new Color(50, 50, 50));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    // Método para crear botones con estilo y efecto hover
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

    // 🧱 Bloque 3: MÃ©todo para volver a la ventana principal
    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }

    // 🧱 Bloque 4: Método para calcular la longitud de onda
    public void actionPerformed(ActionEvent e) {
        try {
            double masa = Double.parseDouble(txtMasa.getText());
            double velocidad = Double.parseDouble(txtVelocidad.getText());
            double h = 6.626e-34; // contante de Planck
            double lambda = h / (masa * velocidad); // Fórmula λ = h / (m·v)
            lblResultado.setText(String.format("λ = %.3e m (%.3f nm)", lambda, lambda * 1e9));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
