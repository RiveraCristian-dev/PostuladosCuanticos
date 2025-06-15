// 🧱 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;

import javax.swing.*; //Componentes de interfaz gráfica
import java.awt.*; //Manejo de colores, fuentes etc
import java.awt.event.*; //Manejo de eventos

//Ventana para calcular la radiancia espectral según la Ley de Planck
public class VentanaPlanck extends JFrame implements ActionListener {
    // 🧱 Bloque 2: Declaración de componentes
    private JTextField txtFrecuencia, txtTemperatura; // Campo para la frecuencia (THz) y temperatura (K)
    private JLabel lblResultado; // Etiqueta para mostrar resultados
    private JButton btnVolver; // Botón para regresar al menú
    // 🧱 Bloque 3: Constructor de la ventana

    public VentanaPlanck() {
        setTitle("Ley de Planck - Cuerpo Negro"); // Titulo
        setSize(400, 350); // Dimensiones(ancho, alto)
        setLayout(null); // Layout absoluto posicionamiento
        setLocationRelativeTo(null); // Centrar ventana en pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al salir
        EstilosSwing.aplicarFondoVentana(this); // Estilo de EstilosSwing, Fondo obscuro

        // 🧱 Bloque 4: Configuración de campos de entrada
        // Campo para frecuencia (en THz)
        JLabel lblFrecuencia = new JLabel("Frecuencia (THz):");
        lblFrecuencia.setBounds(30, 30, 120, 20);
        EstilosSwing.estilizarLabel(lblFrecuencia, false);

        txtFrecuencia = new JTextField();
        txtFrecuencia.setBounds(160, 30, 200, 25);
        EstilosSwing.estilizarCampoTexto(txtFrecuencia); // Estilo de EstilosSwing, estilo campo de texto

        JLabel lblTemperatura = new JLabel("Temperatura (K):");
        lblTemperatura.setBounds(30, 70, 120, 20);
        EstilosSwing.estilizarLabel(lblTemperatura, false);

        txtTemperatura = new JTextField();
        txtTemperatura.setBounds(160, 70, 200, 25);
        EstilosSwing.estilizarCampoTexto(txtTemperatura); // Estilo de EstilosSwing, estilo para campo de texto

        // 🧱 Bloque 5 : Configuracion de Botónes
        JButton btnCalcular = EstilosSwing.crearBotonConHover("Calcular Radiancia", 120, 120, 160, 30);
        btnCalcular.addActionListener(this);

        // 🧱 Bloque 6 :Configuracion del área de resultados
        lblResultado = new JLabel("Resultado:", SwingConstants.CENTER);
        lblResultado.setBounds(30, 160, 340, 60);
        EstilosSwing.estilizarLabelResultado(lblResultado); // Estilo de EstilosSwing, estilo de resultados

        // Botón para volver
        btnVolver = EstilosSwing.crearBotonConHover("Volver", 150, 240, 100, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Agregar componentes a la ventana
        add(lblFrecuencia);
        add(txtFrecuencia);
        add(lblTemperatura);
        add(txtTemperatura);
        add(btnCalcular);
        add(lblResultado);
        add(btnVolver);

        // 🧱 Bloque 7: Si el usuario cierra la ventana con la "X" el programa vuelve a
        // la ventana principal
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }

    // 🧱 Bloque 8: Cálculo de la radiancia espectral
  public void actionPerformed(ActionEvent e) {
        try {
            double frecuenciaTHz = Double.parseDouble(txtFrecuencia.getText());
            double frecuenciaHz = frecuenciaTHz * 1e12; // THz → Hz
            double temperatura = Double.parseDouble(txtTemperatura.getText());

            // Cálculo de B_ν (W/m²·Hz·sr)
            double B_nu = calcularLeyPlanck(frecuenciaHz, temperatura);

            // Conversión a B_λ (kJ/m²·µm·sr)
            double lambda = (3e8 / frecuenciaHz) * 1e6; // Longitud de onda en µm
            double B_lambda = B_nu * (3e8 / (lambda * 1e-6 * lambda * 1e-6)) * 1e-3;

            // Mostrar resultados
            lblResultado.setText(String.format(
                "<html>Radiancia espectral:<br>" +
                "<b>%.3e W/m²·Hz·sr</b><br>" +
                "≈ <b>%.3f kJ/m²·µm·sr</b> (λ = %.2f µm)</html>",
                B_nu, B_lambda, lambda));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "¡Error! Ingrese valores numéricos válidos.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🧱 Bloque 9: Cálculo de la ley de Planck
    private double calcularLeyPlanck(double frecuencia, double T) {
        double h = 6.626e-34, c = 3e8, k = 1.381e-23;
        return (2 * h * Math.pow(frecuencia, 3)) /
                (Math.pow(c, 2) * (Math.exp((h * frecuencia) / (k * T)) - 1));
    }
}
