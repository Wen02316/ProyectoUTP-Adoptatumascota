package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;
import beans.Usuario;
import connection.DBConnection;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController implements IUsuarioController {

    //Metodo para proceso de logeo
    @Override
    public String login(String username, String contrasena) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "SELECT * FROM usuario WHERE username = '" + username
                + "' AND contrasena = '" + contrasena + "'";
        
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String ciudad = rs.getString("ciudad");
                

                Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, ciudad);
                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    
    //Metodo para proceso de registro de usuario
    @Override
    public String register(String username, String contrasena, String nombre, String apellidos, String email,
            String ciudad) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "INSERT INTO usuario VALUES('" + username + "', '" + contrasena + "', '" + nombre
                + "', '" + apellidos + "', '" + email + "','" + ciudad + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            Usuario usuario = new Usuario(username, contrasena, nombre, apellidos, email, ciudad);

            st.close();

            return gson.toJson(usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    @Override
    public String pedir(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM usuario WHERE username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String contrasena = rs.getString("contrasena");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String ciudad = rs.getString("ciudad");
                

                Usuario usuario = new Usuario(username, contrasena,
                        nombre, apellidos, email, ciudad);

                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    
    @Override
    public String modificar(String username, String nuevaContrasena, 
            String nuevoNombre, String nuevosApellidos,
            String nuevoEmail, String nuevoCiudad) {   

        DBConnection con = new DBConnection();

        String sql = "Update usuario set contrasena = '" + nuevaContrasena +
                "', nombre = '" + nuevoNombre + "', "
                + "apellidos = '" + nuevosApellidos + "', email = '" 
                + nuevoEmail + "', ciudad = " + nuevoCiudad;

        sql += " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
     @Override
    public String verEdad(String username) {

        DBConnection con = new DBConnection();
        String sql = "Select id,count(*) as num_edad from adopcion where username = '" 
                + username + "' group by id;";

        Map<Integer, Integer> edad = new HashMap<Integer, Integer>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int num_edad = rs.getInt("num_edad");

                edad.put(id, num_edad);
            }

            devolverMascotas(username, edad);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String devolverMascotas(String username, Map<Integer, Integer> edad) {

        DBConnection con = new DBConnection();

        try {
            for (Map.Entry<Integer, Integer> mascota : edad.entrySet()) {
                int id = mascota.getKey();
                int num_edad = mascota.getValue();

                String sql = "Update mascota set edad = (Select edad + " + num_edad +
                        " from mascota where id = " + id + ") where id = " + id;

                Statement st = con.getConnection().createStatement();
                st.executeUpdate(sql);

            }

            this.eliminar(username);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    public String eliminar(String username) {

        DBConnection con = new DBConnection();

        String sql1 = "Delete from adopcion where username = '" + username + "'";
        String sql2 = "Delete from usuario where username = '" + username + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
}
