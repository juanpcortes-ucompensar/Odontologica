package com.miproyecto.servlets;

import com.miproyecto.model.Horario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svHorario")
public class svHorario extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            List<Horario> horarios = control.traerHorarios();
            request.setAttribute("horarios", horarios);
            request.getRequestDispatcher("/horarioLista.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los horarios: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response); // Llamás manualmente
        } else {
            response.sendRedirect("svHorario");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String horarioId = request.getParameter("horarioId");
            control.borrarHorario(Integer.parseInt(horarioId));
            response.sendRedirect("svHorario");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al eliminar el horario: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
