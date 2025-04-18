<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Odontologo" %>
<%@ page import="com.miproyecto.model.Horario" %>
<!DOCTYPE html>
<html lang="es">
<%@ include file="layout/header.jsp" %>

<body id="page-top">
    <%@ include file="layout/validarSesion.jsp" %>
    <div id="wrapper">
        <%@ include file="layout/sidebar.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@ include file="layout/navbar.jsp" %>
                <div class="container-fluid">
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Lista de Odontólogos</h1>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>DNI</th>
                                        <th>Nombre</th>
                                        <th>Apellidos</th>
                                        <th>Especialidad</th>
                                        <th>Teléfono</th>
                                        <th>Horario</th>
                                        <th>Fecha de Nacimiento</th>
                                        <th scope="col">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Odontologo> odontologos = (List<Odontologo>) request.getAttribute("odontologos");
                                        for (Odontologo o : odontologos) {
                                            Horario horario = o.getHorario();
                                            String horarioDisponible = (horario != null) ? 
                                                horario.getInicioHorario() + " - " + horario.getFinHorario() : "Sin horario asignado";
                                    %>
                                    <tr>
                                        <td><%= o.getDni() %></td>
                                        <td><%= o.getNombre() %></td>
                                        <td><%= o.getApellidos() %></td>
                                        <td><%= o.getEspecialidad() %></td>
                                        <td><%= o.getTelefono() %></td>
                                        <td><%= horarioDisponible %></td>  <!-- Mostrar el horario aquí -->
                                        <td><%= o.getFechaNacimiento() %></td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="odontologoAlta" method="get">
                                                <input type="hidden" name="odontologoDni" value="<%= o.getDni() %>">
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </form>
                                            <form action="svOdontologo" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este odontologo?');">
                                                <input type="hidden" name="odontologoDni" value="<%= o.getDni() %>">
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
            </div>
            <%@ include file="layout/footer.jsp" %>
        </div>
    </div>

    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
    <%@ include file="layout/script.jsp" %>
</body>
</html>
