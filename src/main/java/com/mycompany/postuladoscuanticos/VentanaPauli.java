// 🧱 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;

import javax.swing.*; // Componentes de interfaz gráfica
import java.awt.*; // Manejo de colores, fuentes, geometría
import java.awt.event.*; // Manejo de eventos

//Ventana para demostrar el Principio de Exclusión de Pauli
//Calcula las combinaciones posibles de fermiones en estados cuánticos
//Implementa: C = estados! / (partículas! * (estados-partículas)
public class VentanaPauli extends JFrame {

    // 🧱 Bloque 2: Declaración de componentes
    private JSpinner spinnerParticulas, spinnerEstados; // Selector para número de fermiones y estados disponibles
    private JLabel lblResultado; // Etiqueta para mostrar resultados
    private JButton btnVolver, btnCalcular; // Botón para regresar al menú y realizar el cálculo

    // 🧱 Bloque 3: Constructor de la ventana
    public VentanaPauli() {
        setTitle("Principio de Exclusión de Pauli");
        setSize(500, 300); // Dimensiones (ancho, alto)
        setLayout(null); // Posicionamiento absoluto
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        EstilosSwing.aplicarFondoVentana(this); // Aplicar estilo de fondo

        // 🧱 Bloque 4: Configuración de selectores (JSpinner)
        // Selector para número de fermiones (1-10)
        JLabel lblParticulas = new JLabel("Número de fermiones:");
        lblParticulas.setBounds(50, 30, 150, 25);
        EstilosSwing.estilizarLabel(lblParticulas, false);
        add(lblParticulas);

        spinnerParticulas = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        spinnerParticulas.setBounds(220, 30, 60, 30);
        EstilosSwing.estilizarSpinner(spinnerParticulas);
        add(spinnerParticulas);

        // Selector para estados disponibles (1-20)
        JLabel lblEstados = new JLabel("Estados disponibles:");
        lblEstados.setBounds(50, 70, 150, 25);
        EstilosSwing.estilizarLabel(lblEstados, false);
        add(lblEstados);

        spinnerEstados = new JSpinner(new SpinnerNumberModel(4, 1, 20, 1));
        spinnerEstados.setBounds(220, 70, 60, 30);
        EstilosSwing.estilizarSpinner(spinnerEstados);
        add(spinnerEstados);

        // 🧱 Bloque 5: Configuración de botones
        btnCalcular = EstilosSwing.crearBotonConHover("Calcular Combinaciones", 300, 50, 160, 30);
        btnCalcular.addActionListener(e -> calcularCombinaciones());
        add(btnCalcular);

        // 🧱 Bloque 6: Configuración del área de resultados
        lblResultado = new JLabel("", SwingConstants.CENTER);
        lblResultado.setBounds(50, 120, 410, 40);
        EstilosSwing.estilizarLabelResultado(lblResultado);
        add(lblResultado);

        // Botón Volver al menú principal
        btnVolver = EstilosSwing.crearBotonConHover("Volver", 190, 210, 120, 30);
        btnVolver.addActionListener(e -> volverAPrincipal());
        add(btnVolver);

        // 🧱 Bloque 7: Si el usuario cierra la ventana con"X"
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                volverAPrincipal();
            }
        });
    }

    // 🧱 Bloque 8: Método para calcular combinaciones
    // Calcula las combinaciones posibles de fermiones en estados cuánticos
    // según el principio de exclusión de Pauli (no dos fermiones idénticos pueden
    // ocupar el mismo estado cuántico simultáneamente)
    private void calcularCombinaciones() {
        try {
            int particulas = (int) spinnerParticulas.getValue();
            int estados = (int) spinnerEstados.getValue();

            // Validación: no puede haber más partículas que estados
            if (particulas > estados) {
                lblResultado.setText("¡Error: Partículas > Estados!");
                return;
            }

            // Fórmula combinatoria: C = estados!/(partículas!(estados-partículas)!)
            int combinaciones = factorial(estados) / (factorial(particulas) * factorial(estados - particulas));
            lblResultado.setText("Configuraciones posibles: " + combinaciones);
        } catch (Exception ex) {
            lblResultado.setText("Error en cálculo");
        }
    }

    // 🧱 Bloque 9: Método para volver al menú principal
    private void volverAPrincipal() {
        this.dispose();
        new PostuladosCuanticos().setVisible(true);
    }

    // 🧱 Bloque 10: Método auxiliar para factorial
    private int factorial(int n) {
        return (n <= 1) ? 1 : n * factorial(n - 1);
    }
}
