// 🧱 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;

import javax.swing.*;     //Componentes de interfaz gráfica
import java.awt.*;        //Clases para manejo de colores y fuentes
import java.awt.event.*;  //Manejo de eventos (clicks)
//Clase principal que representa la ventana del simulador 
//Hereda de JFrame para la ventan principal e implementa ActionListener para manejar eventos

public class PostuladosCuanticos extends JFrame implements ActionListener {

    // 🧱 Bloque 2: Componentes de interfaz
    private JButton btnDeBroglie, btnPlanck, btnSchrodinger, btnPauli, btnHeisenberg, btnSalir;
    private JLabel lblResultado;                 //Área para mostrar resultados/teoría
    private JRadioButton rbTeoria, rbSimulacion; //Opción para mostrar teoría o simulación
    
    // 🧱 Bloque 3: Constructor de la ventana
    public PostuladosCuanticos() {
        // Configuración de la ventana principal
        setTitle("Simulador de Postulados Cuánticos");
        setSize(700, 600);                                     //Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        //Cerrar el programa al salir
        setLayout(null);                                       //Layout absoluto posicionamiento
        setLocationRelativeTo(null);                           //Centrar ventana en pantalla
         EstilosSwing.aplicarFondoVentana(this);               //Estilo constante de EstilosSwing

        // 🧱 Bloque 4: Configuración del Título principal
        JLabel lblTitulo = new JLabel("POSTULADOS DE LA MECÁNICA CUÁNTICA", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 600, 30);                    //Posición y tamaño
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));  //Fuente
        lblTitulo.setForeground(Color.WHITE);                    //Color del texto
        add(lblTitulo);                                          //Añadir a la ventana

        // 🧱 Bloque 5: Creación de botones para cada postulado
        //Usa el método auxiliar crearBoton para mantener consistencia
        btnDeBroglie = crearBoton("De Broglie", 100, 80, 150, 40);
        btnPlanck = crearBoton("Ley de Planck", 275, 80, 150, 40);
        btnSchrodinger = crearBoton("Schrödinger", 450, 80, 150, 40);
        btnPauli = crearBoton("Exclusión Pauli", 100, 140, 150, 40);
        btnHeisenberg = crearBoton("Heisenberg", 275, 140, 150, 40);
        btnSalir = crearBoton("Salir", 450, 140, 150, 40);

        // 🧱 Bloque 6: Configuración de radio buttons para la seleccion de modo 
        rbTeoria = new JRadioButton("Teoría", true); //Seleccionado por defecto
        rbTeoria.setBounds(50, 200, 80, 30);
        rbTeoria.setForeground(Color.WHITE);
        rbTeoria.setBackground(new Color(30, 30, 30));
        rbTeoria.setFont(new Font("Seoge UI", Font.PLAIN, 13));

        rbSimulacion = new JRadioButton("Simulación");
        rbSimulacion.setBounds(140, 200, 100, 30);
        rbSimulacion.setForeground(Color.WHITE);
        rbSimulacion.setBackground(new Color(30, 30, 30));
        rbSimulacion.setFont(new Font("Seoge UI", Font.PLAIN, 14));
        
        //Agrupar los radio buttons para seleccion mutuamente exclusiva
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbTeoria);
        grupo.add(rbSimulacion);
        add(rbTeoria);
        add(rbSimulacion);

        // 🧱 Bloque 7: Configuración del área de resultados
        lblResultado = new JLabel("Seleccione un postulado y un modo", SwingConstants.CENTER);
        lblResultado.setBounds(50, 240, 600, 250);                                 //Posición y tamaño
        lblResultado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));  //Borde
        lblResultado.setForeground(Color.WHITE);
        lblResultado.setFont(new Font("Seoge UI", Font.PLAIN, 13));
        add(lblResultado);
    }

    // 🧱 Bloque 8: Método auxiliar para crear botones
    //Crea un botón con estilo consistente y efectos de hover
    private JButton crearBoton(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setFont(new Font("Seoge UI", Font.PLAIN, 14));
        boton.setBackground(new Color(60, 63, 65));       //Color de fondo
        boton.setForeground(Color.WHITE);                 //Color de texto
        boton.setFocusPainted(false);                     //Quitar borde de enfoque 
        boton.setBorder(BorderFactory.createLineBorder(Color.GRAY)); //Borde
        boton.addActionListener(this);                    //Manejador de eventos
        
        //Efecto hover: cambia de color al pasar el mouse
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(85, 110, 122));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(60, 63, 65));
            }
        });
        add(boton);
        return boton;
    }
    
    // 🧱 Bloque 9: Manejo de eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            System.exit(0);                   //Salir del programa
        } else if (rbTeoria.isSelected()) {
            mostrarTeoria(e.getSource());     //Mostrar Teoría del Postulado
        } else {
            iniciarSimulacion(e.getSource()); //Iniciar Simulación del postulado
        }
    }

    // 🧱 Bloque 10: Método que muestra la Teoria de cada postulado
    private void mostrarTeoria(Object boton) {
        String teoria = "";
        if (boton == btnDeBroglie) {
            teoria = "<html><div style='padding:10px;'>"
                    + "<b style='color:#CCCCCC;'>Dualidad Onda-Partícula (De Broglie, 1924):</b><br><br>"
                    + "<center>λ = h/p</center><br>"
                    + "<b>Donde:</b><br>"
                    + "- λ: Longitud de onda asociada (m)<br>"
                    + "- p: Momento lineal (p = m·v) (kg·m/s)<br>"
                    + "- h: Constante de Planck (6.626×10⁻³⁴ J·s)<br><br>"
                    + "<b>Implicación:</b> Toda partícula material tiene propiedades ondulatorias.</div></html>";
        } else if (boton == btnPlanck) {
            teoria = "<html><div style='padding:10px;'>"
                    + "<b style='color:#CCCCCC;'>Ley de Planck (Cuerpo Negro, 1900):</b><br><br>"
                    + "<center>B(ν,T) = (2hν³/c²)·1/(e^(hν/kT) - 1)</center><br>"
                    + "<b>Donde:</b><br>"
                    + "- B: Radiancia espectral (W/m²·Hz·sr)<br>"
                    + "- ν: Frecuencia de la radiación (Hz)<br>"
                    + "- T: Temperatura absoluta (K)<br>"
                    + "- k: Constante de Boltzmann (1.381×10⁻²³ J/K)<br><br>"
                    + "<b>Importancia:</b> Explica el espectro de emisión de cuerpos calientes.</div></html>";
        } else if (boton == btnSchrodinger) {
            teoria = "<html><div style='padding:10px;'>"
                    + "<b style='color:#CCCCCC;'>Ecuación de Schrödinger (1926):</b><br><br>"
                    + "<center>iℏ·∂Ψ/∂t = ĤΨ</center><br>"
                    + "<b>Forma independiente del tiempo:</b><br>"
                    + "<center>Ĥψ = Eψ</center><br>"
                    + "<b>Donde:</b><br>"
                    + "- Ψ: Función de onda (dependiente del tiempo)<br>"
                    + "- ψ: Función de onda estacionaria<br>"
                    + "- Ĥ: Operador Hamiltoniano (-ℏ²/2m·∇² + V)<br>"
                    + "- E: Energía del estado cuántico (J)<br><br>"
                    + "<b>Interpretación:</b> Describe la evolución de sistemas cuánticos.</div></html>";
        } else if (boton == btnPauli) {
            teoria = "<html><div style='padding:10px;'>"
                    + "<b style='color:#CCCCCC;'>Principio de Exclusión de Pauli (1925):</b><br><br>"
                    + "<center>Ψ(1,2) = -Ψ(2,1)</center><br>"
                    + "<b>Enunciado:</b> Dos fermiones idénticos no pueden ocupar el mismo estado cuántico simultáneamente.<br><br>"
                    + "<b>Consecuencias:</b><br>"
                    + "- Estructura de capas electrónicas en átomos<br>"
                    + "- Estabilidad de la materia<br>"
                    + "- Clasificación de partículas (fermiones vs bosones)</div></html>";
        } else if (boton == btnHeisenberg) {
            teoria = "<html><div style='padding:10px;'>"
                    + "<b style='color:#CCCCCC;'>Principio de Incertidumbre (Heisenberg, 1927):</b><br><br>"
                    + "<center>Δx·Δp ≥ ℏ/2</center><br>"
                    + "<center>ΔE·Δt ≥ ℏ/2</center><br>"
                    + "<b>Donde:</b><br>"
                    + "- Δx: Incertidumbre en posición (m)<br>"
                    + "- Δp: Incertidumbre en momento lineal (kg·m/s)<br>"
                    + "- ΔE: Incertidumbre en energía (J)<br>"
                    + "- Δt: Incertidumbre en tiempo (s)<br><br>"
                    + "<b>Implicación:</b> Límite fundamental en la medición simultánea de variables conjugadas.</div></html>";
        }

        lblResultado.setText(teoria);
        lblResultado.setFont(new Font("Seoge UI", Font.PLAIN, 12));
    }
    
    // 🧱 Bloque 11: Método para iniciar simulaciones(manda a llamar a los demas códigos)
    private void iniciarSimulacion(Object boton) {
        
        if (boton == btnDeBroglie) {
            new VentanaDeBroglie().setVisible(true);
        } else if (boton == btnPlanck) {
            new VentanaPlanck().setVisible(true);
        } else if (boton == btnSchrodinger) {
            new VentanaSchrodinger().setVisible(true);
        } else if (boton == btnPauli) {
            new VentanaPauli().setVisible(true);
        } else if (boton == btnHeisenberg) {
            new VentanaHeisenberg().setVisible(true);
        }
        this.dispose(); //Cierra la ventana principal
    }
    // 🧱 Bloque 12: Método principal que ejecuta VentanaLogin para iniciar el programa 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLogin().setVisible(true));
    }
}