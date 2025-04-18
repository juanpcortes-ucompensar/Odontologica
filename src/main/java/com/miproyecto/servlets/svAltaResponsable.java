package com.miproyecto.servlets;

import com.miproyecto.model.Responsable;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/responsableAlta")
public class svAltaResponsable extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String responsableDni = request.getParameter("responsableDni");
            Responsable responsable;

            if (responsableDni == null || responsableDni.isEmpty()) {
                responsable = new Responsable();
            } else {
                responsable = control.traerResponsable(responsableDni);
                if (responsable == null) {
                    throw new Exception("No se encontró un responsable con Dni: " + responsableDni);
                }
            }

            request.setAttribute("responsable", responsable);
            request.getRequestDispatcher("/responsableAlta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el responsable: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String responsableId = request.getParameter("responsableId");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String dni = request.getParameter("dni");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String tipoResponsable = request.getParameter("tipoResponsable");

            Responsable responsable;

            if (responsableId == null || responsableId.isEmpty() || responsableId.equals("null")) {
                System.out.println("Creando nuevo responsable");
                responsable = new Responsable();
            } else {
                System.out.println("Editando responsable existente");
                responsable = control.traerResponsable(responsableId);
                if (responsable == null) {
                    throw new Exception("No se encontró un responsable con ID: " + responsableId);
                }
            }

            responsable.setNombre(nombre);
            responsable.setApellidos(apellidos);
            responsable.setDni(dni);
            responsable.setTelefono(telefono);
            responsable.setDireccion(direccion);
            responsable.setFechaNacimiento(fechaNacimiento);
            responsable.setTipoResponsable(tipoResponsable);

            if (responsableId == null || responsableId.isEmpty() || responsableId.equals("null")) {
                control.crearResponsable(responsable);
            } else {
                control.editarResponsable(responsable);
            }

            response.sendRedirect("svResponsable");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el responsable: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
