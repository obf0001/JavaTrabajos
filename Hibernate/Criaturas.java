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
@Table(name = "Criaturas")

public class Criaturas implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Criaturas")
    private Integer ID_Criaturas;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Genero")
    private Integer Genero;
    
    public Criaturas(){
        
    }
    public Criaturas(int ID, String Nombre, int Genero, Mitologia mito) {
        this.ID_Criaturas = ID;
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.cria_Mitologia= mito;
    }
    //GETTER Y SETTER
    public Integer getID_Criaturas() {
        return ID_Criaturas;
    }

    public void setID_Criaturas(Integer ID_Criaturas) {
        this.ID_Criaturas = ID_Criaturas;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int Genero) {
        this.Genero = Genero;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ID_Criaturas != null ? ID_Criaturas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criaturas)) {
            return false;
        }
        Criaturas other = (Criaturas) object;
        if ((this.ID_Criaturas == null && other.ID_Criaturas != null) || (this.ID_Criaturas != null && !this.ID_Criaturas.equals(other.ID_Criaturas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hibernate_Mitologia.Criaturas[ id=" + ID_Criaturas + " ]";
    }
    
    //Seleccionamos todos los tipos de cascada excepto REFRESH para que no borre la fk
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "ID_Mitologia")
    private Mitologia cria_Mitologia;

    public Mitologia getMitologia() {
        return cria_Mitologia;
    }

    public void setMitologia(Mitologia mito) {
        this.cria_Mitologia = mito;
    }
}
