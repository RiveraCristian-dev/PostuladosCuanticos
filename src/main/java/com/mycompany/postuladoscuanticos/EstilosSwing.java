// 🎨 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;

import javax.swing.*; // Componentes de interfaz gráfica
import java.awt.*; // Manejo de colores, fuentes, cursores
import java.awt.event.*; // Manejo de eventos de ratón

//Clase utilitaria para aplicar estilos consistentes a componentes Swing
//Proporciona métodos estáticos para estilizar ventanas, campos de texto,botones y otros componentes con un tema oscuro moderno.

public class EstilosSwing {

    // 🎨 Bloque 2: Estilos para ventanas (JFrame)
    // Aplica fondo oscuro a una ventana JFrame

    public static void aplicarFondoVentana(JFrame frame) {
        frame.getContentPane().setBackground(new Color(30, 30, 30));
    }

    // 🎨 Bloque 3: Estilos para campos de texto (JTextField)
    // Estiliza campos de texto con fondo gris oscuro, texto blanco y bordes

    public static void estilizarCampoTexto(JTextField campo) {
        campo.setBackground(new Color(50, 50, 50)); // Fondo gris oscuro
        campo.setForeground(Color.WHITE); // Texto blanco
        campo.setCaretColor(Color.WHITE); // Cursor de texto blanco
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100)), // Borde exterior
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding interno
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13)); // Fuente
    }

    // 🎨 Bloque 4: Estilos para spinners (JSpinner)
    // Estiliza spinners para mantener coherencia con los campos de texto
    public static void estilizarSpinner(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
            estilizarCampoTexto(tf);
        }
    }

    // 🎨 Bloque 5: Estilos para áreas de texto (JTextArea)
    // Estiliza áreas de texto no editables para mostrar resultados
    public static void estilizarAreaTexto(JTextArea area) {
        area.setEditable(false);
        area.setForeground(Color.WHITE);
        area.setBackground(new Color(40, 40, 40));
        area.setFont(new Font("Consolas", Font.PLAIN, 12));
        area.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
    }

    // 🎨 Bloque 6: Creación de botones con efecto hover (el boton se ilumina)
    public static JButton crearBotonConHover(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto); // Posición y tamaño
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13)); // Fuente negrita
        boton.setBackground(new Color(60, 60, 60)); // Color base
        boton.setForeground(Color.WHITE); // Texto blanco
        boton.setFocusPainted(false); // Elimina borde de enfoque
        boton.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150))); // Borde claro

        // Efecto hover - Cambia color y cursor al pasar el ratón
        boton.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(90, 90, 90)); // Color más claro
                boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor de mano
            }

            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(60, 60, 60)); // Vuelve al color original
            }
        });
        return boton;
    }

    // 🎨 Bloque 7: Estilos para etiquetas (JLabel)
    // Estiliza etiquetas con opción de fondo

    public static void estilizarLabel(JLabel label, boolean conFondo) {
        label.setForeground(Color.WHITE); // Texto blanco
        if (conFondo) {
            label.setBackground(new Color(40, 40, 40)); // Fondo oscuro
            label.setOpaque(true); // Hace visible el fondo
            label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100))); // Borde
        }
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13)); // Fuente estándar
    }

    // Estelización para etiquetas de resultado (siempre con fondo)

    public static void estilizarLabelResultado(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(40, 40, 40));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

}