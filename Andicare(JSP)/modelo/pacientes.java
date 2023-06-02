
package modelo;

import java.sql.Date;

public class pacientes {
    int idPaciente;
    String DNI;
    String nombre;
    String appelidos;
    Date fecha_nacimiento;
    String estado_LibreView;
    String NUHSA;

    public pacientes(int idPaciente, String DNI, String nombre, String appelidos, Date fecha_nacimiento, String estado_LibreView, String NUHSA) {
        this.idPaciente = idPaciente;
        this.DNI = DNI;
        this.nombre = nombre;
        this.appelidos = appelidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado_LibreView = estado_LibreView;
        this.NUHSA = NUHSA;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAppelidos() {
        return appelidos;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getEstado_LibreView() {
        return estado_LibreView;
    }

    public String getNUHSA() {
        return NUHSA;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAppelidos(String appelidos) {
        this.appelidos = appelidos;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setEstado_LibreView(String estado_LibreView) {
        this.estado_LibreView = estado_LibreView;
    }

    public void setNUHSA(String NUHSA) {
        this.NUHSA = NUHSA;
    }
    
}
