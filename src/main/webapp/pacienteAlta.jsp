<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Paciente, com.miproyecto.model.Responsable" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>

        <%
            Paciente paciente = (request.getAttribute("paciente") == null) ? new Paciente() : (Paciente) request.getAttribute("paciente");
            List<Responsable> responsables = (List<Responsable>) request.getAttribute("responsables");
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
                            <%= paciente.getDni() == null ? "Crear Paciente" : "Actualizar Paciente" %>
                        </h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-6">
                            <form class="user" action="pacienteAlta" method="POST">
                                <input type="hidden" name="pacienteId" value="<%= paciente.getDni() %>">

                                <!-- Nombre del Paciente -->
                                <div class="form-group">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" class="form-control form-control-user" id="nombre"
                                           name="nombre" value="<%= paciente.getNombre() != null ? paciente.getNombre() : "" %>" required>
                                </div>

                                <!-- Apellidos -->
                                <div class="form-group">
                                    <label for="apellidos">Apellidos</label>
                                    <input type="text" class="form-control form-control-user" id="apellidos"
                                           name="apellidos" value="<%= paciente.getApellidos() != null ? paciente.getApellidos() : "" %>" required>
                                </div>

                                <!-- DNI -->
                                <div class="form-group">
                                    <label for="dni">DNI</label>
                                    <input type="text" class="form-control form-control-user" id="dni"
                                           name="dni" value="<%= paciente.getDni() != null ? paciente.getDni() : "" %>" 
                                           <%= paciente.getDni() != null ? "readonly" : "" %> required>
                                </div>

                                <!-- Teléfono -->
                                <div class="form-group">
                                    <label for="telefono">Teléfono</label>
                                    <input type="text" class="form-control form-control-user" id="telefono"
                                           name="telefono" value="<%= paciente.getTelefono() != null ? paciente.getTelefono() : "" %>" required>
                                </div>

                                <!-- Dirección -->
                                <div class="form-group">
                                    <label for="direccion">Dirección</label>
                                    <input type="text" class="form-control form-control-user" id="direccion"
                                           name="direccion" value="<%= paciente.getDireccion() != null ? paciente.getDireccion() : "" %>" required>
                                </div>

                                <!-- Fecha de nacimiento -->
                                <div class="form-group">
                                    <label for="fechaNacimiento">Fecha de nacimiento</label>
                                    <input type="date" class="form-control form-control-user" id="fechaNacimiento"
                                           name="fechaNacimiento" value="<%= paciente.getFechaNacimiento() != null ? paciente.getFechaNacimiento() : "" %>" required>
                                </div>

                                <!-- Tipo de Sangre -->
                                <div class="form-group">
                                    <label for="tipoSangre">Tipo de Sangre</label>
                                    <input type="text" class="form-control form-control-user" id="tipoSangre"
                                           name="tipoSangre" value="<%= paciente.getTipoSangre() != null ? paciente.getTipoSangre() : "" %>" required>
                                </div>

                                <!-- ¿Tiene EPS? -->
                                <div class="form-group">
                                    <label for="tieneEps">¿Tiene EPS?</label>
                                    <select class="form-control" id="tieneEps" name="tieneEps" required>
                                        <option value="true" <%= paciente.isTieneEps() ? "selected" : "" %>>Sí</option>
                                        <option value="false" <%= !paciente.isTieneEps() ? "selected" : "" %>>No</option>
                                    </select>
                                </div>

                                <!-- Responsable -->
                                <div class="form-group">
                                    <label for="responsableId">Responsable</label>
                                    <select class="form-control" id="responsableId" name="responsableId" required>
                                        <option value="">-- Selecciona un responsable --</option>
                                        <%
                                            for (Responsable r : responsables) {
                                        %>
                                            <option value="<%= r.getDni() %>" <%= paciente.getDni() != null ? (r.getDni().equals(paciente.getResponsable().getDni()) ? "selected" : "") : "" %> >
                                                <%= r.getNombre() %> <%= r.getApellidos() %>
                                            </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Botón de Enviar -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= paciente.getDni() == null ? "Crear Paciente" : "Actualizar Paciente" %>
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
