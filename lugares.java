package hibernate_mitologia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Relacionamos la tabla sql con la clase
@Entity
@Table(name = "lugares")
public class lugares implements Serializable{
    //Relacionamos las columnas con variables
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Lugares")
    private Integer ID_Lugares;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Edad")
    private Integer Edad;
    @Column(name = "Accesible_Humanidad")
    private Integer Accesible_Humanidad;   
    
    //CONSTRUCTOR
    public lugares(){
        
    }
    
    public lugares(int ID, String Nombre, int Edad, int Accesibilidad, Mitologia mito){
        this.ID_Lugares=ID;
        this.Nombre=Nombre;
        this.Edad=Edad;
        this.Accesible_Humanidad=Accesibilidad;
        this.lugares_Mitologia=mito;
    }
    //GETTER Y SETTER
    public Integer getID_Lugares() {
        return ID_Lugares;
    }

    public void setID_Lugares(Integer ID_Lugares) {
        this.ID_Lugares = ID_Lugares;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer Edad) {
        this.Edad = Edad;
    }

    public int getAccesible_Humanidad() {
        return Accesible_Humanidad;
    }

    public void setAccesible_Humanidad(int Accesible_Humanidad) {
        this.Accesible_Humanidad = Accesible_Humanidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ID_Lugares != null ? ID_Lugares.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof lugares)) {
            return false;
        }
        lugares other = (lugares) object;
        if ((this.ID_Lugares == null && other.ID_Lugares != null) || (this.ID_Lugares != null && !this.ID_Lugares.equals(other.ID_Lugares))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hibernate_Mitologia.lugares[ id=" + ID_Lugares + " ]";
    }
    //Seleccionamos todos los tipos de cascada excepto REFRESH para que no borre la fk
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "ID_Mitologia")
    private Mitologia lugares_Mitologia;

    public Mitologia getMitologia() {
        return lugares_Mitologia;
    }

    public void setMitologia(Mitologia mito) {
        this.lugares_Mitologia = mito;
    }
    
}
