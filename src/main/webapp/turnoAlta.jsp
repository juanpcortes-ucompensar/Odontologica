<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Turno, com.miproyecto.model.Odontologo, com.miproyecto.model.Paciente" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>

        <%
            Turno turno = (request.getAttribute("turno") == null) ? new Turno() : (Turno) request.getAttribute("turno");
            List<Odontologo> odontologos = (List<Odontologo>) request.getAttribute("odontologos");
            List<Paciente> pacientes = (List<Paciente>) request.getAttribute("pacientes");
        %>

        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 flex-column">
                        <h1 class="h3 mb-0 text-gray-800">
                            <%= turno.getIdTurno() == 0 ? "Crear Turno" : "Actualizar Turno" %>
                        </h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-6">
                            <form class="user" action="turnoAlta" method="POST">
                                <input type="hidden" name="turnoId" value="<%= turno.getIdTurno() != 0 ? turno.getIdTurno() : "" %>">

                                <!-- Fecha del Turno -->
                                <div class="form-group">
                                    <label for="fechaTurno">Fecha del Turno</label>
                                    <input type="date" class="form-control form-control-user" id="fechaTurno"
                                           name="fechaTurno" value="<%= turno.getFechaTurno() != null ? turno.getFechaTurno() : "" %>" required>
                                </div>

                                <!-- Hora del Turno -->
                                <div class="form-group">
                                    <label for="horaTurno">Hora del Turno</label>
                                    <input type="time" class="form-control form-control-user" id="horaTurno"
                                           name="horaTurno" value="<%= turno.getHoraTurno() != null ? turno.getHoraTurno() : "" %>" required>
                                </div>

                                <!-- Tratamiento -->
                                <div class="form-group">
                                    <label for="tratamiento">Tratamiento</label>
                                    <input type="text" class="form-control form-control-user" id="tratamiento"
                                           name="tratamiento" value="<%= turno.getTratamiento() != null ? turno.getTratamiento() : "" %>" required>
                                </div>

                                <!-- Odontólogo -->
                                <div class="form-group">
                                    <label for="odontologoId">Odontólogo</label>
                                    <select class="form-control" id="odontologoId" name="odontologoId" required>
                                        <option value="">-- Selecciona un odontólogo --</option>
                                        <%
                                            for (Odontologo o : odontologos) {
                                        %>
                                            <option value="<%= o.getDni() %>" <%= turno.getIdTurno() != 0 ? (o.getDni().equals(turno.getOdontologo().getDni()) ? "selected" : "") : "" %>>
                                                <%= o.getNombre() + " " + o.getApellidos() %>
                                            </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Paciente -->
                                <div class="form-group">
                                    <label for="pacienteId">Paciente</label>
                                    <select class="form-control" id="pacienteId" name="pacienteId" required>
                                        <option value="">-- Selecciona un paciente --</option>
                                        <%
                                            for (Paciente p : pacientes) {
                                        %>
                                            <option value="<%= p.getDni() %>" <%= turno.getIdTurno() != 0 ? (p.getDni().equals(turno.getPaciente().getDni()) ? "selected" : "") : "" %>>
                                                <%= p.getNombre() + " " + p.getApellidos() %>
                                            </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Botón de Enviar -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= turno.getIdTurno() == 0 ? "Crear Turno" : "Actualizar Turno" %>
                                </button>
                            </form>
                        </div>
                        <div class="col-3"></div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>

            <%@include file="layout/footer.jsp"%>
        </div>
        <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <%@include file="layout/script.jsp"%>

</body>

</html>
