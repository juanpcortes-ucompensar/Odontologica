package com.miproyecto.servlets;

import com.miproyecto.model.Odontologo;
import com.miproyecto.model.Turno;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svOdontologo")
public class svOdontologo extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Odontologo> odontologos = control.traerOdontologos();
            request.setAttribute("odontologos", odontologos);
            request.getRequestDispatcher("/odontologoLista.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los odontólogos: " + e.getMessage());
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
            response.sendRedirect("svOdontologo");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String odontologoDni = request.getParameter("odontologoDni");
            Odontologo odontologo = control.traerOdontologo(odontologoDni);
            
            if (odontologo != null) {
                List<Turno> turnos = control.traerTurnosPorOdontologo(odontologo);
                if (turnos.size() > 0) {
                    request.setAttribute("errorMessage", "No se puede eliminar el odontólogo porque tiene turnos asignados.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }
                control.borrarOdontologo(odontologoDni);
            }
            response.sendRedirect("svOdontologo");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al eliminar el odontólogo: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
