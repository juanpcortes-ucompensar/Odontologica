package com.miproyecto.servlets;

import com.miproyecto.model.Secretario;
import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/secretarioAlta")
public class svAltaSecretario extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String secretarioDni = request.getParameter("secretarioDni");
            Secretario secretario;

            if (secretarioDni == null || secretarioDni.isEmpty()) {
                secretario = new Secretario();
            } else {
                secretario = control.traerSecretario(secretarioDni);
                if (secretario == null) {
                    throw new Exception("No se encontró un secretario con ID: " + secretarioDni);
                }
            }

            List<Usuario> usuarios = control.traerUsuarios();

            // Añadir a la solicitud los datos para ser utilizados en la JSP
            request.setAttribute("secretario", secretario);
            request.setAttribute("usuarios", usuarios);

            // Redirigir al JSP para el formulario
            request.getRequestDispatcher("/secretarioAlta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el secretario: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String secretarioDni = request.getParameter("secretarioDni");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String dni = request.getParameter("dni");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String sector = request.getParameter("sector");

            String usuarioId = request.getParameter("usuarioId");
            Usuario usuario = control.traerUsuario(Integer.parseInt(usuarioId));

            Secretario secretario;

            if (secretarioDni == null || secretarioDni.isEmpty() || secretarioDni.equals("null")) {
                secretario = new Secretario();
            } else {
                secretario = control.traerSecretario(secretarioDni);
                if (secretario == null) {
                    throw new Exception("No se encontró un secretario con DNI: " + secretarioDni);
                }
            }

            secretario.setNombre(nombre);
            secretario.setApellidos(apellidos);
            secretario.setDni(dni);
            secretario.setTelefono(telefono);
            secretario.setDireccion(direccion);
            secretario.setFechaNacimiento(fechaNacimiento);
            secretario.setSector(sector);
            secretario.setUsuario(usuario);

            if (secretarioDni == null || secretarioDni.isEmpty() || secretarioDni.equals("0")) {
                control.crearSecretario(secretario);
            } else {
                control.editarSecretario(secretario);
            }

            response.sendRedirect("svSecretario");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el secretario: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
