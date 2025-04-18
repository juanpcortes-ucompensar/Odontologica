<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Turno" %>
<!DOCTYPE html>
<html lang="en">
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
                        <h1 class="h3 mb-0 text-gray-800">Turnos del día</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Turnos Table -->
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Fecha</th>
                                        <th scope="col">Hora de Inicio</th>
                                        <th scope="col">Paciente</th>
                                        <th scope="col">Tratamiento</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% List<Turno> turnos = (List<Turno>) request.getAttribute("turnos"); 
                                       if(turnos == null || turnos.isEmpty()) { %>
                                            <tr>
                                                <td colspan="7" class="text-center">No hay turnos disponibles</td>
                                            </tr>
                                        <% } else {
                                    for (Turno turno : turnos) { 
                                        String estado = (turno.getPaciente() != null) ? "Confirmado" : "Pendiente";
                                        String pacienteNombre = (turno.getPaciente() != null) ? turno.getPaciente().getNombre() : "No asignado";
                                    %>
                                    <tr>
                                        <td><%= turno.getIdTurno() %></td>
                                        <td><%= turno.getFechaTurno() %></td>
                                        <td><%= turno.getHoraTurno() %></td>
                                        <td><%= pacienteNombre %></td>
                                        <td><%= turno.getTratamiento() %></td>
                                        <td><%= estado %></td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="svTurno" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este turno?');">
                                                <input type="hidden" name="turnoId" value="<%= turno.getIdTurno() %>">
                                                <input type="hidden" name="_method" value="DELETE">
                                                <button type="submit" class="btn btn-danger">Finalizar</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } }%>
                                </tbody>
                            </table>
                        </div>
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
