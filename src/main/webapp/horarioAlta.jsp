<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.miproyecto.model.Horario" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="layout/header.jsp"%>

<body id="page-top">
    <%@include file="layout/validarSesion.jsp"%>
    <div id="wrapper">
        <%@include file="layout/sidebar.jsp"%>
        <% 
            Horario horario = (request.getAttribute("horario") == null) ? new Horario() : (Horario) request.getAttribute("horario"); 
        %>
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
                <%@include file="layout/navbar.jsp"%>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 flex-column">
                        <h1 class="h3 mb-0 text-gray-800"><%= horario.getIdHorario() == 0 ? "Crear Horario" : "Actualizar Horario" %></h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <div class="col-3">
                        </div>
                        <div class="col-6">
                            <form class="user" action="horarioAlta" method="POST">
                                <input type="hidden" name="horarioId" value="<%= horario.getIdHorario() %>">
                                
                                <!-- Inicio del Horario -->
                                <div class="form-group">
                                    <label for="inicioHorario">Inicio del Horario</label>
                                    <input type="time" class="form-control form-control-user" id="inicioHorario" name="inicioHorario" value="<%= horario.getInicioHorario() %>" required>
                                </div>

                                <!-- Fin del Horario -->
                                <div class="form-group">
                                    <label for="finHorario">Fin del Horario</label>
                                    <input type="time" class="form-control form-control-user" id="finHorario" name="finHorario" value="<%= horario.getFinHorario() %>" required>
                                </div>

                                <!-- Botón para crear o actualizar horario -->
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <%= horario.getIdHorario() == 0 ? "Crear Horario" : "Actualizar Horario" %>
                                </button>
                            </form>
                        </div>
                        <div class="col-3">
                        </div>
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
