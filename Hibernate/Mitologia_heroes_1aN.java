package hibernate_mitologia;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.Session;

//Relacionamos la tabla sql con la clase
@Entity
@Table(name = "heroes_Ascendidos")
public class Mitologia_heroes_1aN extends heroes_ascendidos implements Serializable {

    Scanner sc = new Scanner(System.in);

    public void Insertar_heroe(Session miSesion, Mitologia m) {
        //NECESITA MENU
        //NECESITA MENU
        
        System.out.println("Introduzca la id");
        int id = sc.nextInt();
        System.out.print("Introduzca el nombre");
        String nombre = sc.next();
        System.out.print("\nIntroduzca el genero (1 macho 0 hembra): ");
        int genero = sc.nextInt();
        System.out.print("Introduzca el acto que realizo");
        String acto = sc.next();
        System.out.print("Introduzca el arma");
        String arma = sc.next();
        
        miSesion.beginTransaction();
        //Creamos objetos
        heroes_ascendidos h = new heroes_ascendidos(id, nombre,genero, acto,arma, m);
        //Transacciones. Insertamos objeto en la tabla
        miSesion.beginTransaction();
        miSesion.save(h);
        miSesion.getTransaction().commit();
        System.out.println("Registro insertado");
    }

    public void Actualizar_heroe(Session miSesion) {
        //------- ACTUALIZAR CLIENTES
        miSesion.beginTransaction();
        System.out.println("Introduzca la id del que quiera actualizar");
        int id = sc.nextInt();

        heroes_ascendidos h = miSesion.get(heroes_ascendidos.class, id);
        System.out.print("Introduzca el nombre para el heroe: ");
        String nombre = sc.next();
        System.out.print("\nIntroduzca el genero para el heroe(1 macho, 0 hembra): ");
        int genero = sc.nextInt();
        System.out.print("\nIntroduzca el acto heroico: ");
        String Actos = sc.next();
        System.out.print("\nIntroduzca el arma para el dios: ");
        String Arma = sc.next();

        h.setNombre(nombre);
        h.setGenero(genero);
        h.setActos(Actos);
        h.setArma(Arma);

        miSesion.update(h);

        miSesion.getTransaction().commit();
        System.out.println("Registro actualizado");
    }

    public void Delete_heroe(Session miSesion) {
        System.out.print("Introduzca el id para borrar el heroe deseado: ");
        int id = sc.nextInt();
        miSesion.beginTransaction();
        miSesion.createQuery("DELETE FROM heroes_ascendidos WHERE ID_Heroes =" + id)
                .executeUpdate();
        miSesion.getTransaction().commit();
        System.out.println("Registros eliminados");
    }

    public void Consultar_heroe(Session miSesion) {
        List<heroes_ascendidos> heroes = miSesion.createQuery("from heroes_ascendidos").getResultList();
        for (heroes_ascendidos h : heroes) {
            System.out.println(h);
        }
    }
}
