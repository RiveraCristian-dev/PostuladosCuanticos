// 🧱 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;


import javax.swing.*;      //Componentes de interfaz gráfica
import java.awt.*;         //Clases para manejo de colores y fuentes
import java.awt.event.*;   //Manejo de eventos (clicks)

//Clase para calcular la longitud de onda de Broglie
//Hereda de JFrame para la ventan principal e implementa ActionListener para manejar eventos
public class VentanaDeBroglie extends JFrame implements ActionListener {
    
// 🧱 Bloque 2: Declaración de componentes
    private JTextField txtMasa, txtVelocidad;    //Campo para ingresar masa y velocidad
    private JLabel lblResultado;                 //Etiqueta para mostrar resultados
    private JButton btnVolver, btnCalcular;      //Botón para volver al menú principal y realizar el Cálculo

    // 🧱 Bloque 3 : Constructor de la ventana
    public VentanaDeBroglie() {
        setTitle("Dualidad Onda-Partícula");     //Título de la ventana
        setSize(400, 300);                       //Dimensiones(ancho, alto)
        setLayout(null);                         //Layout absoluto posicionamiento
        setLocationRelativeTo(null);             //Centrar ventana en pantalla 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //Cierra solo esta ventana al salir
        EstilosSwing.aplicarFondoVentana(this);              //Estilo constante de EstilosSwing

        // 🧱 Bloque 4 : Configuración de componentes para masa
        JLabel lblMasa = new JLabel("Masa (kg):");
        lblMasa.setBounds(30, 30, 100, 20);           //Posición y tamaño
        EstilosSwing.estilizarLabel(lblMasa, false);

        txtMasa = new JTextField();
        txtMasa.setBounds(130, 30, 200, 25);          //Posición junto a la etiqueta
        EstilosSwing.estilizarCampoTexto(txtMasa);    //Aplicacion del estilo consistente de la clase EstilosSwing

        // 🧱 Bloque 5 : Configuracion de componentes para velocidad
        JLabel lblVelocidad = new JLabel("Velocidad (m/s):");
        lblVelocidad.setBounds(30, 70, 120, 20);      //Posición debajo de masa
         EstilosSwing.estilizarLabel(lblVelocidad, false);

        txtVelocidad = new JTextField();
        txtVelocidad.setBounds(130, 70, 200, 25);
        EstilosSwing.estilizarCampoTexto(txtVelocidad);
        // 🧱 Bloque 6 : Configuracion de botones

        btnCalcular = EstilosSwing.crearBotonConHover("Calcular λ", 150, 120, 100, 30);
        btnCalcular.addActionListener(this);                //Objeto que maneja el evento

        // 🧱 Bloque 7 :Configuracion del área de resultados
        lblResultado = new JLabel("Resultado:"); 
        lblResultado.setBounds(30, 160, 340, 30);           //Posición debajo de los botones
        EstilosSwing.estilizarLabelResultado(lblResultado); //EstilosSwing

        btnVolver = EstilosSwing.crearBotonConHover("Volver", 150, 200, 100, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());

        // Agregar componentes
        add(lblMasa);
        add(txtMasa);
        add(lblVelocidad);
        add(txtVelocidad);
        add(btnCalcular);
        add(lblResultado);
        add(btnVolver);

        // 🧱 Bloque 8: Si el usuario cierra la ventana con la "X" el programa vuelve a la ventana principal
        addWindowListener(new WindowAdapter() {
            
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }
    // 🧱 Bloque 9 : Método para volver a la ventana principal

    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true); //Abre la ventana principal
    }

    // 🧱 Bloque 10: Cálculo de la longitud de onda
    public void actionPerformed(ActionEvent e) {
        try {
            //Obtención  y conversión de valores ingresados
            double masa = Double.parseDouble(txtMasa.getText());
            double velocidad = Double.parseDouble(txtVelocidad.getText());
            double h = 6.626e-34;                   //Constante de Planck
            double lambda = h / (masa * velocidad); //Formula del cálculo
            lblResultado.setText(String.format("λ = %.3e m (%.3f nm)", lambda, lambda * 1e9)); //Muestra el resultado en metros y nanómetros
        } catch (NumberFormatException ex) {        //Mensaje de error de entrada no númerica
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
