package com.mycompany.postuladoscuanticos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PostuladosCuanticos extends JFrame implements ActionListener {
    
    // Componentes
    private JButton btnDeBroglie, btnPlanck, btnSchrodinger, btnPauli, btnHeisenberg, btnSalir;
    private JLabel lblResultado;
    private JRadioButton rbTeoria, rbSimulacion;

    public PostuladosCuanticos() {
        // Configuración de la ventana principal
        setTitle("Simulador de Postulados Cuánticos");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30,30,30));

        // Título
        JLabel lblTitulo = new JLabel("POSTULADOS DE LA MECÁNICA CUÁNTICA", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 600, 30);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        add(lblTitulo);

        // Botones para cada postulado
        btnDeBroglie = crearBoton("De Broglie", 100, 80, 150, 40);
        btnPlanck = crearBoton("Ley de Planck", 275, 80, 150, 40);
        btnSchrodinger = crearBoton("Schrödinger", 450, 80, 150, 40);
        btnPauli = crearBoton("Exclusión Pauli", 100, 140, 150, 40);
        btnHeisenberg = crearBoton("Heisenberg", 275, 140, 150, 40);
        btnSalir = crearBoton("Salir", 450, 140, 150, 40);

        // Radio buttons para selección de modo
        rbTeoria = new JRadioButton("Teoría", true);
        rbTeoria.setBounds(50, 200, 80, 30);
        rbTeoria.setForeground(Color.WHITE);
        rbTeoria.setBackground(new Color(30,30,30));
        rbTeoria.setFont(new Font("Seoge UI", Font.PLAIN, 13));
        
        rbSimulacion = new JRadioButton("Simulación");
        rbSimulacion.setBounds(140, 200, 100, 30);
        rbSimulacion.setForeground(Color.WHITE);
        rbSimulacion.setBackground(new Color(30,30,30));
        rbSimulacion.setFont(new Font("Seoge UI", Font.PLAIN, 14));
        
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbTeoria);
        grupo.add(rbSimulacion);
        add(rbTeoria);
        add(rbSimulacion);

        // Área de resultados
        lblResultado = new JLabel("Seleccione un postulado y un modo", SwingConstants.CENTER);
        lblResultado.setBounds(50, 240, 600, 250);
        lblResultado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblResultado.setForeground(Color.WHITE);
        lblResultado.setFont(new Font("Seoge UI", Font.PLAIN, 13));
        add(lblResultado);
    }

    // Método auxiliar para crear botones
    private JButton crearBoton(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setFont(new Font("Seoge UI", Font.PLAIN, 14));
        boton.setBackground(new Color(60, 63, 65));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        boton.addActionListener(this);
        
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            System.exit(0);
        } else if (rbTeoria.isSelected()) {
            mostrarTeoria(e.getSource());
        } else {
            iniciarSimulacion(e.getSource());
        }
    }
    //Teoria de cada postulado
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
    } 
    else if (boton == btnPlanck) {
        teoria = "<html><div style='padding:10px;'>"
                + "<b style='color:#CCCCCC;'>Ley de Planck (Cuerpo Negro, 1900):</b><br><br>"
                + "<center>B(ν,T) = (2hν³/c²)·1/(e^(hν/kT) - 1)</center><br>"
                + "<b>Donde:</b><br>"
                + "- B: Radiancia espectral (W/m²·Hz·sr)<br>"
                + "- ν: Frecuencia de la radiación (Hz)<br>"
                + "- T: Temperatura absoluta (K)<br>"
                + "- k: Constante de Boltzmann (1.381×10⁻²³ J/K)<br><br>"
                + "<b>Importancia:</b> Explica el espectro de emisión de cuerpos calientes.</div></html>";
    } 
    else if (boton == btnSchrodinger) {
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
    } 
    else if (boton == btnPauli) {
        teoria = "<html><div style='padding:10px;'>"
                + "<b style='color:#CCCCCC;'>Principio de Exclusión de Pauli (1925):</b><br><br>"
                + "<center>Ψ(1,2) = -Ψ(2,1)</center><br>"
                + "<b>Enunciado:</b> Dos fermiones idénticos no pueden ocupar el mismo estado cuántico simultáneamente.<br><br>"
                + "<b>Consecuencias:</b><br>"
                + "- Estructura de capas electrónicas en átomos<br>"
                + "- Estabilidad de la materia<br>"
                + "- Clasificación de partículas (fermiones vs bosones)</div></html>";
    } 
    else if (boton == btnHeisenberg) {
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

    private void iniciarSimulacion(Object boton) {
        this.dispose();
        if (boton == btnDeBroglie) {
            new VentanaDeBroglie().setVisible(true);
        } else if (boton == btnPlanck){
            new VentanaPlanck().setVisible(true);
        } else if (boton == btnSchrodinger) {
            new VentanaSchrodinger().setVisible(true);
        } else if (boton == btnPauli) {
            new VentanaPauli().setVisible(true);
        } else if (boton == btnHeisenberg) {
            new VentanaHeisenberg().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PostuladosCuanticos ventana = new PostuladosCuanticos();
            ventana.setVisible(true);
        });
    }
}