<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Odontologo, com.miproyecto.model.Horario, com.miproyecto.model.Usuario" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>

        <%
            Odontologo odontologo = (request.getAttribute("odontologo") == null) ? new Odontologo() : (Odontologo) request.getAttribute("odontologo");
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
            List<Horario> horarios = (List<Horario>) request.getAttribute("horarios");
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
                            <%= odontologo.getDni() == null ? "Crear Odontólogo" : "Actualizar Odontólogo" %>
                        </h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-6">
                            <form class="user" action="odontologoAlta" method="POST">
                                <input type="hidden" name="odontologoId" value="<%= odontologo.getDni() %>">

                                <!-- Nombre del Odontólogo -->
                                <div class="form-group">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" class="form-control form-control-user" id="nombre"
                                           name="nombre" value="<%= odontologo.getNombre() != null ? odontologo.getNombre() : "" %>" required>
                                </div>

                                <!-- Apellidos -->
                                <div class="form-group">
                                    <label for="apellidos">Apellidos</label>
                                    <input type="text" class="form-control form-control-user" id="apellidos"
                                           name="apellidos" value="<%= odontologo.getApellidos() != null ? odontologo.getApellidos() : "" %>" required>
                                </div>

                                <!-- DNI -->
                                <div class="form-group">
                                    <label for="dni">DNI</label>
                                    <input type="text" class="form-control form-control-user" id="dni"
                                        name="dni" value="<%= odontologo.getDni() != null ? odontologo.getDni() : "" %>" 
                                        <%= odontologo.getDni() != null ? "readonly" : "" %> required>
                                </div>

                                <!-- Teléfono -->
                                <div class="form-group">
                                    <label for="telefono">Teléfono</label>
                                    <input type="text" class="form-control form-control-user" id="telefono"
                                           name="telefono" value="<%= odontologo.getTelefono() != null ? odontologo.getTelefono() : "" %>" required>
                                </div>

                                <!-- Dirección -->
                                <div class="form-group">
                                    <label for="direccion">Dirección</label>
                                    <input type="text" class="form-control form-control-user" id="direccion"
                                           name="direccion" value="<%= odontologo.getDireccion() != null ? odontologo.getDireccion() : "" %>" required>
                                </div>

                                <!-- Fecha de nacimiento -->
                                <div class="form-group">
                                    <label for="fechaNacimiento">Fecha de nacimiento</label>
                                    <input type="date" class="form-control form-control-user" id="fechaNacimiento"
                                           name="fechaNacimiento" value="<%= odontologo.getFechaNacimiento() != null ? odontologo.getFechaNacimiento() : "" %>" required>
                                </div>

                                <!-- Especialidad -->
                                <div class="form-group">
                                    <label for="especialidad">Especialidad</label>
                                    <input type="text" class="form-control form-control-user" id="especialidad"
                                           name="especialidad" value="<%= odontologo.getEspecialidad() != null ? odontologo.getEspecialidad() : "" %>" required>
                                </div>

                                <!-- Usuario (Nombre, Contraseña, Rol) -->
                                <div class="form-group">
                                    <label for="usuarioId">Usuario</label>
                                    <select class="form-control" id="usuarioId" name="usuarioId" required>
                                        <option value="">-- Selecciona un usuario --</option>
                                        <%
                                            for (Usuario u : usuarios) {
                                        %>
                                            <option value="<%= u.getIdUsuario() %>" <%= odontologo.getDni() != null ?(u.getIdUsuario() == odontologo.getUsuario().getIdUsuario() ? "selected" : "") : "" %>>
                                                <%= u.getNombreUsuario() %>
                                            </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Horario (Inicio y Fin) -->
                                <div class="form-group">
                                    <label for="horarioId">Horario</label>
                                    <select class="form-control" id="horarioId" name="horarioId" required>
                                        <option value="">-- Selecciona un horario --</option>
                                        <%
                                            for (Horario h : horarios) {
                                        %>
                                            <option value="<%= h.getIdHorario() %>" <%= odontologo.getDni() != null ? (h.getIdHorario() == odontologo.getHorario().getIdHorario() ? "selected" : "") : "" %>>
                                                <%= h.getInicioHorario() %> - <%= h.getFinHorario() %>
                                            </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <!-- Botón de Enviar -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= odontologo.getDni() == null ? "Crear Odontólogo" : "Actualizar Odontólogo" %>
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
