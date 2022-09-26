
package beans;

import java.sql.Date;

public class Adopcion {
    
    private int id;
    private String username;
    private Date fechaAdopcion;
    private String genero;
    private String raza;

    public Adopcion(int id, String username, Date fecha, String genero, String raza) {
        this.id = id;
        this.username = username;
        this.fechaAdopcion = fecha;
        this.genero = genero;
        this.raza = raza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFecha() {
        return fechaAdopcion;
    }

    public void setFecha(Date fecha) {
        this.fechaAdopcion = fecha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Override
    public String toString() {
        return "Adopcion{" + "id=" + id + ", username=" + username + ", fechaAdopcion=" + fechaAdopcion + ", genero=" + genero + ", raza=" + raza + '}';
    }
    
    
}
