package com.mycompany.postuladoscuanticos;

//importación de libreías
import javax.swing.*;
import java.awt.*;
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
        getContentPane().setBackground(new Color(30, 30, 30));

        // Etiqueta y spinner para número cuántico
        JLabel lblN = new JLabel("Número cuántico (n):");
        lblN.setBounds(30, 30, 150, 20);
        lblN.setForeground(Color.WHITE);

        spinnerN = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerN.setBounds(190, 30, 50, 25);
        estilizarSpinner(spinnerN);

        // Etiqueta y campo de texto para la longitud de la caja
        JLabel lblL = new JLabel("Longitud de la caja (Å):");
        lblL.setBounds(30, 70, 150, 20);
        lblL.setForeground(Color.WHITE);

        txtL = new JTextField("1.0");
        txtL.setBounds(190, 70, 100, 25);
        estilizarCampoTexto(txtL);

        // Etiqueta y campo para masa de la partícula
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 110, 150, 20);
        lblMasa.setForeground(Color.WHITE);

        txtMasa = new JTextField("9.109e-31"); // Masa por defecto
        txtMasa.setBounds(190, 110, 100, 25);
        estilizarCampoTexto(txtMasa);

        // Botón para realizar el calculó
        JButton btnCalcular = crearBotonConHover("Calcular Energía", 150, 150, 150, 30);

        // Área de texto para mostrar el resultado
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setForeground(Color.WHITE);
        txtResultado.setBackground(new Color(40, 40, 40));
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtResultado.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));

        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 190, 390, 100);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));

        // Botón para volver a la ventana principal
        btnVolver = crearBotonConHover("Volver", 150, 300, 150, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Acción del botón Calcular
        btnCalcular.addActionListener(e -> {
            try {
                int n = (int) spinnerN.getValue();
                double L = Double.parseDouble(txtL.getText()) * 1e-10;
                double m = Double.parseDouble(txtMasa.getText());

                double E = calcularEnergia(n, L, m);
                txtResultado.setText(String.format("Energía para n=%d:\n%.3e J\n≈ %.3f eV",
                        n, E, E / 1.602e-19));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar componentes
        add(lblN);
        add(spinnerN);
        add(lblL);
        add(txtL);
        add(lblMasa);
        add(txtMasa);
        add(btnCalcular);
        add(scroll);
        add(btnVolver);

        // Acción al cerrar la ventana con "X"
        addWindowListener(new WindowAdapter() {

            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // Estilizar campo de texto
    private void estilizarCampoTexto(JTextField campo) {
        campo.setBackground(new Color(50, 50, 50));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    private void estilizarSpinner(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
            tf.setBackground(new Color(50, 50, 50));
            tf.setForeground(Color.WHITE);
            tf.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        }
    }

    private JButton crearBotonConHover(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(new Color(60, 60, 60));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(90, 90, 90));
                boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
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

    // 🧱 Bloque 4: Método para calcular la energía según la ecuación de Schrödinger
    private double calcularEnergia(int n, double L, double m) {
        double hbar = 1.0545718e-34; // Constante de Planck reducida
        return (Math.pow(n * Math.PI * hbar, 2)) / (2 * m * Math.pow(L, 2));
    }
}

