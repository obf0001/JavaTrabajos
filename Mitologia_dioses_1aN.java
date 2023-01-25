package hibernate_mitologia;

import java.io.Serializable;
import java.util.List;
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
import java.util.Scanner;
//Relacionamos la tabla sql con la clase
@Entity
@Table(name = "dioses")
public class Mitologia_dioses_1aN extends dioses implements Serializable {

    Scanner sc = new Scanner(System.in);

    public void Insertar_Dios(Session miSesion, Mitologia m) {
        //NECESITA MENU
        miSesion.beginTransaction();
        System.out.println("Introduzca la id");
        int id = sc.nextInt();
        System.out.print("Introduzca el nombre");
        String nombre = sc.next();
        System.out.print("\nIntroduzca el genero (1 macho 0 hembra): ");
        int genero = sc.nextInt();
        System.out.print("Introduzca tipo de dios/a");
        String tipo = sc.next();
        System.out.print("Introduzca el arma");
        String arma = sc.next();
        System.out.print("Introduzca la edad");
        int edad = sc.nextInt();
        //Creamos objetos
        dioses d = new dioses(id, nombre,genero,tipo,arma,edad, m);
        //Transacciones. Insertamos objeto en la tabla
        miSesion.beginTransaction();
        miSesion.save(d);
        miSesion.getTransaction().commit();
        System.out.println("Registro insertado");
    }

    public void Actualizar_Dios(Session miSesion) {
        miSesion.beginTransaction();
        System.out.println("Introduzca la id del que quiera actualizar");
        int id = sc.nextInt();
        dioses d = miSesion.get(dioses.class, id);
        
        System.out.print("Introduzca el nombre para el dios: ");
        String nombre = sc.next();
        System.out.print("\nIntroduzca el genero para el dios(1 macho, 0 hembra): ");
        int genero = sc.nextInt();
        System.out.print("\nIntroduzca la categoria de dios: ");
        String Categoria_Dios = sc.next();
        System.out.print("\nIntroduzca el arma para el dios: ");
        String Arma = sc.next();
        System.out.print("\nIntroduzca la edad para el dios: ");
        int Edad = sc.nextInt();

        d.setNombre(nombre);
        d.setGenero(genero);
        d.setCategoria_Dios(Categoria_Dios);
        d.setArma(Arma);
        d.setEdad(Edad);

        miSesion.update(d);

        miSesion.getTransaction().commit();
        System.out.println("Registro actualizado");
    }

    public void Delete_Dios(Session miSesion) {
        System.out.print("Introduzca el id para borrar el dios deseado: ");
        int id = sc.nextInt();
        miSesion.beginTransaction();
        miSesion.createQuery("DELETE FROM dioses WHERE id_dioses =" + id)
                .executeUpdate();
        miSesion.getTransaction().commit();
        System.out.println("Registros eliminados");
    }

    public void Consultar_Dios(Session miSesion) {
        List<dioses> dioses = miSesion.createQuery("from dioses").getResultList();
        for (dioses d : dioses) {
            System.out.println(d);
        }
    }
}
