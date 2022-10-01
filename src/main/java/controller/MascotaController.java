package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Mascotas;
import connection.DBConnection;

public class MascotaController implements IMascotaController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM mascota";

        if (ordenar == true)
        {
            sql += " ORDER BY genero " + orden;
        }

        List<String> mascotas = new ArrayList<String>();

        try
        {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {

                int id = rs.getInt("id");
                String nombremascota = rs.getString("nombremascota");
                String genero = rs.getString("genero");
                String ciudadmascota = rs.getString("ciudadmascota");
                String raza = rs.getString("raza");
                int edad = rs.getInt("edad");
                
                Mascotas mascota = new Mascotas(id, nombremascota, genero, ciudadmascota, raza, edad);

                mascotas.add(gson.toJson(mascota));

            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } finally
        {
            con.desconectar();
        }

        return gson.toJson(mascotas);

    }
    
    
}
