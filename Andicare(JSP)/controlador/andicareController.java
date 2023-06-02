package controlador;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.pacientes;
import modelo.pacientesLista;

@WebServlet(name = "andicareController", urlPatterns = {"/andicareController"})
public class andicareController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        pacientesLista pacientesLista = null;
        try {
            pacientesLista = new pacientesLista();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(andicareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String accion;
        RequestDispatcher dispatcher = null;
        accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            dispatcher = request.getRequestDispatcher("Paginas/cargaPacientes.jsp");
            List<pacientes> listaPacientes = pacientesLista.listaPacientes();
            request.setAttribute("lista", listaPacientes);
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    //hacer boton, comprobar los datos
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
