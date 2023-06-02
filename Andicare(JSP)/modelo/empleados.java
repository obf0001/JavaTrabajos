
package modelo;

import java.util.List;

public class empleados {
  private String dni;
  private String nombre;
  private String apellidos;
  private String tipo;
  private centros centro;
  private List<pacientes> pacientes;
  private String nuhsa; // nuevo atributo NUHSA

  public empleados(String dni, String nombre, String apellidos, String tipo, centros centro, List<pacientes> pacientes, String nuhsa) {
    this.dni = dni;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.tipo = tipo;
    this.centro = centro;
    this.pacientes = pacientes;
    this.nuhsa = nuhsa;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public centros getCentro() {
    return centro;
  }

  public void setCentro(centros centro) {
    this.centro = centro;
  }

  public List<pacientes> getPacientes() {
    return pacientes;
  }

  public void setPacientes(List<pacientes> pacientes) {
    this.pacientes = pacientes;
  }

  public String getNuhsa() {
    return nuhsa;
  }

  public void setNuhsa(String nuhsa) {
    this.nuhsa = nuhsa;
  }
//Relacionamos la foreign key
  public void addPaciente(pacientes paciente) {
    this.pacientes.add(paciente);
    paciente.setNUHSA(this.nuhsa);
  }
}


