<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.miproyecto.model.Horario" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>

    <% if (request.getAttribute("horarios") == null) { %>
        <script type="text/javascript">
            window.location.href = "/odontologica/index";
        </script>
    <% } else { %>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <div class="container-fluid">
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Lista de Horarios</h1>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Hora de Inicio</th>
                                        <th scope="col">Hora de Fin</th>
                                        <th scope="col">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Horario> horarios = (List<Horario>) request.getAttribute("horarios");
                                        if (horarios != null) {
                                            for (Horario h : horarios) {
                                    %>
                                    <tr>
                                        <td><%= h.getIdHorario() %></td>
                                        <td><%= h.getInicioHorario() %></td>
                                        <td><%= h.getFinHorario() %></td>
                                        <td style="display: flex; gap: 10px;">
                                            <form action="horarioAlta" method="get">
                                                <input type="hidden" name="horarioId" value="<%= h.getIdHorario() %>">
                                                <button type="submit" class="btn btn-success">Editar</button>
                                            </form>
                                            <form action="svHorario" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este horario?');">
                                                <input type="hidden" name="horarioId" value="<%= h.getIdHorario() %>">
                                                <input type="hidden" name="_method" value="DELETE">
                                                <button type="submit" class="btn btn-danger">Eliminar</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div> <!-- /.container-fluid -->
            </div> <!-- End of Main Content -->

            <%@include file="layout/footer.jsp"%>
        </div> <!-- End of Content Wrapper -->
    </div> <!-- End of Page Wrapper -->
    <% } %>

    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <%@include file="layout/script.jsp"%>
</body>
</html>
