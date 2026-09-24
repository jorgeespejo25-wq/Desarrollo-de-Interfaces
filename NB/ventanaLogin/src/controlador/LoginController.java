package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaLogin;
import modelo.LoginModelo;
import modelo.User;

/**
 *
 * @author Jorge Espejo Matínez
 */
public class LoginController {
    private final LoginModel modelo;
    private final VentanaLogin vista;

    public LoginController(LoginModel modelo, VentanaLogin vista) {
        this.modelo = modelo;
        this.vista = vista;
        // Vincular el controlador al evento de acción del botón
        this.vista.getBtnAcceder().addActionListener(this);
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAcceder()) {
            procesarAutenticacion();
        }
    }

    private void procesarAutenticacion() {
        String username = vista.getNombreUsuario();
        String password = vista.getContrasena();

        // 1. Validación de campos obligatorios en la vista
        if (username.isEmpty() || password.isEmpty()) {
            vista.mostrarMensajeError("Debe rellenar todos los campos.");
            return;
        }

        // 2. Comunicación con el modelo
        Usuario usuarioIntento = new Usuario(username, password);
        boolean esValido = modelo.autenticar(usuarioIntento);

        // 3. Respuesta visual según el resultado del modelo
        if (esValido) {
            vista.mostrarMensajeExito("Bienvenido al sistema, " + username + ".");
            // En una aplicación completa, aquí se cerraría el login y se abriría la ventana principal
        } else {
            vista.mostrarMensajeError("Credenciales incorrectas. Inténtelo de nuevo.");
        }
    }
}
