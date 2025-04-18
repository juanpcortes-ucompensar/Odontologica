<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Secretario, com.miproyecto.model.Usuario" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>

        <%
            Secretario secretario = (request.getAttribute("secretario") == null) ? new Secretario() : (Secretario) request.getAttribute("secretario");
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
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
                            <%= secretario.getDni() == null ? "Crear Secretario" : "Actualizar Secretario" %>
                        </h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-6">
                            <form class="user" action="secretarioAlta" method="POST">
                                <input type="hidden" name="secretarioDni" value="<%= secretario.getDni() %>">

                                <!-- Nombre del Secretario -->
                                <div class="form-group">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" class="form-control form-control-user" id="nombre"
                                           name="nombre" value="<%= secretario.getNombre() != null ? secretario.getNombre() : "" %>" required>
                                </div>

                                <!-- Apellidos -->
                                <div class="form-group">
                                    <label for="apellidos">Apellidos</label>
                                    <input type="text" class="form-control form-control-user" id="apellidos"
                                           name="apellidos" value="<%= secretario.getApellidos() != null ? secretario.getApellidos() : "" %>" required>
                                </div>

                                <!-- DNI -->
                                <div class="form-group">
                                    <label for="dni">DNI</label>
                                    <input type="text" class="form-control form-control-user" id="dni"
                                           name="dni" value="<%= secretario.getDni() != null ? secretario.getDni() : "" %>" 
                                           <%= secretario.getDni() != null ? "readonly" : "" %> required>
                                </div>

                                <!-- Teléfono -->
                                <div class="form-group">
                                    <label for="telefono">Teléfono</label>
                                    <input type="text" class="form-control form-control-user" id="telefono"
                                           name="telefono" value="<%= secretario.getTelefono() != null ? secretario.getTelefono() : "" %>" required>
                                </div>

                                <!-- Dirección -->
                                <div class="form-group">
                                    <label for="direccion">Dirección</label>
                                    <input type="text" class="form-control form-control-user" id="direccion"
                                           name="direccion" value="<%= secretario.getDireccion() != null ? secretario.getDireccion() : "" %>" required>
                                </div>

                                <!-- Fecha de nacimiento -->
                                <div class="form-group">
                                    <label for="fechaNacimiento">Fecha de nacimiento</label>
                                    <input type="date" class="form-control form-control-user" id="fechaNacimiento"
                                           name="fechaNacimiento" value="<%= secretario.getFechaNacimiento() != null ? secretario.getFechaNacimiento() : "" %>" required>
                                </div>

                                <!-- Sector -->
                                <div class="form-group">
                                    <label for="sector">Sector</label>
                                    <input type="text" class="form-control form-control-user" id="sector"
                                           name="sector" value="<%= secretario.getSector() != null ? secretario.getSector() : "" %>" required>
                                </div>

                                <!-- Usuario (Nombre, Contraseña, Rol) -->
                                <div class="form-group">
                                    <label for="usuarioId">Usuario</label>
                                    <select class="form-control" id="usuarioId" name="usuarioId" required>
                                        <option value="">-- Selecciona un usuario --</option>
                                        <%
                                            for (Usuario u : usuarios) {
                                        %>
                                            <option value="<%= u.getIdUsuario() %>" <%= secretario.getDni() != null ?(u.getIdUsuario() == secretario.getUsuario().getIdUsuario() ? "selected" : "") : "" %>>
                                                <%= u.getNombreUsuario() %>
                                            </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Botón de Enviar -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= secretario.getDni() == null ? "Crear Secretario" : "Actualizar Secretario" %>
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
