package com.miproyecto.servlets;

import com.miproyecto.model.Paciente;
import com.miproyecto.model.Responsable;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svResponsable")
public class svResponsable extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Responsable> responsables = control.traerResponsables();
            request.setAttribute("responsables", responsables);
            request.getRequestDispatcher("/responsableLista.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los responsables: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response);
        } else {
            response.sendRedirect("svResponsable");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String responsableDni = request.getParameter("responsableDni");
            
            Responsable responsable = control.traerResponsable(responsableDni);
            
            if (responsable != null) {
                List<Paciente> pacientes = control.traerPacientesPorResponsable(responsable);
                if (pacientes.size() > 0) {
                    request.setAttribute("errorMessage", "No se puede eliminar el responsable porque tiene pacientes asignados.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }
                control.borrarResponsable(responsableDni);
            }
            response.sendRedirect("svResponsable");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al eliminar el responsable: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
