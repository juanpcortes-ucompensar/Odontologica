<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Responsable" %>
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

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Lista de Responsables</h1>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>DNI</th>
                                        <th>Nombre</th>
                                        <th>Apellidos</th>
                                        <th>Tipo Responsable</th>
                                        <th>Teléfono</th>
                                        <th>Dirección</th>
                                        <th>Fecha Nac.</th>
                                        <th>Paciente Asociado</th>
                                        <th scope="col">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Responsable> responsables = (List<Responsable>) request.getAttribute("responsables");
                                        int i = 1;
                                        for (Responsable resp : responsables) {
                                    %>
                                    <tr>
                                        <td><%= i++ %></td>
                                        <td><%= resp.getDni() %></td>
                                        <td><%= resp.getNombre() %></td>
                                        <td><%= resp.getApellidos() %></td>
                                        <td><%= resp.getTipoResponsable() %></td>
                                        <td><%= resp.getTelefono() %></td>
                                        <td><%= resp.getDireccion() %></td>
                                        <td><%= resp.getFechaNacimiento() %></td>
                                        <td>
                                            <%= resp.getPacientes() != null ? resp.getPacientes().getNombre() + " " + resp.getPacientes().getApellidos() : "Ninguno" %>
                                        </td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="responsableAlta" method="get">
                                                <input type="hidden" name="responsableDni" value="<%= resp.getDni() %>">
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </form>
                                            <form action="svResponsable" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este responsable?');">
                                                <input type="hidden" name="responsableDni" value="<%= resp.getDni() %>">
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
