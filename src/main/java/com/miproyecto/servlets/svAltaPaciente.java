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

@WebServlet("/pacienteAlta")
public class svAltaPaciente extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pacienteDni = request.getParameter("pacienteDni");
            Paciente paciente;

            if (pacienteDni == null || pacienteDni.isEmpty()) {
                paciente = new Paciente();
            } else {
                paciente = control.traerPaciente(pacienteDni);
                if (paciente == null) {
                    throw new Exception("No se encontró un paciente con DNI: " + pacienteDni);
                }
            }

            List<Responsable> responsables = control.traerResponsables();

            request.setAttribute("paciente", paciente);
            request.setAttribute("responsables", responsables);

            request.getRequestDispatcher("/pacienteAlta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el paciente: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener parámetros del formulario
            String pacienteId = request.getParameter("pacienteId");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String dni = request.getParameter("dni");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String tipoSangre = request.getParameter("tipoSangre");
            boolean tieneEps = Boolean.parseBoolean(request.getParameter("tieneEps"));
            String responsableId = request.getParameter("responsableId");

            Responsable responsable = control.traerResponsable(responsableId);

            // Crear o editar paciente
            Paciente paciente;

            if (pacienteId == null || pacienteId.isEmpty() || pacienteId.equals("null")) {
                paciente = new Paciente();
            } else {
                paciente = control.traerPaciente(pacienteId);
                if (paciente == null) {
                    throw new Exception("No se encontró un paciente con ID: " + pacienteId);
                }
            }

            // Asignar los valores al paciente
            paciente.setNombre(nombre);
            paciente.setApellidos(apellidos);
            paciente.setDni(dni);
            paciente.setTelefono(telefono);
            paciente.setDireccion(direccion);
            paciente.setFechaNacimiento(fechaNacimiento);
            paciente.setTipoSangre(tipoSangre);
            paciente.setTieneEps(tieneEps);
            paciente.setResponsable(responsable);

            // Crear o editar el paciente en la base de datos
            if (pacienteId == null || pacienteId.isEmpty() || pacienteId.equals("0")) {
                control.crearPaciente(paciente);
            } else {
                control.editarPaciente(paciente);
            }

            // Redirigir a la lista de pacientes o alguna otra página relevante
            response.sendRedirect("svPaciente");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el paciente: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
