// 🔐 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;

import javax.swing.*; // Componentes de interfaz gráfica
import java.awt.*; // Manejo de colores, fuentes, geometría
import java.awt.event.*; // Manejo de eventos

//Ventana de login para autenticar usuarios antes de acceder al simulador cuántico
//Implementa verificación básica de credenciales con usuario y contraseña

public class VentanaLogin extends JFrame {

    // 🔐 Bloque 2: Declaración de componentes y credenciales
    private JTextField txtUsuario; // Campo para nombre de usuario
    private JPasswordField txtContrasena; // Campo para contraseña (protegida)
    private final String USUARIO_CORRECTO = "admin"; // Usuario
    private final String CONTRASENA_CORRECTA = "2000"; // Contraseña

    // 🔐 Bloque 3: Constructor de la ventana
    public VentanaLogin() {
        setTitle("Iniciar Sesión");
        setSize(350, 250); // Tamaño
        setLayout(null); // Posicionamiento absoluto
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cerrar aplicación al salir
        EstilosSwing.aplicarFondoVentana(this); // Aplicar estilo oscuro

        // 🔐 Bloque 4: Configuración del campo de usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(40, 40, 100, 25);
        EstilosSwing.estilizarLabel(lblUsuario, false);
        add(lblUsuario);

        // Campo Usuario
        txtUsuario = new JTextField();
        txtUsuario.setBounds(130, 40, 150, 25);
        EstilosSwing.estilizarCampoTexto(txtUsuario);
        add(txtUsuario);

        // 🔐 Bloque 5: Configuración del campo de contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(40, 80, 100, 25);
        EstilosSwing.estilizarLabel(lblContrasena, false);
        add(lblContrasena);

        // Campo Contraseña
        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(130, 80, 150, 25);
        EstilosSwing.estilizarCampoTexto(txtContrasena);
        add(txtContrasena);

        // 🔐 Bloque 6: Configuración del botón de login
        JButton btnIngresar = EstilosSwing.crearBotonConHover("Iniciar sesión", 100, 130, 140, 30);
        btnIngresar.addActionListener(e -> verificarCredenciales());
        add(btnIngresar);
    }

    // 🔐 Bloque 7: Método para verificar credenciales
    // Compara las credenciales ingresadas con las almacenadas
    // Si son correctas, abre la ventana principal
    // Si son incorrectas, muestra mensaje de error

    private void verificarCredenciales() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword()); // Convertir char[] a String

        if (usuario.equals(USUARIO_CORRECTO) && contrasena.equals(CONTRASENA_CORRECTA)) {
            // Éxito: abrir ventana principal (Credenciales correctas)
            new PostuladosCuanticos().setVisible(true);
            this.dispose(); // cerrar login
        } else {
            // Error (Credenciales incorrectas)
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos",
                    "Error de acceso", JOptionPane.ERROR_MESSAGE);
        }
    }
}
