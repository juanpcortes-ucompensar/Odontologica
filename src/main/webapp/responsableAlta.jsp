<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Responsable, com.miproyecto.model.Usuario" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>

        <%
            Responsable responsable = (request.getAttribute("responsable") == null) ? new Responsable() : (Responsable) request.getAttribute("responsable");
        %>

        <div id="content-wrapper" class="d-flex flex-column">

            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <div class="container-fluid">

                    <div class="d-sm-flex align-items-center justify-content-between mb-4 flex-column">
                        <h1 class="h3 mb-0 text-gray-800">
                            <%= responsable.getDni() == null ? "Crear Responsable" : "Actualizar Responsable" %>
                        </h1>
                    </div>

                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-6">
                            <form class="user" action="responsableAlta" method="POST">
                                <input type="hidden" name="responsableId" value="<%= responsable.getDni() %>">

                                <!-- Nombre del Responsable -->
                                <div class="form-group">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" class="form-control form-control-user" id="nombre"
                                           name="nombre" value="<%= responsable.getNombre() != null ? responsable.getNombre() : "" %>" required>
                                </div>

                                <!-- Apellidos -->
                                <div class="form-group">
                                    <label for="apellidos">Apellidos</label>
                                    <input type="text" class="form-control form-control-user" id="apellidos"
                                           name="apellidos" value="<%= responsable.getApellidos() != null ? responsable.getApellidos() : "" %>" required>
                                </div>

                                <!-- DNI -->
                                <div class="form-group">
                                    <label for="dni">DNI</label>
                                    <input type="text" class="form-control form-control-user" id="dni"
                                        name="dni" value="<%= responsable.getDni() != null ? responsable.getDni() : "" %>" 
                                        <%= responsable.getDni() != null ? "readonly" : "" %> required>
                                </div>

                                <!-- Teléfono -->
                                <div class="form-group">
                                    <label for="telefono">Teléfono</label>
                                    <input type="text" class="form-control form-control-user" id="telefono"
                                           name="telefono" value="<%= responsable.getTelefono() != null ? responsable.getTelefono() : "" %>" required>
                                </div>

                                <!-- Dirección -->
                                <div class="form-group">
                                    <label for="direccion">Dirección</label>
                                    <input type="text" class="form-control form-control-user" id="direccion"
                                           name="direccion" value="<%= responsable.getDireccion() != null ? responsable.getDireccion() : "" %>" required>
                                </div>

                                <!-- Fecha de nacimiento -->
                                <div class="form-group">
                                    <label for="fechaNacimiento">Fecha de nacimiento</label>
                                    <input type="date" class="form-control form-control-user" id="fechaNacimiento"
                                           name="fechaNacimiento" value="<%= responsable.getFechaNacimiento() != null ? responsable.getFechaNacimiento() : "" %>" required>
                                </div>

                                <!-- Tipo de Responsable -->
                                <div class="form-group">
                                    <label for="tipoResponsable">Tipo de Responsable</label>
                                    <input type="text" class="form-control form-control-user" id="tipoResponsable"
                                           name="tipoResponsable" value="<%= responsable.getTipoResponsable() != null ? responsable.getTipoResponsable() : "" %>" required>
                                </div>

                                <!-- Botón de Enviar -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= responsable.getDni() == null ? "Crear Responsable" : "Actualizar Responsable" %>
                                </button>
                            </form>
                        </div>
                        <div class="col-3"></div>
                    </div>

                </div>

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
