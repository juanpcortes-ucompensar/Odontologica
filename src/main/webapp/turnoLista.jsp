<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Turno" %>
<!DOCTYPE html>
<html lang="es">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Turnos Registrados</h1>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Fecha</th>
                                        <th>Hora</th>
                                        <th>Tratamiento</th>
                                        <th>Odontólogo</th>
                                        <th>Paciente</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Turno> turnos = (List<Turno>) request.getAttribute("turnos");
                                        int i = 1;
                                        for (Turno turno : turnos) {
                                    %>
                                    <tr>
                                        <td><%= i++ %></td>
                                        <td><%= turno.getFechaTurno() %></td>
                                        <td><%= turno.getHoraTurno() %></td>
                                        <td><%= turno.getTratamiento() %></td>
                                        <td><%= turno.getOdontologo() != null ? turno.getOdontologo().getNombre() + " " + turno.getOdontologo().getApellidos() : "No asignado" %></td>
                                        <td><%= turno.getPaciente() != null ? turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellidos() : "No asignado" %></td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="turnoAlta" method="get">
                                                <input type="hidden" name="turnoId" value="<%= turno.getIdTurno() %>">
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </form>
                                            <form action="svTurno" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este turno?');">
                                                <input type="hidden" name="turnoId" value="<%= turno.getIdTurno() %>">
                                                <input type="hidden" name="_method" value="DELETE">
                                                <button type="submit" class="btn btn-danger">Eliminar</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
            </div>

            <%@include file="layout/footer.jsp"%>
        </div>
    </div>

    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <%@include file="layout/script.jsp"%>
</body>
</html>
