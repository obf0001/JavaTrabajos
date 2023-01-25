package hibernate_mitologia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Session;

//Relacionamos la tabla sql con la clase
@Entity
@Table(name = "Mitologia")

public class Mitologia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Mitologia")
    private Integer ID_Mitologia;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Lugar_Origen")
    private String Lugar_Origen;
    @Column(name = "Edad_Antiguedad")
    private Integer Edad_Antiguedad;

    public Mitologia(){}
    
    public Mitologia(int ID, String Nombre, String Lugar_Origen, Integer Edad_Antiguedad) {
        this.ID_Mitologia = ID;
        this.Nombre = Nombre;
        this.Lugar_Origen = Lugar_Origen;
        this.Edad_Antiguedad = Edad_Antiguedad;
    }
    //GETTER Y SETTER
    public Integer getID_Mitologia() {
        return ID_Mitologia;
    }

    public void setID_Mitologia(Integer ID_Mitologia) {
        this.ID_Mitologia = ID_Mitologia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getLugar_Origen() {
        return Lugar_Origen;
    }

    public void setLugar_Origen(String Lugar_Origen) {
        this.Lugar_Origen = Lugar_Origen;
    }

    public Integer getEdad_Antiguedad() {
        return Edad_Antiguedad;
    }

    public void setEdad_Antiguedad(Integer Edad_Antiguedad) {
        this.Edad_Antiguedad = Edad_Antiguedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ID_Mitologia != null ? ID_Mitologia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mitologia)) {
            return false;
        }
        Mitologia other = (Mitologia) object;
        if ((this.ID_Mitologia == null && other.ID_Mitologia != null) || (this.ID_Mitologia != null && !this.ID_Mitologia.equals(other.ID_Mitologia))) {
            return false;
        }
        return true;
    }

    public void Insertar_Mito(Session miSesion) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el ID de la Mitologia: ");
        int id = sc.nextInt();
        System.out.print("\nIntroduzca el nombre de la Mitologia: ");
        String nombre = sc.nextLine();
        System.out.print("\nIntroduzca el lugar de origen: ");
        String lugar_origen = sc.nextLine();
        System.out.print("\nIntroduzca la antiguedad: ");
        int edad_ant = sc.nextInt();

        Mitologia m = new Mitologia(id, nombre, lugar_origen, edad_ant);

        miSesion.beginTransaction();
        miSesion.save(m);
        miSesion.getTransaction().commit();
        System.out.println("Registro insertado");
    }

    public void Actualizar_mitologia(Session miSesion, int ID_Mitologia) {
        Scanner sc = new Scanner(System.in);
        //------- ACTUALIZAR CLIENTES
        miSesion.beginTransaction();

        Mitologia m = miSesion.get(Mitologia.class, ID_Mitologia);
        System.out.print("Introduzca el nombre de la mitologia: ");
        String nombre = sc.nextLine();
        System.out.print("\nIntroduzca el lugar de origen: ");
        String lugar_origen = sc.nextLine();
        System.out.print("\nIntroduzca la antiguedad: ");
        int edad_ant = sc.nextInt();

        m.setNombre(nombre);
        m.setLugar_Origen(lugar_origen);
        m.setEdad_Antiguedad(edad_ant);

        miSesion.update(m);

        miSesion.getTransaction().commit();
        System.out.println("Registro actualizado");
    }

    public void Delete_Mitologia(Session miSesion) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el id para borrar la mitologia deseado: ");
        int id = sc.nextInt();
        miSesion.beginTransaction();
        miSesion.createQuery("DELETE FROM Mitologia WHERE ID_Mitologia =" + id)
                .executeUpdate();
        miSesion.getTransaction().commit();
        System.out.println("Registros eliminados");
    }

    public void Consultar_Mitologia(Session miSesion) {
        List<Mitologia> mito = miSesion.createQuery("from Mitologia").getResultList();
        for (Mitologia l : mito) {
            System.out.println(l);
        }
    }

    @Override
    public String toString() {
        return "Hibernate_Mitologia.Mitologia[ id=" + ID_Mitologia + " ]";
    }

    //Relación con la tabla pedido
    @OneToMany(mappedBy = "cria_Mitologia", cascade = CascadeType.ALL)

    private List<Criaturas> criaturas;

    public List<Criaturas> getCriaturas() {
        return criaturas;
    }

    public void addCriatura(Criaturas cria) {
        if (criaturas == null) {
            criaturas = new ArrayList<>();
        }
        criaturas.add(cria);
        cria.setMitologia(this);
    }

    @OneToMany(mappedBy = "dioses_Mitologia", cascade = CascadeType.ALL)

    private List<dioses> dioses;

    public List<dioses> getdioses() {
        return dioses;
    }

    public void addDios(dioses dios) {
        if (dioses == null) {
            dioses = new ArrayList<>();
        }
        dioses.add(dios);
        dios.setMitologia(this);
    }

    @OneToMany(mappedBy = "lugares_Mitologia", cascade = CascadeType.ALL)

    private List<lugares> lugaresA;

    public List<lugares> getlugares() {
        return lugaresA;
    }

    public void addLugar(lugares lugar) {
        if (lugaresA == null) {
            lugaresA = new ArrayList<>();
        }
        lugaresA.add(lugar);
        lugar.setMitologia(this);
    }

    @OneToMany(mappedBy = "heroes_Mitologia", cascade = CascadeType.ALL)

    private List<heroes_ascendidos> heroesA;

    public List<heroes_ascendidos> getheroes() {
        return heroesA;
    }

    public void addHeroe(heroes_ascendidos heroes) {
        if (heroesA == null) {
            heroesA = new ArrayList<>();
        }
        heroesA.add(heroes);
        heroes.setMitologia(this);
    }

}
