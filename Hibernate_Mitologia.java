package hibernate_mitologia;

import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate_Mitologia {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try { //SessionFactory instancia = (SessionFactory) HibernateUtil.buildSessionFactory();
            //Sesiones. Factory crea la sesión conforme al fichero de configuración de hibernate
            //Session. Crea la sesión de conexión a la base de datos

            SessionFactory instancia = (SessionFactory) new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Mitologia.class).buildSessionFactory();
            Session session = instancia.openSession();
            //Creamos objeto mitologia con switch
            System.out.println("Elija la mitologia cuando inserte un dato");
            System.out.println("1.Griega");
            System.out.println("2.Nordica");
            System.out.println("3.Egipcia");
            System.out.println("4.Japonesa");
            int opcion = sc.nextInt();
            //Para el insertar necesitamos la mitologia
            Mitologia m = null;
            switch (opcion) {

                case 1:
                    m = new Mitologia(1, "Griega", "Grecia", 3000);
                    break;
                case 2:
                    m = new Mitologia(2, "Nordica", "Noruega", 3000);
                    break;
                case 3:
                    m = new Mitologia(3, "Egipcia", "Egipto", 3000);
                    break;
                default:
                    m = new Mitologia(4, "Japonesa", "Japon", 3000);
            }
            //Ejectuamos los metodos

            Mitologia_cria_1aN relc = new Mitologia_cria_1aN();
            //relc.Insertar_Cria(session, m);
//            relc.Actualizar_Cria(session);
//            relc.Delete_Cria(session);
//            relc.Consultar_Cria(session);
//
            Mitologia_dioses_1aN reld = new Mitologia_dioses_1aN();
//            reld.Insertar_Dios(session, m);
//            reld.Actualizar_Dios(session);
//            reld.Consultar_Dios(session);
//            reld.Delete_Dios(session);
//
            Mitologia_heroes_1aN relh = new Mitologia_heroes_1aN();
//            relh.Insertar_heroe(session, m);
//            relh.Actualizar_heroe(session);
//            relh.Consultar_heroe(session);
//            relh.Delete_heroe(session);
//
            Mitologia_lugares_1aN rell = new Mitologia_lugares_1aN();
//            rell.Insertar_lugar(session, m);
//            rell.Actualizar_lugar(session);
//            rell.Consultar_lugar(session);
//            rell.Delete_lugar(session);

            session.close();
        } catch (HibernateException he) {
            System.out.println(he);
        }

    }
}
//
