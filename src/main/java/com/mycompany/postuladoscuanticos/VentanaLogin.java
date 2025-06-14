package com.mycompany.postuladoscuanticos;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import org.json.*;

public class VentanaLogin extends JFrame implements ActionListener {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    public VentanaLogin() {
        setTitle("Iniciar Sesión");
        setSize(400, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Etiqueta y campo usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 30, 80, 25);
        EstilosSwing.estilizarLabel(lblUsuario, false);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(140, 30, 200, 25);
        EstilosSwing.estilizarCampoTexto(txtUsuario);
        add(txtUsuario);

        // Etiqueta y campo contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 70, 80, 25);
        EstilosSwing.estilizarLabel(lblPassword, false);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 70, 200, 25);
        EstilosSwing.estilizarCampoTexto(txtPassword);
        add(txtPassword);

        // Botón ingresar
        btnIngresar = EstilosSwing.crearBotonConHover("Ingresar", 140, 120, 100, 30);
        btnIngresar.addActionListener(this);
        add(btnIngresar);

        EstilosSwing.aplicarFondoVentana(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        if (verificarLogin(usuario, password)) {
            JOptionPane.showMessageDialog(this, "¡Bienvenido!");
            dispose(); // cerrar la ventana de login
            new PostuladosCuanticos().setVisible(true); // abrir ventana principal
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }

    private boolean verificarLogin(String user, String pass) {
        File archivo = new File("usuarios.json");
        if (!archivo.exists())
            return false;

        try {
            StringBuilder contenido = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }
            br.close();

            JSONArray usuarios = new JSONArray(contenido.toString());

            for (int i = 0; i < usuarios.length(); i++) {
                JSONObject obj = usuarios.getJSONObject(i);
                if (obj.getString("usuario").equals(user) &&
                        obj.getString("password").equals(pass)) {
                    return true;
                }
            }

        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        new VentanaLogin();
    }
}
