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
@Table(name = "lugares")
public class Mitologia_lugares_1aN extends lugares implements Serializable {

    Scanner sc = new Scanner(System.in);

    public void Insertar_lugar(Session miSesion, Mitologia m) {
        //NECESITA MENU
        miSesion.beginTransaction();
        System.out.println("Introduzca la id");
        int id = sc.nextInt();
        System.out.print("Introduzca el nombre");
        String nombre = sc.next();
        System.out.print("\nIntroduzca accesibiliad a la humanidad (1->si , 0->no): ");
        int acc = sc.nextInt();
        System.out.print("Introduzca la edad");
        int edad = sc.nextInt();
        //Creamos objetos
        lugares l = new lugares(id, nombre, edad, acc, m);
        //Transacciones. Insertamos objeto en la tabla
        miSesion.beginTransaction();
        miSesion.save(l);
        miSesion.getTransaction().commit();
        System.out.println("Registro insertado");
    }

    public void Actualizar_lugar(Session miSesion) {
        miSesion.beginTransaction();
        System.out.println("Introduzca la id del que quiera actualizar");
        int id = sc.nextInt();

        lugares l = miSesion.get(lugares.class, id);
        System.out.print("Introduzca el nombre para el lugar: ");
        String nombre = sc.next();
        System.out.print("\nIntroduzca la edad para el lugar: ");
        int Edad = sc.nextInt();
        System.out.print("\nIntroduzca la accesibilidad a la humanidad(0: no puede, 1: si puede): ");
        int Accesible_Humanidad = sc.nextInt();

        l.setNombre(nombre);
        l.setEdad(Edad);
        l.setAccesible_Humanidad(Accesible_Humanidad);

        miSesion.update(l);

        miSesion.getTransaction().commit();
        System.out.println("Registro actualizado");
    }

    public void Delete_lugar(Session miSesion) {
        System.out.print("Introduzca el id para borrar el lugar deseado: ");
        int id = sc.nextInt();
        miSesion.beginTransaction();
        miSesion.createQuery("DELETE FROM lugares WHERE ID_Lugares =" + id)
                .executeUpdate();
        miSesion.getTransaction().commit();
        System.out.println("Registros eliminados");
    }

    public void Consultar_lugar(Session miSesion) {
        List<lugares> lugares = miSesion.createQuery("from lugares").getResultList();
        for (lugares l : lugares) {
            System.out.println(l);
        }
    }
}
