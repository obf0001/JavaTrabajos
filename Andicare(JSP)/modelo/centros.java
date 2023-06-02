
package modelo;

public class centros {
    private int idCentro;
    private String Nombre;
    private String Direccion;
    private String Contacto;
    
    public centros(int idCentro, String Nombre, String Direccion, String Contacto) {
        this.idCentro = idCentro;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Contacto = Contacto;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String Contacto) {
        this.Contacto = Contacto;
    }
}
