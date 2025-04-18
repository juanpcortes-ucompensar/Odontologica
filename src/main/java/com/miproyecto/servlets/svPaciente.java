package com.miproyecto.servlets;

import com.miproyecto.model.Paciente;
import com.miproyecto.model.Turno;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svPaciente")
public class svPaciente extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Paciente> pacientes = control.traerPacientes();
            request.setAttribute("pacientes", pacientes);
            request.getRequestDispatcher("/pacienteLista.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los pacientes: " + e.getMessage());
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
            response.sendRedirect("svPaciente");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String pacienteDni = request.getParameter("pacienteDni");
            Paciente paciente = control.traerPaciente(pacienteDni);

            if (paciente != null) {
                List<Turno> turnos = control.traerTurnosPorPaciente(paciente);
                if (turnos.size() > 0) {
                    request.setAttribute("errorMessage", "No se puede eliminar el paciente porque tiene turnos asignados.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }
                control.borrarPaciente(pacienteDni);
            }
            response.sendRedirect("svPaciente");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al eliminar el paciente: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
