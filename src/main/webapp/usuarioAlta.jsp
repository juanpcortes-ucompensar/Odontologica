<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Usuario" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>
        <%
            Usuario usuario = (request.getAttribute("usuario") == null) ? new Usuario() : (Usuario) request.getAttribute("usuario");
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
                            <%= usuario.getIdUsuario() == 0 ? "Crear Usuario" : "Actualizar Usuario" %>
                        </h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-6">
                            <form class="user" action="usuarioAlta" method="POST">
                                <input type="hidden" name="usuarioId" value="<%= usuario.getIdUsuario() %>">

                                <!-- Nombre de Usuario -->
                                <div class="form-group">
                                    <label for="nombreUsuario">Nombre de Usuario</label>
                                    <input type="text" class="form-control form-control-user" id="nombreUsuario"
                                           name="nombreUsuario" value="<%= usuario.getNombreUsuario() != null ? usuario.getNombreUsuario() : "" %>" required>
                                </div>

                                <!-- Contraseña -->
                                <div class="form-group">
                                    <label for="contrasena">Contraseña</label>
                                    <input type="password" class="form-control form-control-user" id="contrasena"
                                           name="contrasena" value="<%= usuario.getContrasena() != null ? usuario.getContrasena() : "" %>" required>
                                </div>

                                <!-- Rol -->
                                <div class="form-group">
                                    <label for="rol">Rol</label>
                                    <select class="form-control" id="rol" name="rol" required>
                                        <option value="">-- Selecciona un rol --</option>
                                        <option value="admin" <%= "admin".equals(usuario.getRol()) ? "selected" : "" %>>Admin</option>
                                        <option value="odontologo" <%= "odontologo".equals(usuario.getRol()) ? "selected" : "" %>>Odontólogo</option>
                                        <option value="secretario" <%= "secretario".equals(usuario.getRol()) ? "selected" : "" %>>Secretario</option>
                                    </select>
                                </div>

                                <!-- Botón de Enviar -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= usuario.getIdUsuario() == 0 ? "Crear Usuario" : "Actualizar Usuario" %>
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
