package com.miproyecto.servlets;

import com.miproyecto.model.Odontologo;
import com.miproyecto.model.Horario;
import com.miproyecto.model.Usuario;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/odontologoAlta")
public class svAltaOdontologo extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String odontologoDni = request.getParameter("odontologoDni");
            Odontologo odontologo;

            if (odontologoDni == null || odontologoDni.isEmpty()) {
                odontologo = new Odontologo();
            } else {
                odontologo = control.traerOdontologo(odontologoDni);
                if (odontologo == null) {
                    throw new Exception("No se encontró un odontólogo con ID: " + odontologoDni);
                }
            }

            List<Horario> horarios = control.traerHorarios();
            List<Usuario> usuarios = control.traerUsuarios();

            request.setAttribute("odontologo", odontologo);
            request.setAttribute("horarios", horarios);
            request.setAttribute("usuarios", usuarios);

            request.getRequestDispatcher("/odontologoAlta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el odontólogo: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String odontologoId = request.getParameter("odontologoId");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String dni = request.getParameter("dni");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String especialidad = request.getParameter("especialidad");

            String usuarioId = request.getParameter("usuarioId");
            Usuario usuario = control.traerUsuario(Integer.parseInt(usuarioId));
            String horarioId = request.getParameter("horarioId");
            Horario horario = control.traerHorario(Integer.parseInt(horarioId));

            Odontologo odontologo;

            if (odontologoId == null || odontologoId.isEmpty() || odontologoId.equals("null")) {
                odontologo = new Odontologo();
            } else {
                odontologo = control.traerOdontologo(odontologoId);
                if (odontologo == null) {
                    throw new Exception("No se encontró un odontólogo con ID: " + odontologoId);
                }
            }

            odontologo.setNombre(nombre);
            odontologo.setApellidos(apellidos);
            odontologo.setDni(dni);
            odontologo.setTelefono(telefono);
            odontologo.setDireccion(direccion);
            odontologo.setFechaNacimiento(fechaNacimiento);
            odontologo.setEspecialidad(especialidad);
            odontologo.setUsuario(usuario);
            odontologo.setHorario(horario);

            if (odontologoId == null || odontologoId.isEmpty() || odontologoId.equals("0")) {
                control.crearOdontologo(odontologo);
            } else {
                control.editarOdontologo(odontologo);
            }

            response.sendRedirect("svOdontologo");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el odontólogo: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
