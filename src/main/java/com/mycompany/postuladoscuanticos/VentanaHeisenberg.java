// 🧱 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;

// Importación de librerías
import javax.swing.*;            //Componentes de interfaz gráfica
import java.awt.*;               //Clases para manejo de colores y fuentes
import java.awt.event.*;         //Manejo de eventos (clicks)

//Ventana para calcular las incertidumbres según el principio de Heisenberg
// Implementa: Δx·Δp ≥ ħ/2 (donde ħ = h/2π)
public class VentanaHeisenberg extends JFrame implements ActionListener {
    
    // 🧱 Bloque 2: Declaración de componentes
    private JTextField txtDeltaX, txtMasa; //Campo para incertidumbre en posición (nm) y masa de la partícula(kg)
    private JLabel lblDeltaP, lblDeltaV; //Etiqueta para mostrar momento y velocidad
    private JButton btnVolver, btnCalcular; //Botón para regresar al menu y realizar el cálculo

    // 🧱 Bloque 3: Constructor de la ventana
    public VentanaHeisenberg() {
        //Configuración de la ventana
        setTitle("Principio de Incertidumbre de Heisenberg");
        setSize(450, 300);                                     //Dimenciones (ancho, alto)
        setLayout(null);                                       //Layout absoluto posicionamiento
        setLocationRelativeTo(null);                           //Centrar ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     //Cierra la ventana
        EstilosSwing.aplicarFondoVentana(this);

        // 🧱 Bloque 4: Configuración de campos
        //campo de texto para Δx (posición)
        JLabel lblDeltaX = new JLabel("Δx (posición, nm):");
        lblDeltaX.setBounds(30, 30, 180, 20);
        lblDeltaX.setForeground(Color.WHITE);

        txtDeltaX = new JTextField("0.1"); //Valor por defecto, sepuede cambiar
        txtDeltaX.setBounds(220, 30, 180, 25);
        EstilosSwing.estilizarCampoTexto(txtDeltaX); //Estilo constante de EstilosSwing

        // Etiqueta y campo de texto para masa
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 70, 180, 20);
        lblMasa.setForeground(Color.WHITE);

        txtMasa = new JTextField("9.109e-31");//Valor por defecto de la masa, se puede cambiar
        txtMasa.setBounds(220, 70, 180, 25);
        EstilosSwing.estilizarCampoTexto(txtMasa);
        // 🧱 Bloque 5: Configuración de Botones

        btnCalcular = EstilosSwing.crearBotonConHover("Calcular Incertidumbre", 120, 110, 200, 30);
        btnCalcular.addActionListener(this);

        // 🧱 Bloque 6: Configuración de etiquetas de resultados
        lblDeltaP = new JLabel("Δp = ?"); //Incretidumbre en el momento
        lblDeltaP.setBounds(30, 160, 380, 20);
        EstilosSwing.estilizarLabelResultado(lblDeltaP);

        lblDeltaV = new JLabel("Δv = ?"); //Incertidumbre en la velocidad
        lblDeltaV.setBounds(30, 190, 380, 20);
        EstilosSwing.estilizarLabelResultado(lblDeltaV);

        btnVolver = EstilosSwing.crearBotonConHover("Volver", 150, 230, 150, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // 🧱 Bloque 7: Agregar componentes a la ventana 
        add(lblDeltaX);
        add(txtDeltaX);
        add(lblMasa);
        add(txtMasa);
        add(btnCalcular);
        add(lblDeltaP);
        add(lblDeltaV);
        add(btnVolver);

        // 🧱 Bloque 8: Si el usuario cierra la ventana con"X"
        addWindowListener(new WindowAdapter() {
            
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }
    
    //🧱 Bloque 9: Método para cálculo de incertidumbre
    public void actionPerformed(ActionEvent e) {
        try {
            double deltaX = Double.parseDouble(txtDeltaX.getText()) * 1e-9;
            double masa = Double.parseDouble(txtMasa.getText());

            double hbar = 1.0545718e-34; // Constante de Planck reducida
            double deltaP = hbar / (2 * deltaX);
            double deltaV = deltaP / masa;
            
            //Muestra el resultado en texto científico
            lblDeltaP.setText(String.format("Δp ≥ %.3e kg·m/s", deltaP));
            lblDeltaV.setText(String.format("Δv ≥ %.3e m/s ", deltaV));
        } catch (NumberFormatException ex) {
            //Mensake de error de entrada no numérica
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🧱 Bloque 10: Método para volver al menú
    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }
}
