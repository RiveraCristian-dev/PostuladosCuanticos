package com.mycompany.postuladoscuanticos;

//importacion de librerias
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 🧱 Bloque 1: Definición de la clase y atributos
public class VentanaPlanck extends JFrame implements ActionListener {
    private JTextField txtFrecuencia, txtTemperatura;
    private JLabel lblResultado;
    private JButton btnVolver;

    // 🧱 Bloque 2:Constructor de la interfaz grÃ¡fica
    public VentanaPlanck() {
        setTitle("Ley de Planck - Cuerpo Negro");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30)); // Fondo oscuro

        // Etiquetas y campos de texto
        JLabel lblFrecuencia = new JLabel("Frecuencia (THz):");
        lblFrecuencia.setBounds(30, 30, 120, 20);
        lblFrecuencia.setForeground(Color.WHITE);

        txtFrecuencia = new JTextField();
        txtFrecuencia.setBounds(160, 30, 200, 25);
        estilizarCampoTexto(txtFrecuencia); // Aplica estilo oscuro

        JLabel lblTemperatura = new JLabel("Temperatura (K):");
        lblTemperatura.setBounds(30, 70, 120, 20);
        lblTemperatura.setForeground(Color.WHITE);

        txtTemperatura = new JTextField();
        txtTemperatura.setBounds(160, 70, 200, 25);
        estilizarCampoTexto(txtTemperatura); // Aplica estilo oscuro

        // Botón para calcular
        JButton btnCalcular = crearBotonConHover("Calcular Radiancia", 120, 120, 160, 30);
        btnCalcular.addActionListener(this);

        // Etiqueta para mostrar resultado
        lblResultado = new JLabel("Resultado:", SwingConstants.CENTER);
        lblResultado.setBounds(30, 160, 340, 60);
        estilizarLabelResultado(lblResultado); // Aplica estilo

        // Botón para volver al menún pricipal
        btnVolver = crearBotonConHover("Volver", 150, 240, 100, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Agregar todos los componentes
        add(lblFrecuencia);
        add(txtFrecuencia);
        add(lblTemperatura);
        add(txtTemperatura);
        add(btnCalcular);
        add(lblResultado);
        add(btnVolver);

        // Acción al cerrar ventana con "X"
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // Métodos que estiliza campos de texto
    private void estilizarCampoTexto(JTextField campo) {
        campo.setBackground(new Color(50, 50, 50));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    private void estilizarLabelResultado(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(40, 40, 40));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

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

    // 🧱 Bloque 3: Método para volver al menú principal
    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }

    // 🧱 Bloque 4: Manejo del evento del botÃ³n Calcular
    public void actionPerformed(ActionEvent e) {
        try {
            double frecuencia = Double.parseDouble(txtFrecuencia.getText()) * 1e12;
            double temperatura = Double.parseDouble(txtTemperatura.getText());
            
            double B = calcularLeyPlanck(frecuencia, temperatura);

            // Mostrar en formato científico y también en kJ/m²·µm·sr
            lblResultado.setText(String.format(
                    "<html>Radiancia espectral: <b>%.3e W/m²·Hz·sr</b><br>≈ %.3f kJ/m²·µm·sr</html>",
                    B, B * 1e-9));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🧱 Bloque 5: Método para calcular la Ley de Planck
    private double calcularLeyPlanck(double frecuencia, double T) {
        double h = 6.626e-34, c = 3e8, k = 1.381e-23;
        return (2 * h * Math.pow(frecuencia, 3)) /
                (Math.pow(c, 2) * (Math.exp((h * frecuencia) / (k * T)) - 1));
    }
}