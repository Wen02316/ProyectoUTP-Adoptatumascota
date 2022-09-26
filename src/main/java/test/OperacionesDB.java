/** Acciones que se van a realizar dentro del aplicativo web **/
package test;

import beans.Mascotas;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;


public class OperacionesDB {
    
    public static void main(String[] args) {
        listarMascota(); 

    }
    
    public static void actualizarMascota(int id, String raza){
        DBConnection con = new DBConnection();
        
        String sql = "UPDATE mascota SET raza = ' "+raza+" ' WHERE id = " + id;
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {                                 /** Quiere decir que cuando desconecte termine el proceso **/
            con.desconectar(); 
        }
    }
    
    
        public static void listarMascota(){       /** Metodo para listar todas las peliculas **/
        DBConnection con = new DBConnection();    /** DBConnection para conectarnos a la base de datos **/
        String sql = "SELECT * FROM mascota";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);    /** Ejecuta la sentencia SQL select **/
            while (rs.next()){                                 /** next es un metodo que va a recorrer pelicula por pelicula **/
                int id = rs.getInt("id");                      /** Trae el valor que encuentre el id **/
                String nombremascota = rs.getString("nombremascota");     
                String genero = rs.getString("genero");                 
                String ciudadmascota = rs.getString("ciudadmascota"); 
                String raza = rs.getString("raza"); 
                int edad = rs.getInt("edad");
                                
                Mascotas mascotas = new Mascotas(id, nombremascota, genero, ciudadmascota, raza, edad);
                System.out.println(mascotas.toString());
            }
            st.executeQuery(sql);
            

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {                                 /** Quiere decir que cuando desconecte termine el proceso **/
            con.desconectar(); 
        }
    }
    
    
}