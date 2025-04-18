package com.miproyecto.servlets;

import com.miproyecto.model.Odontologo;
import com.miproyecto.model.Turno;
import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class svTurnosDelDia extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false); // false: no crea una nueva sesión si no existe
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            Usuario usuario = (Usuario) control.traerUsuarioPorUserName(session.getAttribute("username").toString());
            List<Turno> turnosDelDia;

            if ("odontologo".equalsIgnoreCase(usuario.getRol())) {
                Odontologo odontologo = control.traerOdontologoPorUsuario(usuario);
                turnosDelDia = control.traerTurnosPorOdontologo(odontologo);
            } else {
                turnosDelDia = control.traerTurnos();
            }
            request.setAttribute("turnos", turnosDelDia);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar los turnos: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
