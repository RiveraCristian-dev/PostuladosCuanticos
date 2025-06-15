// 🔒 Bloque 1: Declaración del paquete e imports
package com.mycompany.postuladoscuanticos;

import javax.swing.*; // Componentes de interfaz gráfica
import java.awt.event.*; // Manejo de eventos
import java.io.*; // Operaciones de entrada/salida
import org.json.*; // Manejo de datos JSON

// 🔒 Bloque 2: Clase principal de la ventana de login
// Implementa una ventana de autenticación con campos para usuario y contraseña
// Verifica las credenciales contra un archivo JSON de usuarios
public class VentanaLogin extends JFrame implements ActionListener {

    // Componentes de la interfaz
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    // 🔒 Bloque 3: Constructor - Configuración inicial de la ventana
    public VentanaLogin() {
        setTitle("Iniciar Sesión"); // Título de la ventana
        setSize(400, 250); // Dimensiones (ancho x alto)
        setLayout(null); // Layout absoluto (posicionamiento manual)
        setLocationRelativeTo(null); // Centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar aplicación al salir

        // 🔒 Bloque 4: Componentes para el campo de usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 30, 80, 25); // Posición y tamaño
        EstilosSwing.estilizarLabel(lblUsuario, false); // Aplica estilos
        add(lblUsuario); // Añade a la ventana

        txtUsuario = new JTextField();
        txtUsuario.setBounds(140, 30, 200, 25); // Posición y tamaño
        EstilosSwing.estilizarCampoTexto(txtUsuario); // Aplica estilos
        add(txtUsuario); // Añade a la ventana

        // Componentes para el campo de contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 70, 80, 25); // Posición y tamaño
        EstilosSwing.estilizarLabel(lblPassword, false); // Aplica estilos
        add(lblPassword);// Añade a la ventana

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 70, 200, 25);
        EstilosSwing.estilizarCampoTexto(txtPassword);
        add(txtPassword);

        // 🖱️ Bloque 5: Botón de ingreso con efecto hover
        btnIngresar = EstilosSwing.crearBotonConHover("Ingresar", 140, 120, 100, 30);
        btnIngresar.addActionListener(this); // Asigna listener de eventos
        add(btnIngresar);

        EstilosSwing.aplicarFondoVentana(this); // Aplica fondo oscuro
        setVisible(true); // Hace visible la ventana
    }

    // 🔒 Bloque 6: Manejo de eventos (clic en botón)
    public void actionPerformed(ActionEvent e) {
        String usuario = txtUsuario.getText(); // Obtiene texto de usuario
        String password = new String(txtPassword.getPassword()); // Obtiene contraseña

        if (verificarLogin(usuario, password)) { // Verifica credenciales
            JOptionPane.showMessageDialog(this, "¡Bienvenido!");// Mensaje de éxito
            dispose(); // cerrar la ventana de login
            new PostuladosCuanticos().setVisible(true); // Abre ventana principal
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");// Mensaje de error
        }
    }

    // 🔒 Bloque 7: Verificación de credenciales contra archivo JSON
    private boolean verificarLogin(String user, String pass) {
        File archivo = new File("usuarios.json");
        if (!archivo.exists())
            return false;

        try {
            // 🔒 Bloque 8: Lectura del archivo JSON
            StringBuilder contenido = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea); // Concatena cada línea
            }
            br.close();// Cierra el lector

            // 🔒 Bloque 9: Procesamiento del JSON
            JSONArray usuarios = new JSONArray(contenido.toString());

            for (int i = 0; i < usuarios.length(); i++) {
                JSONObject obj = usuarios.getJSONObject(i);
                if (obj.getString("usuario").equals(user) &&
                        obj.getString("password").equals(pass)) {
                    return true; // Credenciales válidas
                }
            }

        } catch (IOException | JSONException ex) {
            ex.printStackTrace(); // Manejo de errores
        }

        return false; // Credenciales inválidas
    }

    // 🔒 Bloque 10: Método main - Punto de entrada
    public static void main(String[] args) {
        new VentanaLogin();
    }
}
