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
@Table(name = "heroes_Ascendidos")
public class heroes_ascendidos implements Serializable{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Heroes")
    private Integer ID_Heroes;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Genero")
    private Integer Genero;
    @Column(name = "Actos")
    private String Actos;
    @Column(name = "Arma")
    private String Arma;
   
    
    public heroes_ascendidos(){
        
    }
    public heroes_ascendidos(int ID, String Nombre, int Genero, String Actos, String Arma,Mitologia mito){
        this.ID_Heroes=ID;
        this.Nombre=Nombre;
        this.Genero=Genero;
        this.Actos=Actos;
        this.Arma=Arma;
        this.heroes_Mitologia=mito;
    }
        //GETTER Y SETTER
    public Integer getID_Heroes() {
        return ID_Heroes;
    }

    public void setID_Heroes(Integer ID_Heroes) {
        this.ID_Heroes = ID_Heroes;
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

    public String getActos() {
        return Actos;
    }

    public void setActos(String Actos) {
        this.Actos = Actos;
    }

    public String getArma() {
        return Arma;
    }

    public void setArma(String Arma) {
        this.Arma = Arma;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ID_Heroes != null ? ID_Heroes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof heroes_ascendidos)) {
            return false;
        }
        heroes_ascendidos other = (heroes_ascendidos) object;
        if ((this.ID_Heroes == null && other.ID_Heroes != null) || (this.ID_Heroes != null && !this.ID_Heroes.equals(other.ID_Heroes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hibernate_Mitologia.heroes_ascendidos[ id=" + ID_Heroes + " ]";
    }
    //Seleccionamos todos los tipos de cascada excepto REFRESH para que no borre la fk
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "ID_Mitologia")
    private Mitologia heroes_Mitologia;

    public Mitologia getMitologia() {
        return heroes_Mitologia;
    }

    public void setMitologia(Mitologia mito) {
        this.heroes_Mitologia = mito;
    }
    
}
