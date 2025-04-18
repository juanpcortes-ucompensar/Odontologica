package com.miproyecto.servlets;

import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/svLogin")
public class svLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Usuario usuario = control.traerUsuarioPorUserName(username);

        if (usuario != null && usuario.getContrasena().equals(password)) {
            // Iniciar sesión
            HttpSession session = request.getSession();
            session.setAttribute("username", usuario.getNombreUsuario());
            session.setAttribute("rol", usuario.getRol());

            response.sendRedirect("index");
        } else {
            // Si las credenciales son incorrectas, mostrar mensaje de error
            request.setAttribute("errorMessage", "Usuario o contrasenia incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidar el atributo errorMessage al recibir una solicitud GET
        request.removeAttribute("errorMessage");
        int cantUsuarios = control.traerUsuarios().size();
        if(cantUsuarios == 0){
            control.crearUsuarioAdmin();
            // Crear un usuario administrador por defecto si no hay usuarios en la base de datos
        }
        
        // Redirigir a la página de login sin error (por si alguien accede al login directamente)
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
