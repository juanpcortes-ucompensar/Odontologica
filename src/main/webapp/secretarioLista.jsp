<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Secretario" %>
<%@ page import="com.miproyecto.model.Usuario" %>
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
                        <h1 class="h3 mb-0 text-gray-800">Lista de Secretarios</h1>
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
                                        <th>Teléfono</th>
                                        <th>Dirección</th>
                                        <th>Fecha Nac.</th>
                                        <th>Sector</th>
                                        <th>Usuario Asignado</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Secretario> secretarios = (List<Secretario>) request.getAttribute("secretarios");
                                        int i = 1;
                                        for (Secretario sec : secretarios) {
                                    %>
                                    <tr>
                                        <td><%= i++ %></td>
                                        <td><%= sec.getDni() %></td>
                                        <td><%= sec.getNombre() %></td>
                                        <td><%= sec.getApellidos() %></td>
                                        <td><%= sec.getTelefono() %></td>
                                        <td><%= sec.getDireccion() %></td>
                                        <td><%= sec.getFechaNacimiento() %></td>
                                        <td><%= sec.getSector() %></td>
                                        <td>
                                            <%= sec.getUsuario() != null ? sec.getUsuario().getNombreUsuario() : "Sin asignar" %>
                                        </td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="secretarioAlta" method="get">
                                                <input type="hidden" name="secretarioDni" value="<%= sec.getDni() %>">
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </form>
                                            <form action="svSecretario" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este secretario?');">
                                                <input type="hidden" name="secretarioDni" value="<%= sec.getDni() %>">
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
