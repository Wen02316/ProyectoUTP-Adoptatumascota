
package beans;

public class Mascotas {
    
    private int id;
    private String nombremascota;
    private String genero;
    private String ciudadmascota;
    private String raza;
    private int edad;

    public Mascotas(int id, String nombremascota, String genero, String ciudadmascota, String raza, int edad) {
        this.id = id;
        this.nombremascota = nombremascota;
        this.genero = genero;
        this.ciudadmascota = ciudadmascota;
        this.raza = raza;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombremascota() {
        return nombremascota;
    }

    public void setNombremascota(String nombremascota) {
        this.nombremascota = nombremascota;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudadmascota() {
        return ciudadmascota;
    }

    public void setCiudadmascota(String ciudadmascota) {
        this.ciudadmascota = ciudadmascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Mascota{" + "id=" + id + ", nombremascota=" + nombremascota + ", genero=" + genero + ", ciudadmascota=" + ciudadmascota + ", raza=" + raza + ", edad=" + edad + '}';
    }
    
    
}
