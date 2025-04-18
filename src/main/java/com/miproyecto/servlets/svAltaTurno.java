package com.miproyecto.servlets;

import com.miproyecto.model.Turno;
import com.miproyecto.model.Odontologo;
import com.miproyecto.model.Paciente;
import com.persistencia.ControladoraPersistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/turnoAlta")
public class svAltaTurno extends HttpServlet {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String turnoId = request.getParameter("turnoId");
            Turno turno;

            if (turnoId == null || turnoId.isEmpty()) {
                turno = new Turno(); // Crear un turno vacío
            } else {
                turno = control.traerTurno(Integer.parseInt(turnoId));
                if (turno == null) {
                    throw new Exception("No se encontró un turno con ID: " + turnoId);
                }
            }

            List<Odontologo> odontologos = control.traerOdontologos();
            List<Paciente> pacientes = control.traerPacientes();

            request.setAttribute("turno", turno);
            request.setAttribute("odontologos", odontologos);
            request.setAttribute("pacientes", pacientes);

            request.getRequestDispatcher("/turnoAlta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el turno: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String turnoId = request.getParameter("turnoId");
            String fechaTurno = request.getParameter("fechaTurno");
            String horaTurno = request.getParameter("horaTurno");
            String tratamiento = request.getParameter("tratamiento");

            String odontologoDni = request.getParameter("odontologoId");
            Odontologo odontologo = control.traerOdontologo(odontologoDni);
            String pacienteId = request.getParameter("pacienteId");
            Paciente paciente = control.traerPaciente(pacienteId);

            if (turnoId != null && !turnoId.isEmpty() && !turnoId.equals("0")) {
                // Verificamos si el paciente ya tiene un turno activo 
                if (control.pacienteTieneTurnoActivo(paciente)) {
                    request.setAttribute("errorMessage", "El paciente ya tiene un turno activo.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }

                // Verificamos si el odontólogo está disponible en esa fecha y hora
                if (!control.odontologoDisponible(odontologo, fechaTurno, horaTurno)) {
                    request.setAttribute("errorMessage", "El odontólogo no está disponible en esta fecha y hora.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }
            }

            Turno turno;

            if (turnoId == null || turnoId.isEmpty() || turnoId.equals("0")) {
                turno = new Turno();
            } else {
                turno = control.traerTurno(Integer.parseInt(turnoId));
                if (turno == null) {
                    throw new Exception("No se encontró un turno con ID: " + turnoId);
                }
            }

            turno.setFechaTurno(fechaTurno);
            turno.setHoraTurno(horaTurno);
            turno.setTratamiento(tratamiento);
            turno.setOdontologo(odontologo);
            turno.setPaciente(paciente);

            if (turnoId == null || turnoId.isEmpty() || turnoId.equals("0")) {
                control.crearTurno(turno);
            } else {
                control.editarTurno(turno);
            }

            response.sendRedirect("svTurno");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el turno: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
