package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usuarioAlta")
public class svAltaUsuario extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String usuarioId = request.getParameter("usuarioId");
            Usuario usuario;

            if (usuarioId == null || usuarioId.isEmpty()) {
                usuario = new Usuario();
            } else {
                usuario = control.traerUsuario(Integer.parseInt(usuarioId));
                if (usuario == null) {
                    throw new Exception("No se encontró un usuario con ID: " + usuarioId);
                }
            }

            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/usuarioAlta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el usuario: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idUsuario = request.getParameter("usuarioId");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasena = request.getParameter("contrasena");
            String rol = request.getParameter("rol");

            Usuario usuario;

            if (idUsuario == null || idUsuario.isEmpty() || idUsuario.equals("0")) {
                usuario = new Usuario();
            } else {
                usuario = control.traerUsuario(Integer.parseInt(idUsuario));
                if (usuario == null) {
                    throw new Exception("No se encontró un usuario con ID: " + idUsuario);
                }
            }

            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);
            usuario.setRol(rol);

            if (idUsuario == null || idUsuario.isEmpty() || idUsuario.equals("0")) {
                control.crearUsuario(usuario);
            } else {
                control.editarUsuario(usuario);
            }

            response.sendRedirect("svUsuario");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el usuario: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
