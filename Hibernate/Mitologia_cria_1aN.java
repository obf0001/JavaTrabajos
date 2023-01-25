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
@Table(name = "Criaturas")
public class Mitologia_cria_1aN extends Criaturas implements Serializable {

    Scanner sc = new Scanner(System.in);

    public void Insertar_Cria(Session miSesion, Mitologia m) {
        //NECESITA MENU
        System.out.println("Introduzca la id para la criatura");
        int id = sc.nextInt();
        System.out.print("Introduzca el nombre para la criatura: ");
        String nombre = sc.next();
        System.out.print("\nIntroduzca el genero para la criatura (1 macho 0 hembra): ");
        int genero = sc.nextInt();
        miSesion.beginTransaction();
        //Creamos objetos
        Criaturas c = new Criaturas(id, nombre, genero, m);
        //Transacciones. Insertamos objeto en la tabla
        miSesion.save(c);
        miSesion.getTransaction().commit();
        System.out.println("Registro insertado");
    }

    public void Actualizar_Cria(Session miSesion) {
        //------- ACTUALIZAR CLIENTES
        //Actualizar cliente con id
        //Comprobar ids en tabla clientes
        miSesion.beginTransaction();
        System.out.println("Introduzca la id del que quiera actualizar");
        int id = sc.nextInt();

        Criaturas c = miSesion.get(Criaturas.class, id);
        System.out.print("Introduzca el nombre para la criatura: ");
        String nombre = sc.next();
        System.out.print("\nIntroduzca el genero para la criatura: ");
        int genero = sc.nextInt();

        c.setNombre(nombre);
        c.setGenero(genero);

        miSesion.update(c);

        miSesion.getTransaction().commit();
        System.out.println("Registro actualizado");
    }

    public void Delete_Cria(Session miSesion) {
        System.out.print("Introduzca el id para borrar la criatura deseada: ");
        int id = sc.nextInt();
        miSesion.beginTransaction();
        miSesion.createQuery("DELETE FROM Criaturas WHERE ID_Criaturas =" + id)
                .executeUpdate();
        miSesion.getTransaction().commit();
        System.out.println("Registros eliminados");

    }

    public void Consultar_Cria(Session miSesion) {
        List<Criaturas> cria = miSesion.createQuery("from Criaturas").getResultList();
        for (Criaturas c : cria) {
            System.out.println(c);
        }
    }
}
