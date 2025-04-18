package com.miproyecto.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miproyecto.model.Horario;
import com.persistencia.ControladoraPersistencia;

@WebServlet("/horarioAlta")
public class svAltaHorario extends HttpServlet  {

    private ControladoraPersistencia control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraPersistencia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String horarioId = request.getParameter("horarioId");
            Horario horario;
            if (horarioId == null) {
                horario = new Horario();
            } else {
                horario = control.traerHorario(Integer.parseInt(horarioId));
            }
            request.setAttribute("horario", horario);
            request.getRequestDispatcher("/horarioAlta.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error al cargar el horario: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String horarioId = request.getParameter("horarioId");
            String inicioHorario = request.getParameter("inicioHorario");
            String finHorario = request.getParameter("finHorario");
            
            Horario horario;

            if (horarioId == null || horarioId.isEmpty() || horarioId.equals("0")) {
                horario = new Horario();
            } else {
                horario = control.traerHorario(Integer.parseInt(horarioId));
                if (horario == null) {
                    throw new Exception("No se encontró un horario con ID: " + horarioId);
                }
            }

            horario.setInicioHorario(inicioHorario);
            horario.setFinHorario(finHorario);

            if (horarioId == null || horarioId.isEmpty() || horarioId.equals("0")) {
                control.crearHorario(horario);
            } else {
                control.editarHorario(horario);
            }
            
            response.sendRedirect("svHorario");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al guardar el horario: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
}
