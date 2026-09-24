package modelo;

/**
 *
 * @author Jorge Espejo Matínez
 */
public class LoginModelo {
    
    //Simulación de credenciales válidas
    private static final String USUARIO_CORRECTO = "DAM2";
    private static final String PASSWORD_CORRECTA = "Dam2026";

    /**
     * Valida si las credenciales coinciden con el registro autorizado.
     * 
     * @param usuario Objeto con las credenciales introducidas.
     * @return true si el acceso es concedido; false en caso contrario.
     */
    public boolean autenticar(Usuario usuario) {
        if (usuario == null || usuario.getNombreUsuario() == null || usuario.getContrasena() == null) {
            return false;
        }
        return usuario.getNombreUsuario().trim().equalsIgnoreCase(USUARIO_CORRECTO)
                && usuario.getContrasena().equals(PASSWORD_CORRECTA);
    }
}
