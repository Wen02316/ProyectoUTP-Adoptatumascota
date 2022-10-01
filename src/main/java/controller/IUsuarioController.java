package controller;

import java.util.Map;

public interface IUsuarioController {

    public String login(String username, String contrasena);
    
    public String register(String username, String contrasena, 
            String nombre, String apellidos, String email, String ciudad);
    
    public String pedir(String username);
    
    public String modificar(String username, String nuevaContrasena, 
        String nuevoNombre, String nuevosApellidos, String nuevoEmail, 
        String nuevoCiudad);
    
    public String verEdad(String username);

    public String devolverMascotas(String username, Map<Integer, Integer> edad);

    public String eliminar(String username);
    
}


