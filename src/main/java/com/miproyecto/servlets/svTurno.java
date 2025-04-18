package com.miproyecto.servlets;

import com.miproyecto.model.Turno;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svTurno")
public class svTurno extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Turno> turnos = control.traerTurnos();
            request.setAttribute("turnos", turnos);
            request.getRequestDispatcher("/turnoLista.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los turnos: " + e.getMessage());
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
            response.sendRedirect("svTurno");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String turnoId = request.getParameter("turnoId");
            Turno turno = control.traerTurno(Integer.parseInt(turnoId));

            if (turno != null) {
                control.borrarTurno(Integer.parseInt(turnoId));
            } else {
                request.setAttribute("errorMessage", "No se encontró el turno con el ID: " + turnoId);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
            response.sendRedirect("svTurno");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al eliminar el turno: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
