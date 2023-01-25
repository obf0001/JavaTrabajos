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
@Table(name = "dioses")

public class dioses implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Dioses")
    private Integer ID_Dioses;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Genero")
    private Integer Genero;
    @Column(name = "Categoria_Dios")
    private String Categoria_Dios;
    @Column(name = "Arma")
    private String Arma;
    @Column(name = "Edad")
    private Integer Edad;
    
    public dioses(){
        
    }
    
    public dioses(int ID, String Nombre, int Genero, String Categoria,String Arma ,int Edad, Mitologia mito) {
        this.ID_Dioses = ID;
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.Categoria_Dios = Categoria;
        this.Arma=Arma;
        this.Edad=Edad;
        this.dioses_Mitologia=mito;
        
    }
    //GETTER Y SETTER
    public Integer getID_Dioses() {
        return ID_Dioses;
    }

    public void setID_Dioses(Integer ID_Dioses) {
        this.ID_Dioses = ID_Dioses;
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

    public String getCategoria_Dios() {
        return Categoria_Dios;
    }

    public void setCategoria_Dios(String Categoria_Dios) {
        this.Categoria_Dios = Categoria_Dios;
    }

    public String getArma() {
        return Arma;
    }

    public void setArma(String Arma) {
        this.Arma = Arma;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer Edad) {
        this.Edad = Edad;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ID_Dioses != null ? ID_Dioses.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof dioses)) {
            return false;
        }
        dioses other = (dioses) object;
        if ((this.ID_Dioses == null && other.ID_Dioses != null) || (this.ID_Dioses != null && !this.ID_Dioses.equals(other.ID_Dioses))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hibernate_Mitologia.dioses[ id=" + ID_Dioses + " ]";
    }
    //Seleccionamos todos los tipos de cascada excepto REFRESH para que no borre la fk
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "ID_Mitologia")
    private Mitologia dioses_Mitologia;

    public Mitologia getMitologia() {
        return dioses_Mitologia;
    }

    public void setMitologia(Mitologia mito) {
        this.dioses_Mitologia = mito;
    }

}

    

