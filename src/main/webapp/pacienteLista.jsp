<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Paciente" %>
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

                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Lista de Pacientes</h1>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>DNI</th>
                                        <th>Nombre</th>
                                        <th>Apellidos</th>
                                        <th>Teléfono</th>
                                        <th>Dirección</th>
                                        <th>Fecha de Nacimiento</th>
                                        <th>Tipo de Sangre</th>
                                        <th>Tiene EPS</th>
                                        <th>Responsable</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Paciente> pacientes = (List<Paciente>) request.getAttribute("pacientes");
                                        for (Paciente paciente : pacientes) {
                                    %>
                                    <tr>
                                        <td><%= paciente.getDni() %></td>
                                        <td><%= paciente.getNombre() %></td>
                                        <td><%= paciente.getApellidos() %></td>
                                        <td><%= paciente.getTelefono() %></td>
                                        <td><%= paciente.getDireccion() %></td>
                                        <td><%= paciente.getFechaNacimiento() %></td>
                                        <td><%= paciente.getTipoSangre() %></td>
                                        <td><%= paciente.isTieneEps() ? "Sí" : "No" %></td>
                                        <td><%= paciente.getResponsable() != null 
                                                    ? paciente.getResponsable().getNombre() + " " + paciente.getResponsable().getApellidos()
                                                    : "N/A" %></td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="pacienteAlta" method="get">
                                                <input type="hidden" name="pacienteDni" value="<%= paciente.getDni() %>">
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </form>
                                            <form action="svPaciente" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este paciente?');">
                                                <input type="hidden" name="pacienteDni" value="<%= paciente.getDni() %>">
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
